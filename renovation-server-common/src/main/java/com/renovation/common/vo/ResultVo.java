package com.renovation.common.vo;

import com.renovation.common.enums.ResultVoCodeType;
import lombok.Getter;

/**
 * @ClassName: SimpleVo
 * @Description: TODO 数据响应体视图对象
 * @Author: SAKURA
 * @Date: 2020/4/5 17:25
 **/
@Getter
public class ResultVo<T> {

    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    public ResultVo(T data) {
        this.data = data;
    }

    public ResultVo(ResultVoCodeType resultVoCodeType, T data) {
        this.code = resultVoCodeType.getCode();
        this.message = resultVoCodeType.getDescribe();
        this.data = data;
    }

}
