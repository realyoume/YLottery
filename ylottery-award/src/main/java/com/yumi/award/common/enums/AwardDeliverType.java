package com.yumi.award.common.enums;

/**
 * @version 1.0
 * @author: xk
 * @description 奖品分发策略类别
 * @date: 2023/8/2 20:33
 */
public enum AwardDeliverType {
    PHYSICAL_PEIZE("1", "physicalPrizeDeliver"),
    COUPONS("2", "couponsDeliver"),
    EXPERIENCE_CARD("3", "experienceCardDeliver");

    private String code;

    private String name;

    AwardDeliverType(String code, String name) {
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
