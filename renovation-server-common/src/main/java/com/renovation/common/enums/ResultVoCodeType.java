package com.renovation.common.enums;

import lombok.Getter;

/**
 * @ClassName: ResultVoCodeType
 * @Description: TODO 数据响应体视图对象状态类型
 * @Author: SAKURA
 * @Date: 2020/4/5 17:29
 **/
@Getter
public enum ResultVoCodeType {

    /**
     * 响应成功
     */
    SUCCESS(200, "success"),

    /**
     * 响应失败
     */
    FAILED(500, "failed"),

    /**
     * 数据校验失败
     */
    VALIDATE_FAILED(600, "validateFailed");

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 描述
     */
    private String describe;

    ResultVoCodeType(Integer code, String describe) {
        this.code = code;
        this.describe = describe;
    }

}
