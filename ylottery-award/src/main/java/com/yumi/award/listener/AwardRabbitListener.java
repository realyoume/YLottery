package com.yumi.award.listener;

import com.alibaba.fastjson.JSON;
import com.yumi.award.common.enums.IDGeneratorType;
import com.yumi.award.domain.awarddelivery.DefaultAwardDeliver;
import com.yumi.award.domain.id.IDGeneratorMap;
import com.yumi.award.mapper.AwardMapper;
import com.yumi.award.mapper.UserActivityMapper;
import com.yumi.award.mapper.UserAwardMapper;
import com.yumi.award.model.dto.DrawResult;
import com.yumi.award.model.entity.Award;
import com.yumi.award.model.entity.UserActivity;
import com.yumi.award.model.entity.UserAward;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @version 1.0
 * @author: xk
 * @description 奖品消息监听器
 * @date: 2023/8/1 21:02
 */

//@Component
public class AwardRabbitListener {
    @Autowired
    private AwardMapper awardMapper;

    @Autowired
    private UserActivityMapper userActivityMapper;

    @Autowired
    private UserAwardMapper userAwardMapper;

    @Autowired
    private IDGeneratorMap idGeneratorMap;

    @Autowired
    private DefaultAwardDeliver defaultAwardDeliver;

    protected static final Logger logger = LoggerFactory.getLogger(AwardRabbitListener.class);


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "award.queue"),
            exchange = @Exchange(name = "award.topic", type = ExchangeTypes.TOPIC),
            key = "lottery.award"
    ))
    @Transactional
    public void handleAwardMsg(String jsonStr) {
        logger.info("接收到消息：{}", jsonStr);
        DrawResult drawResult = JSON.parseObject(jsonStr, DrawResult.class);

        // 记录用户参加活动
        UserActivity userActivity = UserActivity.builder()
                .id(idGeneratorMap.getIDGenerator(IDGeneratorType.SNOW_FLAKE.getCode()).nextId())
                .userId(drawResult.getUserId())
                .activityId(drawResult.getActivityId())
                .lotteryCount(1)
                .createTime(LocalDateTime.now())
                .build();

        userActivityMapper.insert(userActivity);

        Award award = awardMapper.selectById(drawResult.getAwardId());

        // 记录用户得到奖品
        UserAward userAward = UserAward.builder()
                .id(idGeneratorMap.getIDGenerator(IDGeneratorType.SHORT_CODE.getCode()).nextId() + "-" + drawResult.getUserId() + "-" + RandomStringUtils.randomAlphabetic(4))
                .userId(drawResult.getUserId())
                .userActivityId(userActivity.getId())
                .awardId(award.getId())
                .awardType(award.getAwardType())
                .build();

        userAwardMapper.insert(userAward);

        // 发奖品
        defaultAwardDeliver.deliverAward(userAward);
    }
}


    