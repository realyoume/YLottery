package com.yumi.award.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @author: xk
 * @description 抽奖结果类
 * @date: 2023/7/23 20:05
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DrawResult {
    private Integer userId;

    private Integer activityId;

    private Integer awardId;

    private String awardName;

    private String code;

    private String msg;
}


    