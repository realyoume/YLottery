package com.yumi.lottery.common.enums;

/**
 * @version 1.0
 * @author: xk
 * @description 抽奖过程类别
 * @date: 2023/8/2 20:01
 */
public enum LotteryProcessType {
    COMMON("100", "commonLotteryProcess"),
    HIGH_CONCURRENCY("200", "highConcurrencyLotteryProcess");


    private String code;

    private String name;

    LotteryProcessType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
