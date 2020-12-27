package com.renovation.rose.module.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName CustomerDto
 * @Description TODO
 * @Author SAKURA
 * @Date 2020/12/27 15:21
 * @Version 1.0
 */
@Data
@ApiModel("会员入参对象")
public class CustomerDto {

    private Integer id;

    @ApiModelProperty("管理员姓名")
    @NotBlank(message = "管理员姓名不能为空")
    private String name;

}
