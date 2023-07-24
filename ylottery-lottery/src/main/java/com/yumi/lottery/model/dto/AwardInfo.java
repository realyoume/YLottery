package com.yumi.lottery.model.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @version 1.0
 * @author: xk
 * @description 奖品信息
 * @date: 2023/7/24 21:41
 */

@Data
@Builder
public class AwardInfo {
    private Integer awardId;

    private String awardName;
}


    