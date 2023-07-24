package com.yumi.lottery.service;

import com.yumi.lottery.model.dto.DrawResult;

/**
 * @version 1.0
 * @author: xk
 * @description 抽奖业务层接口
 * @date: 2023/7/24 20:33
 */
public interface ILotteryService {

    DrawResult draw(Integer userId, Integer activityId);
}
