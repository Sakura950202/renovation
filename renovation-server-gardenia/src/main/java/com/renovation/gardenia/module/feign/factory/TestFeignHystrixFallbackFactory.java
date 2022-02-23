package com.renovation.gardenia.module.feign.factory;

import com.renovation.common.config.BaseFeignHystrixFallbackFactory;
import com.renovation.gardenia.module.feign.TestFeign;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @ClassName TestFeignHystrixFallbackFactory
 * @Description TODO 用来处理测试远程调用服务降级
 * @Author SAKURA
 * @Date 2021/1/19 22:58
 */
@Component
@Slf4j
public class TestFeignHystrixFallbackFactory extends BaseFeignHystrixFallbackFactory<TestFeign> {

    @Override
    public Class<TestFeign> getProxyClass() {
        return TestFeign.class;
    }

    @Override
    public Logger getLog() {
        return log;
    }

}
