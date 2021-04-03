package com.renovation.gardenia.module.feign.factory;

import com.renovation.common.vo.ResultVo;
import com.renovation.gardenia.module.feign.TestFeign;
import feign.hystrix.FallbackFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName TestFeignHystrixFallbackFactory
 * @Description TODO 用来处理测试远程调用服务降级
 * @Author SAKURA
 * @Date 2021/1/19 22:58
 */
@Component
@Log4j2
public class TestFeignHystrixFallbackFactory implements FallbackFactory<TestFeign>, InvocationHandler {

    @Override
    public TestFeign create(Throwable throwable) {
        return (TestFeign) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class[]{TestFeign.class},
                this
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        String className = this.getClass().getName();
        log.error("Feign[{}#{}]失败，参数：{}", className, method.getName(), args);
        return new ResultVo("测试断路器——服务降级");
    }
}
