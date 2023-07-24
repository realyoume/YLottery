package com.yumi.lottery.model.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @version 1.0
 * @author: xk
 * @description 抽奖结果类
 * @date: 2023/7/23 20:05
 */

@Data
@Builder
public class DrawResult {
    private Integer userId;

    private Integer activityId;

    private Integer awardId;

    private String awardName;

    private String code;

    private String msg;
}


    