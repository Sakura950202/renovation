package com.renovation.gardenia.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName AdminDto
 * @Description TODO 管理员入参对象
 * @Author SAKURA
 * @Date 2020/12/09 17:09
 * @Version 1.0
 */
@Data
@ApiModel("管理员入参对象")
public class AdminDto {

    private Integer id;

    @ApiModelProperty("管理员姓名")
    @NotBlank(message = "管理员姓名不能为空")
    private String name;

}
