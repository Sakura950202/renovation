package com.renovation.common.annotation;

import java.lang.annotation.*;

/**
 * @ClassName ExcelAttribute
 * @Description TODO 自定义Excel导出注解
 * @Author SAKURA
 * @Date 2021/3/22 9:58
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface ExcelAttribute {

    /**
     * 属性名
     *
     * @return
     */
    String name() default "";

    /**
     * 序号
     *
     * @return
     */
    int sort();

    /**
     * 格式
     *
     * @return
     */
    String format() default "";

}
