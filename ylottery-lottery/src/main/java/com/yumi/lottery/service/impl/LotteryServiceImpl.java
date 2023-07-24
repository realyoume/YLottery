package com.yumi.lottery.service.impl;

import com.yumi.lottery.domain.strategy.ILotteryStrategy;
import com.yumi.lottery.model.dto.DrawResult;
import com.yumi.lottery.service.ILotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @author: xk
 * @description 抽奖业务层实现类
 * @date: 2023/7/24 20:35
 */

@Service
public class LotteryServiceImpl implements ILotteryService {
    @Autowired
    private ILotteryStrategy flexibleLotteryStrategy;

    @Override
    public DrawResult draw(Integer userId, Integer activityId) {
        return flexibleLotteryStrategy.draw(userId, activityId);
    }
}


    