package com.renovation.gardenia.module.admin.feign;

import com.renovation.common.constant.FeignConstant;
import com.renovation.common.vo.ResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName TestFeign
 * @Description TODO 用来测试feign
 * @Author SAKURA
 * @Date 2021/01/14 15:16
 */
@FeignClient(value = FeignConstant.ROSE)
public interface TestFeign {

    @GetMapping("/customer/getCustomer")
    ResultVo getFeignCustomer(@RequestParam("id") Integer id);

}
