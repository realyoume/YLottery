package com.yumi.lottery.domain.process;


import com.alibaba.fastjson.JSON;
import com.yumi.base.exception.YLotteryException;
import com.yumi.lottery.common.enums.DrawResultStatus;
import com.yumi.lottery.common.enums.PickResultStatus;
import com.yumi.lottery.model.dto.DrawResult;
import com.yumi.lottery.model.dto.PickResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @version 1.0
 * @author: xk
 * @description 抽象抽奖过程类
 * @date: 2023/7/26 19:52
 */


public abstract class AbstractLotteryProcess implements ILotteryProcess {

    protected static final Logger logger = LoggerFactory.getLogger(AbstractLotteryProcess.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 抽奖过程模版方法
     *
     * @param userId 用户ID
     * @param activityId 活动ID
     * @return 抽奖结果
     */
    @Override
    public DrawResult process(Integer userId, Integer activityId) {
        // 领取活动
        PickResult pickResult = doPickActivity(userId, activityId);
        if (PickResultStatus.FAILUER.getCode().equals(pickResult.getCode())){
           // logger.info("活动领取失败，用户ID：{}，活动ID：{}", userId, activityId);
            YLotteryException.cast("手慢了，活动已经结束");
        }

        // 若成功领取，参加抽奖
        DrawResult drawResult = doLottery(userId, activityId);
        if (DrawResultStatus.FAILURE.getCode().equals(drawResult.getCode())){
            logger.info("未抽中奖品，用户ID：{}，活动ID：{}", userId, activityId);
            YLotteryException.cast("真可惜，未抽中奖品");
        }

        // 发送消息，记录抽奖结果
        sendSuccessMsg(drawResult);

        return drawResult;
    }

    protected abstract PickResult doPickActivity(Integer userId, Integer activityId);

    protected abstract DrawResult doLottery(Integer userId, Integer activityId);

    /**
     * 发送中奖信息
     * 通过消息队列发送消息形成解耦
     *
     * @param drawResult 抽奖结果
     */
    protected void sendSuccessMsg(DrawResult drawResult){
        // TODO 实现消息队列
        logger.info("恭喜中奖，用户ID：{}，奖品：{}",drawResult.getUserId(), drawResult.getAwardName());

        String exchangeName = "award.topic";
        String topicName = "lottery.award";
        String jsonStr = JSON.toJSONString(drawResult);

        rabbitTemplate.convertAndSend(exchangeName, topicName, jsonStr);
    }
}


    