package com.yumi.lottery.domain.pick.impl;

import com.yumi.base.exception.YLotteryException;
import com.yumi.lottery.common.enums.ActivityStatus;
import com.yumi.lottery.common.enums.PickResultStatus;
import com.yumi.lottery.domain.pick.IPickActivity;
import com.yumi.lottery.mapper.ActivityMapper;
import com.yumi.lottery.mapper.UserActivityMapper;
import com.yumi.lottery.model.dto.PickResult;
import com.yumi.lottery.model.entity.Activity;
import com.yumi.lottery.model.entity.UserActivity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @version 1.0
 * @author: xk
 * @description 常规活动领取方案
 * @date: 2023/7/26 20:11
 */

@Component
public class CommonPickActivity implements IPickActivity {

    protected static final Logger logger = LoggerFactory.getLogger(CommonPickActivity.class);

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private UserActivityMapper userActivityMapper;


    @Override
    @Transactional
    public synchronized PickResult pick(Integer userId, Integer activityId) {
        Activity activity = checkActivityValid(activityId);

        activity.setCapacity(activity.getCapacity() - 1);

        activityMapper.updateById(activity);

        UserActivity userActivity = UserActivity.builder()
                .id(userId + "-" + activityId + "-" + System.currentTimeMillis())
                .activityId(activityId)
                .userId(userId)
                .createTime(LocalDateTime.now())
                .lotteryCount(1)
                .build();

        userActivityMapper.insert(userActivity);

        PickResult pickResult = PickResult.builder()
                .activityId(activityId)
                .userId(userId)
                .code(PickResultStatus.SUCCESS.getCode())
                .build();

        return pickResult;
    }

    @Override
    public Activity checkActivityValid(Integer activityId) {
        Activity activity = activityMapper.selectById(activityId);

        if (ActivityStatus.CAPACITY_OUT.getCode().equals(activity.getStatus())
                || ActivityStatus.ENDING.getCode().equals(activity.getStatus())) {
            YLotteryException.cast("活动已结束");
        }

        if (activity.getValidTime().isAfter(LocalDateTime.now())) {
            logger.info("活动未开始，活动ID：{}", activity);
            YLotteryException.cast("活动未开始");
        }

        if (activity.getExpireTime().isBefore(LocalDateTime.now())) {
            activity.setStatus(ActivityStatus.ENDING.getCode());
            activityMapper.updateById(activity);

            logger.info("活动已结束，活动ID：{}", activity);
            YLotteryException.cast("活动已结束");
        }

        if (activity.getCapacity() <= 0) {
            activity.setStatus(ActivityStatus.CAPACITY_OUT.getCode());
            activityMapper.updateById(activity);

            logger.info("活动到达最高容量，活动ID：{}", activity);
            YLotteryException.cast("活动到达最高容量");
        }

        return activity;
    }
}


    