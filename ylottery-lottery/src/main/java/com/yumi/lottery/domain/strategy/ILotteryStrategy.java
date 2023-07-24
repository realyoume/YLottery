package com.yumi.lottery.domain.strategy;

import com.yumi.lottery.model.dto.DrawResult;

/**
 * @version 1.0
 * @author: xk
 * @description 抽奖策略接口
 * @date: 2023/7/23 20:04
 */
public interface ILotteryStrategy {

    /**
     * 抽奖接口
     * @param userId 用户id
     * @param activityId 活动id
     * @return 抽奖结果
     */
    DrawResult draw(Integer userId, Integer activityId);
}
