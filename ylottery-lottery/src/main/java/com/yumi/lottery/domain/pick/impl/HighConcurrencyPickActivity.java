package com.yumi.lottery.domain.pick.impl;

import com.alibaba.fastjson.JSON;
import com.yumi.base.exception.YLotteryException;
import com.yumi.lottery.common.enums.PickResultStatus;
import com.yumi.lottery.domain.pick.IPickActivity;
import com.yumi.lottery.domain.process.AbstractLotteryProcess;
import com.yumi.lottery.model.dto.PickResult;
import com.yumi.lottery.model.entity.Activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @author: xk
 * @description 高并发活动领取方案
 * @date: 2023/7/26 20:13
 */

@Component
public class HighConcurrencyPickActivity implements IPickActivity {

    @Autowired
    private RedisTemplate redisTemplate;

    private static Map<Integer, Activity> activityMap = new HashMap<>();

    protected static final Logger logger = LoggerFactory.getLogger(HighConcurrencyPickActivity.class);


    @Override
    public PickResult pick(Integer userId, Integer activityId) {
        Activity activity = checkActivityValid(activityId);

        Long increment = redisTemplate.opsForValue().increment(getActivityCountKey(activityId), 1);

        if (increment > activity.getCapacity()){
            redisTemplate.opsForValue().decrement(getActivityCountKey(activityId), 1);
            logger.info("活动到达最大容量，活动ID：{}，用户ID：{}", activityId, userId);
            redisTemplate.opsForValue().set(getActivityValidKey(activityId), "false");
            return new PickResult(userId, activityId, PickResultStatus.FAILUER.getCode());
        }

        Boolean success = redisTemplate.opsForValue().setIfAbsent(getActivityLockKey(activityId, increment), "lock", 10, TimeUnit.SECONDS);
        if (!success){
            logger.info("活动秒杀抢占锁失效，活动ID：{}，用户ID：{}", activityId, userId);
            return new PickResult(userId, activityId, PickResultStatus.FAILUER.getCode());
        }

        return new PickResult(userId, activityId, PickResultStatus.SUCCESS.getCode());
    }

    @Override
    public Activity checkActivityValid(Integer activityId) {
        String valid = redisTemplate.opsForValue().get(getActivityValidKey(activityId)).toString();
        if(valid == null || valid.equals("false")){
            YLotteryException.cast("活动不在有效期");
        }

        // 双重锁校验，仅从缓存中读取一次
        Activity activity = activityMap.getOrDefault(activityId, null);
        if (activity == null){
            synchronized (this){
                activity = activityMap.getOrDefault(activityId, null);
                if (activity == null){
                    String jsonString = redisTemplate.opsForValue().get(getActivityKey(activityId)).toString();
                    activity = JSON.parseObject(jsonString, Activity.class);
                    activityMap.put(activityId, activity);
                }
            }
        }

        return activity;
    }

    private String getActivityValidKey(Integer activityId){
        return "activity-" + activityId + "-valid";
    }

    private String getActivityKey(Integer activityId){
        return "activity-" + activityId;
    }

    private String getActivityCountKey(Integer activityId){
        return "activity-" + activityId + "-count";
    }

    private String getActivityLockKey(Integer activityId, Long increment){
        return "activity-" + activityId + "-lock-" + increment;
    }

}


    