package com.renovation.gardenia.module.feign;

import com.renovation.common.constant.FeignConstant;
import com.renovation.common.vo.ResultVo;
import com.renovation.gardenia.module.feign.factory.TestFeignHystrixFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName TestFeign
 * @Description TODO 用来测试feign
 * @Author SAKURA
 * @Date 2021/01/14 15:16
 *
 * FeignClient用来标注此接口为feign远程调用接口，value用来指定远程调用的服务名称，fallbackFactory用来处理服务调用出错的容灾处理
 */
@FeignClient(value = FeignConstant.ROSE, fallbackFactory = TestFeignHystrixFallbackFactory.class)
public interface TestFeign {

    /**
     * 用来远程调用会员信息
     * @param id 会员id
     * @return 视图对象
     */
    @GetMapping("/rose/customer/getCustomer")
    ResultVo getFeignCustomer(@RequestParam("id") Integer id);

}
