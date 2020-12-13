package com.renovation.common.enums;

import lombok.Getter;

/**
 * @ClassName: APIExceptionCodeType
 * @Description: TODO 业务异常信息状态类型
 * @Author: SAKURA
 * @Date: 2020/4/13 20:43
 * @Version 1.0
 **/
@Getter
public enum APIExceptionCodeType {

    /**
     * 数据未找到
     */
    DATA_NOTFOUND("数据未找到!"),

    /**
     * 数据已存在
     */
    DATA_HASEXISTS("数据已存在!"),

    /**
     * 数据不能删除
     */
    DATA_NOTDELETE("数据不能删除!"),

    /**
     * 数据不能修改
     */
    DATA_NOTUPDATE("数据不能修改"),

    ;

    /**
     * 描述
     */
    private String describe;

    APIExceptionCodeType(String describe) {
        this.describe = describe;
    }
}
