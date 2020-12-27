package com.renovation.rose.module.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.renovation.rose.module.customer.dto.CustomerDto;
import com.renovation.rose.module.customer.entity.Customer;

/**
 * @ClassName CustomerService
 * @Description TODO
 * @Author SAKURA
 * @Date 2020/12/27 15:25
 * @Version 1.0
 */
public interface CustomerService extends IService<Customer> {

    /**
     * 新增或修改会员
     * @param customerDto
     * @return
     */
    Boolean saveOrUpdateAdmin(CustomerDto customerDto);

}
