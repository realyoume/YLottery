package com.yumi.lottery.common.enums;

/**
 * @version 1.0
 * @author: xk
 * @description 活动状态类
 * @date: 2023/7/26 22:39
 */
public enum ActivityStatus {
    NOT_START("0", "活动未开始"),
    RUNNING("1", "活动进行中"),
    CAPACITY_OUT("2", "活动到达最大容量"),
    ENDING("3", "活动已过期");

    private String code;

    private String msg;

    ActivityStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
