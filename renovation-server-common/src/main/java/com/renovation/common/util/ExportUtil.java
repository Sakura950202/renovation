package com.renovation.common.util;

import com.renovation.common.annotation.ExcelAttribute;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName ExportUtil
 * @Description TODO 自定义单表导出工具
 * @Author SAKURA
 * @Date 2021/4/6 10:20
 */
@Log4j2
public class ExportUtil<T> {

    /**
     * 定义原子变量，用来设置行号
     */
    private static AtomicInteger ATOMIC_ROW = new AtomicInteger();

    /**
     * 定义原子变量，用来设置列号
     */
    private static AtomicInteger ATOMIC_COLUMN = new AtomicInteger();

    /**
     * 标题字体大小（磅）
     */
    private static final Short TITLE_FONT_SIZE = 24;

    /**
     * 字符串类型的数据单元格宽度
     */
    private static final Integer STRING_DATA_COLUMN_WIDTH = 18;

    /**
     * 创建集合，用来保存每列数据的最大宽度
     */
    private static final Map<String, Integer> COLUMN_MAX_WIDTH_MAP = new ConcurrentHashMap<>();

    /**
     * 文件后缀名
     */
    private static final String FILE_SUFFIX = ".xlsx";

    /**
     * 导出方法（不需要传入对象类型）
     *
     * @param httpServletResponse 响应对象
     * @param list                导出数据
     * @param fileName            导出文件名
     * @param <T>                 泛型
     * @return 导出结果
     * @throws Exception 异常
     */
    public static <T> Boolean export(HttpServletResponse httpServletResponse, List<T> list, @NotBlank String fileName) throws Exception {
        // 判断集合是否为空
        if (!CollectionUtils.isEmpty(list)) {
            // 获取集合第一个数据
            T t = list.get(0);
            // 获取数据的类型
            Class<?> clazz = t.getClass();
            return export(httpServletResponse, clazz, list, fileName);
        }
        return false;
    }

    /**
     * 导出方法（需要传入对象类型）
     *
     * @param httpServletResponse 响应对象
     * @param clazz               导出对象类型
     * @param list                导出数据
     * @param fileName            导出文件名
     * @param <T>                 泛型
     * @return 导出结果
     */
    public static <T> Boolean export(HttpServletResponse httpServletResponse, Class<?> clazz, List<T> list, @NotBlank String fileName) throws Exception {
        // 创建工作簿对象，SXSSFWorkbook性能更高，在内存中保留100条数据，其余写入硬盘临时文件
        SXSSFWorkbook workbook = new SXSSFWorkbook(100);

        // 创建sheet对象
        Sheet sheet = workbook.createSheet("sheet1");

        // 获取导出数据类型的全部属性
        List<Field> fieldList = Arrays.asList(clazz.getDeclaredFields());

        // 组装表头数据
        setTitleRow(fieldList, workbook, sheet, fileName);

        // 组装表格数据
        setDataRow(fieldList, list, workbook, sheet);

        // 设置每列数据最大宽度
        setCellMaxWidth(fieldList, sheet);

        // 将行号和列号全部重置
        ATOMIC_ROW.set(0);
        ATOMIC_COLUMN.set(0);

        // 响应数据
        return downLoadExcel(fileName, httpServletResponse, workbook);
    }

    /**
     * 用来组装标题、表头
     *
     * @param fieldList 导出数据类型属性集合
     * @param sheet     excel的sheet对象
     */
    private static void setTitleRow(List<Field> fieldList, SXSSFWorkbook workbook, Sheet sheet, String fileName) {
        // 创建行，用来保存标题，使用文件名做表头
        Row rowTitle = sheet.createRow(ATOMIC_ROW.get());

        // 获取标题样式
        CellStyle cellStyleTitle = setTitleStyle(workbook, sheet, fieldList.size());

        // 创建标题单元格
        Cell cellTitle = rowTitle.createCell(ATOMIC_COLUMN.get());
        cellTitle.setCellValue(fileName);
        cellTitle.setCellStyle(cellStyleTitle);

        // 创建行，用来保存表头，表头使用数据类型的属性
        Row rowHead = sheet.createRow(ATOMIC_ROW.getAndIncrement());

        // 获取表头样式对象
        CellStyle cellStyleHead = setHeadStyle(workbook);

        // 创建序号单元格
        Cell cellOrder = rowHead.createCell(ATOMIC_COLUMN.getAndIncrement());
        cellOrder.setCellValue("序号");
        cellOrder.setCellStyle(cellStyleHead);

        // 循环创建表头
        for (Field field : fieldList) {
            // 创建单元格
            Cell cell = rowHead.createCell(ATOMIC_COLUMN.getAndIncrement());
            // 判断属性上面是否包含属性注解
            if (field.isAnnotationPresent(ExcelAttribute.class)) {
                // 获取注解对象
                ExcelAttribute annotation = field.getAnnotation(ExcelAttribute.class);
                // 设置单元格值为注解的名称
                cell.setCellValue(annotation.name());
                cell.setCellStyle(cellStyleHead);
            }
        }

        // 将列号还原
        ATOMIC_COLUMN.set(0);
    }

