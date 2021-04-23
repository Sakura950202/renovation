package com.renovation.gardenia.module.feign.factory;

import com.renovation.common.vo.ResultVo;
import com.renovation.gardenia.module.feign.TestFeign;
import feign.hystrix.FallbackFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * @ClassName TestFeignHystrixFallbackFactory
 * @Description TODO 用来处理测试远程调用服务降级
 * @Author SAKURA
 * @Date 2021/1/19 22:58
 *
 * 使用JDK的动态代理实现统一处理远程调用失败信息
 */
@Component
@Log4j2
public class TestFeignHystrixFallbackFactory implements FallbackFactory<TestFeign> {

    @Override
    public TestFeign create(Throwable throwable) {
        return (TestFeign) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class[]{TestFeign.class},
                (proxy, method, args) -> {
                    log.error("Feign调用失败，失败类：[{}]，方法：[{}]，参数：{}", this.getClass().getName(), method.getName(), args);
                    return new ResultVo("远程调用失败！");
                }
        );
    }

}
