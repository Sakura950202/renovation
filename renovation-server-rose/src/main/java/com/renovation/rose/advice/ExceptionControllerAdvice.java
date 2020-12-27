package com.renovation.rose.advice;

import com.renovation.common.enums.ResultVoCodeType;
import com.renovation.common.exception.APIException;
import com.renovation.common.vo.ResultVo;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName: ExceptionControllerAdvice
 * @Description: TODO 全局处理异常
 * @Author: SAKURA
 * @Date: 2020/4/5 22:26
 *
 * 将捕捉的异常信息进行包装之后，再返回给前端
 * 加上这个注解就是全局处理了，返回的是json数据使用RestControllerAdvice，还可以使用ControllerAdvice
 **/

@RestControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * 数据验证的异常处理
     * 使用ExceptionHandler注解可以捕捉想要的异常类型，只要在后面括号配好
     * 返回值也是可以自己定义的，因为已经构建自己想要的返回视图对象，使用自己定义的
     *
     * @param e 异常
     * @return 响应视图对象
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVo<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 获取异常信息
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);

        // 将异常信息组装成视图，返回给前端
        return new ResultVo<>(ResultVoCodeType.VALIDATE_FAILED, objectError.getDefaultMessage());
    }

    /**
     * 自定义接口运行时异常，也可以定义其他的异常
     *
     * @param e 异常
     * @return 响应视图对象
     */
    @ExceptionHandler(APIException.class)
    public ResultVo<String> APIExceptionHandler(APIException e) {
        return new ResultVo<>(ResultVoCodeType.FAILED, e.getMessage());
    }

}
