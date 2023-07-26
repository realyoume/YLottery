package com.yumi.lottery.domain.process;

import com.yumi.lottery.model.dto.DrawResult;

/**
 * @version 1.0
 * @author: xk
 * @description 抽奖流程接口
 * @date: 2023/7/26 19:48
 */
public interface ILotteryProcess {

    DrawResult process(Integer userId, Integer activityId);
}
