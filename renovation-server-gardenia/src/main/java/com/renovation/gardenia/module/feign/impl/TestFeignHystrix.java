package com.renovation.gardenia.module.feign.impl;

import com.renovation.common.vo.ResultVo;
import com.renovation.gardenia.module.feign.TestFeign;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * @ClassName TestFeignHystric
 * @Description TODO
 * @Author SAKURA
 * @Date 2021/01/19 16:57
 */
@Component
@Log4j2
public class TestFeignHystrix implements TestFeign {

    @Override
    public ResultVo getFeignCustomer(Integer id) {
        log.info("测试断路器");
        return new ResultVo("测试断路器");
    }

}
