package com.yumi.lottery.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @author: xk
 * @description 领取活动结果
 * @date: 2023/7/26 20:06
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PickResult {

    private Integer userId;

    private Integer activityId;

    private String code;

}


    