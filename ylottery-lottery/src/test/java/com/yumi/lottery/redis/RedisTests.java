package com.yumi.lottery.redis;

import com.yumi.lottery.mapper.ActivityMapper;
import com.yumi.lottery.model.entity.Activity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @version 1.0
 * @author: xk
 * @description TODO
 * @date: 2023/7/31 20:13
 */

@SpringBootTest
public class RedisTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ActivityMapper activityMapper;

    @Test
    public void addActivityValid(){
        Integer activityId = 1;

        redisTemplate.opsForValue().set(getActivityValidKey(activityId), "true");
    }

    @Test
    public void addActivity(){
        Integer activityId = 1;

        Activity activity = activityMapper.selectById(activityId);

        redisTemplate.opsForValue().set(getActivityKey(activityId), activity);
    }

    @Test
    public void addActivityCount(){
        Integer activityId = 1;

        redisTemplate.opsForValue().set(getActivityCountKey(activityId), 0);
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


    