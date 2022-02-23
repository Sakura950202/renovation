package com.renovation.common.config;

import com.renovation.common.vo.ResultVo;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;

import java.lang.reflect.Proxy;

/**
 * @ClassName BaseFeignHystrixFallbackFactory
 * @Description TODO 基础远程熔断处理
 * @Author SAKURA
 * @Date 2021/5/6 12:36
 */
public abstract class BaseFeignHystrixFallbackFactory<T> implements FallbackFactory<T> {

    @Override
    public T create(Throwable cause) {
        //noinspection unchecked
        return (T) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class[]{this.getProxyClass()},
                (proxy, method, args) -> {
                    this.getLog().error("Feign调用失败，失败类：[{}]，方法：[{}]，参数：{}", this.getClass().getName(), method.getName(), args);
                    return new ResultVo("远程调用失败！");
                }
        );
    }

    /**
     * 获取被代理类的类对象
     *
     * @return 类对象
     */
    public abstract Class<T> getProxyClass();

    /**
     * 获取日志对象
     *
     * @return 日志对象
     */
    public abstract Logger getLog();

}
