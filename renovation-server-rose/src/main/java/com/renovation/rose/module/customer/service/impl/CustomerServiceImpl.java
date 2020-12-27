package com.renovation.rose.module.customer.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.renovation.common.enums.APIExceptionCodeType;
import com.renovation.common.exception.APIException;
import com.renovation.rose.module.customer.dto.CustomerDto;
import com.renovation.rose.module.customer.entity.Customer;
import com.renovation.rose.module.customer.mapper.CustomerMapper;
import com.renovation.rose.module.customer.mapstruct.CustomerDtoMapStruct;
import com.renovation.rose.module.customer.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @ClassName CustomerServiceImpl
 * @Description TODO
 * @Author SAKURA
 * @Date 2020/12/27 15:26
 * @Version 1.0
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Resource
    private CustomerDtoMapStruct customerDtoMapStruct;

    @Override
    @Transactional(rollbackFor = APIException.class)
    public Boolean saveOrUpdateAdmin(CustomerDto customerDto) {
        // 判断名字是否重复
        Customer customerTemp = this.getOne(
                Wrappers.<Customer>lambdaQuery()
                        .eq(StringUtils.isNotBlank(customerDto.getName()), Customer::getName, customerDto.getName())
        );
        if (Objects.nonNull(customerTemp)) {
            throw new APIException(APIExceptionCodeType.DATA_HASEXISTS);
        }
        // 执行新增会修改
        Customer customer = customerDtoMapStruct.toEntity(customerDto);
        return this.saveOrUpdate(customer);
    }
}
