package com.renovation.common.enums;

import lombok.Getter;

/**
 * @ClassName: APIExceptionCodeType
 * @Description: TODO 业务异常信息状态类型
 * @Author: SAKURA
 * @Date: 2020/4/13 20:43
 **/
@Getter
public enum APIExceptionCode {

    /**
     * 数据未找到
     */
    DATA_NOT_FOUND("数据未找到！"),

    /**
     * 数据新增失败
     */
    DATA_HAS_EXISTS("数据新增失败："),

    /**
     * 数据删除失败
     */
    DATA_DELETE_FAIL("数据删除失败！"),

    /**
     * 数据修改失败
     */
    DATA_UPDATE_FAIL("数据修改失败：");

    /**
     * 描述
     */
    private String describe;

    APIExceptionCode(String describe) {
        this.describe = describe;
    }
}
