package com.renovation.rose.module.customer.controller;

import com.renovation.rose.module.customer.dto.CustomerDto;
import com.renovation.rose.module.customer.entity.Customer;
import com.renovation.rose.module.customer.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("获取会员")
    @GetMapping("/getCustomer")
    public Customer getCustomer(Integer id) {
        return customerService.getCustomer(id);
    }
}
