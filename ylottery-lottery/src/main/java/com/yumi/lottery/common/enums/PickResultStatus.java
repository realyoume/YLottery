package com.yumi.lottery.common.enums;

/**
 * @version 1.0
 * @author: xk
 * @description 领取活动结果状态码
 * @date: 2023/7/26 20:08
 */
public enum PickResultStatus {
    SUCCESS("100"),
    FAILUER("200");


    private String code;

    PickResultStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
