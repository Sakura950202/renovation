package com.renovation.common.annotation;

import org.apache.poi.ss.usermodel.IndexedColors;

import java.lang.annotation.*;

/**
 * @ClassName ExcelAttribute
 * @Description TODO 自定义Excel导出普通属性注解
 * @Author SAKURA
 * @Date 2021/3/22 9:58
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelAttribute {

    /**
     * 单元格名称
     *
     * @return
     */
    String name() default "";

    /**
     * 单元格数据格式
     *
     * @return
     */
    String[] format() default "";

    /**
     * 单元格字体颜色
     *
     * @return
     */
    IndexedColors color() default IndexedColors.BLACK;

}
