package com.yumi.base.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;



/**
 * @version 1.0
 * @author: xk
 * @description 全局异常处理器
 * @date: 2023/7/24 22:25
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(YLotteryException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResult customException(YLotteryException e){
        String errMsg = e.getErrMsg();
        RestErrorResult restErrorResult = new RestErrorResult(errMsg);

        return restErrorResult;
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResult exception(Exception e){
        String errMsg = e.getMessage();
        RestErrorResult restErrorResult = new RestErrorResult(errMsg);

        return restErrorResult;
    }
}