    /**
     * 用来设置标题样式
     *
     * @param workbook  工作簿对象
     * @param sheet     sheet对象
     * @param endCell   结束合并的单元格列
     * @return 单元格样式对象
     */
    private static CellStyle setTitleStyle(SXSSFWorkbook workbook, Sheet sheet, @NotNull int endCell) {
        // 设置单元格合并， 4个参数，分别为起始行，结束行，起始列，结束列
        CellRangeAddress cellAddresses = new CellRangeAddress(ATOMIC_ROW.get(), ATOMIC_ROW.getAndIncrement(), ATOMIC_COLUMN.get(), endCell);
        sheet.addMergedRegion(cellAddresses);

        // 创建单元格样式对象
        CellStyle cellStyle = workbook.createCellStyle();
        // 设置居中对齐
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 设置字体加粗
        Font font = workbook.createFont();
        font.setBold(true);
        // 设置字体高度
        font.setFontHeightInPoints(TITLE_FONT_SIZE);

        cellStyle.setFont(font);
        return cellStyle;
    }

    /**
     * 用来设置表头样式
     *
     * @param workbook 工作簿对象
     * @return 单元格样式对象
     */
    private static CellStyle setHeadStyle(SXSSFWorkbook workbook) {
        // 创建单元格样式对象
        CellStyle cellStyle = workbook.createCellStyle();
        // 设置左右居中对齐
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 设置上下居中对齐
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置字体加粗
        Font font = workbook.createFont();
        font.setBold(true);
        cellStyle.setFont(font);

        // 设置单元格四周边框
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);

