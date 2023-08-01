package com.yumi.base.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @version 1.0
 * @author: xk
 * @description 返回前端的异常类型
 * @date: 2023/7/24 22:24
 */

public class RestErrorResult implements Serializable {
    private String errMsg;

    private LocalDateTime time;

    public RestErrorResult(String errMsg) {
        this.errMsg = errMsg;
        this.time = LocalDateTime.now();
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}


    