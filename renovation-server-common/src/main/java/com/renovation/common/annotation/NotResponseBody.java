package com.renovation.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName NotResponseBody
 * @Description TODO 自定义注解，用来处理出参不为统一数据响应体视图对象
 * @Author SAKURA
 * @Date 2021/5/6 16:12
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NotResponseBody {
}