        return cellStyle;
    }

    /**
     * 用来组装表格数据
     *
     * @param fieldList 导出数据类型属性集合
     * @param list      数据集合
     * @param workbook  工作簿对象
     * @param sheet     sheet对象
     * @param <T>       泛型
     * @throws IllegalArgumentException 非法参数异常
     * @throws IllegalAccessException   访问权限异常
     */
    private static <T> void setDataRow(List<Field> fieldList, List<T> list, SXSSFWorkbook workbook, Sheet sheet) throws IllegalArgumentException, IllegalAccessException {
        // 循环创建导出数据
        for (T t : list) {
            // 创建行，用来保存数据
            Row row = sheet.createRow(ATOMIC_ROW.get());

            // 创建序号单元格
            Cell cellOrder = row.createCell(ATOMIC_COLUMN.getAndIncrement());
            // 设置序号值
            cellOrder.setCellValue(ATOMIC_ROW.getAndIncrement() - 2);
            // 设置序号单元格样式
            cellOrder.setCellStyle(setCellStyle(workbook, null, "order", 5));

            // 根据导出对象的属性，创建单元格
            for (Field field : fieldList) {
                // 判断属性上是否包含注解
                if (field.isAnnotationPresent(ExcelAttribute.class)) {
                    // 将访问安全检查的开关打开，可以操作类的私有属性
                    field.setAccessible(true);
                    // 获得属性上的注解对象
                    ExcelAttribute annotation = field.getAnnotation(ExcelAttribute.class);
                    // 创建单元格
                    Cell cell = row.createCell(ATOMIC_COLUMN.getAndIncrement());

                    // 判断属性值是否为空
                    if (Objects.nonNull(field.get(t))) {
                        // 获取属性当前的值
                        String value = field.get(t).toString();
                        // 获取注解的格式属性
                        List<String> formatList = Arrays.asList(annotation.format());
                        // 获取注解的字体颜色属性
                        IndexedColors color = annotation.color();
                        // 创建单元格样式
                        CellStyle cellStyle = setCellStyle(workbook, color, field.getName(), value.length());

                        //设置单元格的值
                        setCellValue(field, formatList, cellStyle, workbook, cell, t);
                    }
                }
            }

            // 将列号还原
            ATOMIC_COLUMN.set(0);
        }
    }

    /**
     * 用来设置数据单元格样式
     *
     * @param workbook    工作簿对象
     * @param color       字体颜色对象
     * @param fieldName   属性名
     * @param valueLength 属性值长度
     * @return 单元格样式
     */
    private static CellStyle setCellStyle(SXSSFWorkbook workbook, IndexedColors color, @NotBlank String fieldName, @NotBlank Integer valueLength) {
        // 创建单元格样式对象
        CellStyle cellStyle = workbook.createCellStyle();
        // 创建单元格字体对象
        Font font = workbook.createFont();

        // 设置字体颜色
        // 判断是否传入颜色
        if (Objects.isNull(color)) {
            font.setColor(IndexedColors.BLACK.getIndex());
        } else {
            font.setColor(color.getIndex());
        }

        cellStyle.setFont(font);

        // 设置单元格四周边框
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);

        // 设置单元格最大宽度，为内容长度
        if (Objects.isNull(COLUMN_MAX_WIDTH_MAP.get(fieldName))) {
            COLUMN_MAX_WIDTH_MAP.put(fieldName, valueLength);
        }

        return cellStyle;
    }

    /**
     * 用来设置单元格的值
     *
     * @param field      属性对象
     * @param formatList 属性值格式
     * @param cellStyle  单元格样式
     * @param workbook   工作簿对象
     * @param cell       单元格对象
     * @param t          导出数据对象
     * @param <T>        泛型
     * @throws IllegalAccessException 访问权限异常
     */
    private static <T> void setCellValue(@NotNull Field field,
                                         List<String> formatList,
                                         CellStyle cellStyle,
                                         SXSSFWorkbook workbook,
                                         Cell cell,
                                         T t) throws IllegalAccessException {
        // 获取属性类型
        Class<?> type = field.getType();
        // 获取属性的值
        Object object = field.get(t);

        // 判断是否LocalDate、LocalDateTime类型
        if (LocalDate.class.equals(type) || LocalDateTime.class.equals(type)) {
            // 判断是否传入格式
            if (!CollectionUtils.isEmpty(formatList)) {
                // 处理时间类型格式
                cellStyle.setDataFormat(workbook.createDataFormat().getFormat(formatList.get(0)));
            }
            // 设置单元格数据格式
            cell.setCellStyle(cellStyle);
            // 设置单元格值
            if (LocalDate.class.equals(type)) {
                cell.setCellValue((LocalDate) object);
            } else {
                cell.setCellValue((LocalDateTime) object);
            }
            return;
        }

        // 判断是否boolean类型
        if (Boolean.class.equals(type)) {
            // 设置单元格数据格式
            cell.setCellStyle(cellStyle);
            // 判断是否传入格式
            if (!CollectionUtils.isEmpty(formatList)) {
                cell.setCellValue(Boolean.TRUE.equals(object) ? formatList.get(0) : formatList.get(1));
            } else {
                // 设置单元格值
                cell.setCellValue(object.toString());
            }
            return;
        }

        // 判断是否String类型
        if (String.class.equals(type)) {
            // 设置单元格数据格式
            cell.setCellStyle(cellStyle);
            // 设置单元格值
            cell.setCellValue(object.toString());
            // 将字符串单元格最大宽度设置为固定值
            COLUMN_MAX_WIDTH_MAP.put(field.getName(), STRING_DATA_COLUMN_WIDTH);
            return;
        }

        // 设置单元格数据格式
        cell.setCellStyle(cellStyle);

        // 设置单元格值
        cell.setCellValue(object.toString());

        // 判断是否传入格式
        if (!CollectionUtils.isEmpty(formatList)) {
            // 循环属性的格式
            formatList.forEach(i -> {
                // 将格式通过 - 拆分
                String[] split = i.split("-");
                // 判断 - 前面的值，是否和属性的值相等，相等将 - 后面的值设置为单元格的值
                if (split[0].equals(object.toString())) {
                    // 设置单元格值
                    cell.setCellValue(split[1]);
                }
            });
        }

        // 判断当前值的长度和集合中的值的长度大小，将大的设置为新的值
        if (COLUMN_MAX_WIDTH_MAP.get(field.getName()) < object.toString().length()) {
            COLUMN_MAX_WIDTH_MAP.put(field.getName(), object.toString().length());
        }
    }

    /**
     * 用来设置每列数据的最大宽度
     *
     * @param fieldList 导出数据类型属性集合
     * @param sheet     sheet对象
     */
    private static void setCellMaxWidth(List<Field> fieldList, Sheet sheet) {
        // 循环设置列宽，循环的次数需要多一次，因为第一次为设置序号的宽度
        for (int i = 0; i < fieldList.size() + 1; i++) {
            if (i == 0) {
                // 序号的列宽度
                sheet.setColumnWidth(i, COLUMN_MAX_WIDTH_MAP.get("order") * 256 + 200);
            } else {
                // 数据的列宽度
                String name = fieldList.get(i - 1).getName();
                sheet.setColumnWidth(i, COLUMN_MAX_WIDTH_MAP.get(name) * 256 + 200);
            }
        }
    }

    /**
     * 设置相应头，将要导出的数据以流的形式发送给前端，控制浏览器以下载的形式打开文件
     *
     * @param fileName 文件名
     * @param response 服务器响应对象
     * @param workbook 要导出的excel数据，java的excel一般使用Workbook对象
     */
    private static Boolean downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) throws IOException {
        try {
            // 设置响应的字符编码格式
            response.setCharacterEncoding("UTF-8");

            // 设置响应的MIME类型
            response.setHeader("content-Type", "application/vnd.ms-excel");

            // 设置响应的MIME，指示MIME如何显示附加的文件
            // URLEncoder，客户端在进行网页请求的时候，网址中可能会包含非ASCII码形式的内容，比如中文，
            // 而直接把中文放到网址中请求是不允许的，所以需要用URLEncoder编码地址
            // 当然，可以编码也是可以解码的，使用URLDncoder可以解码
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + "." + FILE_SUFFIX, "UTF-8"));

            // 使用响应的输出流，将数据传输出去
            workbook.write(response.getOutputStream());

            return true;
        } catch (Exception e) {
            log.error("数据导出失败！失败文件名：【{}】", fileName);
            throw new IOException(e.getMessage());
        }
    }

}
