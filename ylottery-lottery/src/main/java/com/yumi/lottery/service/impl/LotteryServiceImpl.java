package com.yumi.lottery.service.impl;

import com.yumi.lottery.domain.process.ILotteryProcess;
import com.yumi.lottery.domain.process.ProcessFactory;
import com.yumi.lottery.domain.strategy.ILotteryStrategy;
import com.yumi.lottery.model.dto.DrawResult;
import com.yumi.lottery.service.ILotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @author: xk
 * @description 抽奖业务层实现类
 * @date: 2023/7/24 20:35
 */

@Service
public class LotteryServiceImpl implements ILotteryService {
    @Value("${lottery.process-name}")
    private String processName;

    @Autowired
    private ProcessFactory processFactory;

    @Override
    public DrawResult draw(Integer userId, Integer activityId) {
        return processFactory.getLotteryProcess(processName).process(userId, activityId);
    }
}


    