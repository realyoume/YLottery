package com.yumi.lottery.domain.process.impl;

import com.yumi.lottery.domain.pick.IPickActivity;
import com.yumi.lottery.domain.process.AbstractLotteryProcess;
import com.yumi.lottery.domain.strategy.ILotteryStrategy;
import com.yumi.lottery.model.dto.DrawResult;
import com.yumi.lottery.model.dto.PickResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @author: xk
 * @description 常规抽奖流程
 * @date: 2023/7/26 20:34
 */
@Component
public class CommonLotteryProcess extends AbstractLotteryProcess {

    @Autowired
    @Qualifier("commonPickActivity")
    private IPickActivity pickActivity;

    @Autowired
    @Qualifier("flexibleLotteryStrategy")
    private ILotteryStrategy lotteryStrategy;

    @Override
    protected PickResult doPickActivity(Integer userId, Integer activityId) {
        return pickActivity.pick(userId, activityId);
    }

    @Override
    protected DrawResult doLottery(Integer userId, Integer activityId) {
        return lotteryStrategy.draw(userId, activityId);
    }
}


    