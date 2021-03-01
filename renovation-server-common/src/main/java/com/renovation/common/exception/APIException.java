package com.renovation.common.exception;

import com.renovation.common.enums.APIExceptionCodeType;
import lombok.Getter;

/**
 * @ClassName: APIException
 * @Description: TODO 用来处理程序中的业务异常类
 * @Author: SAKURA
 * @Date: 2020/4/8 22:02
 **/
@Getter
public class APIException extends RuntimeException {

    /**
     * 异常信息
     */
    private String message;

    public APIException(String message) {
        super(message);
    }

    public APIException(APIExceptionCodeType apiExceptionCodeType) {
        this.message = apiExceptionCodeType.getDescribe();
    }

}
