package com.renovation.gardenia.module.feign.factory;

import com.renovation.common.vo.ResultVo;
import com.renovation.gardenia.module.feign.TestFeign;
import feign.hystrix.FallbackFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * @ClassName TestFeignHystrixFallbackFactory
 * @Description TODO 用来处理测试远程调用服务降级
 * @Author SAKURA
 * @Date 2021/1/19 22:58
 * @Version 1.0
 */
@Component
@Log4j2
public class TestFeignHystrixFallbackFactory implements FallbackFactory<TestFeign> {
    @Override
    public TestFeign create(Throwable throwable) {
        return new TestFeign() {
            @Override
            public ResultVo getFeignCustomer(Integer id) {
                log.info("测试断路器——服务降级");
                return new ResultVo("测试断路器——服务降级");
            }
        };
    }
}
