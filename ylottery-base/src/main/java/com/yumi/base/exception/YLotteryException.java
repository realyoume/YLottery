package com.yumi.base.exception;

/**
 * @version 1.0
 * @author: xk
 * @description 项目自定义异常
 * @date: 2023/7/24 22:21
 */


public class YLotteryException extends RuntimeException {

    private String errMsg;

    public YLotteryException() {
    }

    public YLotteryException(String message) {
        super(message);
        this.errMsg = message;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public static void cast(String errMsg){
        throw new YLotteryException(errMsg);
    }
}


    