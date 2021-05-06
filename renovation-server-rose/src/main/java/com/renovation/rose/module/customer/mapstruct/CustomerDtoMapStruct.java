package com.renovation.rose.module.customer.mapstruct;

import com.renovation.common.config.BaseMapStruct;
import com.renovation.rose.module.customer.dto.CustomerDto;
import com.renovation.rose.module.customer.entity.Customer;
import org.mapstruct.Mapper;

/**
 * @ClassName CustomerDtoMapStruct
 * @Description TODO
 * @Author SAKURA
 * @Date 2020/12/27 15:23
 * @Version 1.0
 */
@Mapper(componentModel = "spring")
public interface CustomerDtoMapStruct extends BaseMapStruct<CustomerDto, Customer> {
}
