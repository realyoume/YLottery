package com.yumi.lottery.common.enums;

/**
 * @version 1.0
 * @author: xk
 * @description 抽奖结果枚举类
 * @date: 2023/7/23 20:26
 */
public enum DrawResultStatus {
    SUCCESS("100", "成功中奖"),
    FAILURE("200","未抽中");


    // 状态码
    private String code;

    // 状态信息
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    DrawResultStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
