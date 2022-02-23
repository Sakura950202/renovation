package com.renovation.common.vo;

import com.renovation.common.enums.ResultVoCode;
import lombok.Data;

/**
 * @ClassName: SimpleVo
 * @Description: TODO 统一数据响应体视图对象
 * @Author: SAKURA
 * @Date: 2020/4/5 17:25
 **/
@Data
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

    public ResultVo() {}

    public ResultVo(T data) {
        this.data = data;
    }

    public ResultVo(ResultVoCode resultVoCode, T data) {
        this.code = resultVoCode.getCode();
        this.message = resultVoCode.getDescribe();
        this.data = data;
    }

}
