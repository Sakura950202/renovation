package com.renovation.common.util;

import com.renovation.common.annotation.ExcelAttribute;
import com.sun.istack.internal.NotNull;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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
     * 标题所在行
     */
    private static Integer TITLE_ROW_INDEX = 0;

    /**
     * 写入数据的起始行，从第二行开始，因为第一行为表头
     */
    private static Integer DATA_ROW_INDEX = 1;

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
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> Boolean export(HttpServletResponse httpServletResponse, List<T> list, @NotNull String fileName) throws Exception {
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
     * @param <T>
     * @return 导出结果
     */
    public static <T> Boolean export(HttpServletResponse httpServletResponse, Class clazz, List<T> list, @NotNull String fileName) throws Exception {
        // 创建工作簿对象
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();

        // 获取导出数据类型的全部属性
        Field[] declaredFields = clazz.getDeclaredFields();

        // 创建sheet对象
        XSSFSheet xssfSheet = xssfWorkbook.createSheet(fileName);

        // 组装表头数据
        setTitleRow(declaredFields, xssfSheet);

        // 组装表格数据
        setDataRow(declaredFields, list, xssfWorkbook, xssfSheet);

        // 响应数据
        return downLoadExcel(fileName, httpServletResponse, xssfWorkbook);
    }

    /**
     * 用来组装表头数据
     *
     * @param declaredFields 导出数据类型属性数组
     * @param xssfSheet      excel的sheet对象
     */
    private static void setTitleRow(Field[] declaredFields, XSSFSheet xssfSheet) {
        // 创建行，用来保存表头，表头使用数据类型的属性
        XSSFRow titleRow = xssfSheet.createRow(TITLE_ROW_INDEX);
        // 创建原子操作对象，用来创建单元格（原子操作类保证了线程安全）
        AtomicInteger atomicInteger = new AtomicInteger();
        // 循环创建表头
        for (Field declaredField : declaredFields) {
            // 创建单元格
            XSSFCell cell = titleRow.createCell(atomicInteger.getAndIncrement());
            // 判断属性上面是否包含注解
            if (declaredField.isAnnotationPresent(ExcelAttribute.class)) {
                // 获取注解对象
                ExcelAttribute annotation = declaredField.getAnnotation(ExcelAttribute.class);
                // 设置单元格值为注解的名称
                cell.setCellValue(annotation.name());
            }
        }
    }

    /**
     * 用来组装表格数据
     *
     * @param declaredFields 导出数据类型属性数组
     * @param list           数据集合
     * @param xssfWorkbook   工作簿对象
     * @param xssfSheet      sheet对象
     * @param <T>
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    private static <T> void setDataRow(Field[] declaredFields, List<T> list, XSSFWorkbook xssfWorkbook, XSSFSheet xssfSheet) throws IllegalArgumentException, IllegalAccessException {
        // 创建原子操作对象，用来创建单元格（原子操作类保证了线程安全）
        AtomicInteger atomicInteger = new AtomicInteger(DATA_ROW_INDEX);

        // 循环创建导出数据
        for (T t : list) {
            // 创建行，用来保存数据
            XSSFRow xssfRow = xssfSheet.createRow(atomicInteger.getAndIncrement());

            // 根据导出对象的属性，创建单元格
            for (Field declaredField : declaredFields) {
                // 判断属性上是否包含注解
                if (declaredField.isAnnotationPresent(ExcelAttribute.class)) {
                    // 将访问安全检查的开关打开，可以操作类的私有属性
                    declaredField.setAccessible(true);
                    // 获得属性上的注解对象
                    ExcelAttribute annotation = declaredField.getAnnotation(ExcelAttribute.class);
                    // 获得序号属性
                    int sort = annotation.sort();
                    // 创建单元格
                    XSSFCell xssfCell = xssfRow.createCell(sort);

                    // 判断属性值是否为空
                    if (Objects.nonNull(declaredField.get(t))) {
                        // 获取属性的类型
                        String type = declaredField.getGenericType().toString();
                        // 获取属性值
                        String value = declaredField.get(t).toString();

                        // 判断属性类型，数据做不同处理
                        switch (type) {
                            case "class java.time.LocalDateTime":
                                // 处理时间类型格式
                                XSSFCellStyle cellStyle = xssfWorkbook.createCellStyle();
                                cellStyle.setDataFormat(xssfWorkbook.createDataFormat().getFormat(annotation.format()));
                                // 设置值和类型
                                xssfCell.setCellValue((LocalDateTime) declaredField.get(t));
                                xssfCell.setCellStyle(cellStyle);
                                break;

                            case "class java.lang.boolean":
                                xssfCell.setCellValue(value.equals("true") ? "1" : "0");
                                break;

                            default:
                                xssfCell.setCellValue(value);
                        }
                    }
                }
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
