package com.yumi.base.exception;

import java.io.Serializable;

/**
 * @version 1.0
 * @author: xk
 * @description 返回前端的异常类型
 * @date: 2023/7/24 22:24
 */

public class RestErrorResult implements Serializable {
    private String errMsg;

    public RestErrorResult(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}


    