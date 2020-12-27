package com.renovation.rose.module.customer.controller;

import com.renovation.rose.module.customer.dto.CustomerDto;
import com.renovation.rose.module.customer.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @ClassName CustomerController
 * @Description TODO
 * @Author SAKURA
 * @Date 2020/12/27 15:28
 * @Version 1.0
 */
@Api(tags = "会员相关接口")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @ApiOperation("会员新增或修改")
    @PostMapping("/saveOrUpdateCustomer")
    public Boolean saveOrUpdateAdmin(@RequestBody @Valid CustomerDto customerDto) {
        return customerService.saveOrUpdateAdmin(customerDto);
    }
}
