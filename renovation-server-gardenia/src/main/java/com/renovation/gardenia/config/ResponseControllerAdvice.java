package com.renovation.gardenia.config;

import com.alibaba.fastjson.JSON;
import com.renovation.common.annotation.NotResponseBody;
import com.renovation.common.enums.ResultVoCode;
import com.renovation.common.vo.ResultVo;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Type;

/**
 * @ClassName: ResponseControllerAdvice
 * @Description: TODO 全局处理响应数据
 * @Author: SAKURA
 * @Date: 2020/4/5 22:45
 *
 * 因为SpringBoot默认的ResponseBody的处理程序就是HandlerMethodReturnValueHandler，所以我们自己写的HandlerMethodReturnValueHandler通常无法生效，
 * 非要使用HandlerMethodReturnValueHandler，那么只能替换掉默认的
 * 其实这里就是对Controller层返回的数据做统一处理，处理成自己想要的结果
 * 下面basePackages参数可以是数组{"com.automvc", "com.test"}，配置为多个包，但是项目是分模块开发，为了不配置每个包，所以将模块放进module包，统一拦截
 **/

@RestControllerAdvice(basePackages = "com.renovation.gardenia.module")
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {

        // 这里是通过反射得到的
        // 如果接口返回的类型本身就是ResultVO，或者使用了自定义注解，那就没有必要进行额外的操作，返回false
        return !(methodParameter.getGenericParameterType().equals(ResultVo.class) || methodParameter.hasMethodAnnotation(NotResponseBody.class));
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        // String类型不能直接包装，所以要进行些特别的处理
        Type genericParameterType = methodParameter.getGenericParameterType();
        if (genericParameterType.equals(String.class)) {
            return JSON.toJSONString(new ResultVo<>(ResultVoCode.SUCCESS, data));
        }

        // 判断返回的类型是否就是试图对象，如果是的直接返回前端
        Class returnType = methodParameter.getMethod().getReturnType();
        if (returnType.equals(ResultVo.class)) {
            return data;
        }

        // 将数据包装到视图对象中，返回给前端
        return new ResultVo<>(ResultVoCode.SUCCESS, data);
    }

}
