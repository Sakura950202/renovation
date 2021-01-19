package com.renovation.common.enums;

import lombok.Getter;

/**
 * @ClassName: APIExceptionCodeType
 * @Description: TODO 业务异常信息状态类型
 * @Author: SAKURA
 * @Date: 2020/4/13 20:43
 **/
@Getter
public enum APIExceptionCodeType {

    /**
     * 数据未找到
     */
    DATA_NOT_FOUND("数据未找到!"),

    /**
     * 数据已存在
     */
    DATA_HAS_EXISTS("数据已存在!"),

    /**
     * 数据不能删除
     */
    DATA_NOT_DELETE("数据不能删除!"),

    /**
     * 数据不能修改
     */
    DATA_NOT_UPDATE("数据不能修改");

    /**
     * 描述
     */
    private String describe;

    APIExceptionCodeType(String describe) {
        this.describe = describe;
    }
}
