package com.yumi.activity.service.impl;

import com.yumi.activity.domain.activityStatus.AbstractStatus;
import com.yumi.activity.domain.activityStatus.IStatus;
import com.yumi.activity.domain.activityStatus.StatusFactory;
import com.yumi.activity.mapper.ActivityPublishMapper;
import com.yumi.activity.mapper.ActivityPublishMessageMapper;
import com.yumi.activity.model.entity.ActivityPublish;
import com.yumi.activity.model.entity.ActivityPublishMessage;
import com.yumi.activity.service.ActivityPublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @version 1.0
 * @author: xk
 * @description 活动发布接口实现类
 * @date: 2023/8/3 22:53
 */


public class ActivityPublishServiceImpl implements ActivityPublishService {
    @Autowired
    private ActivityPublishMapper activityPublishMapper;

    @Autowired
    private ActivityPublishMessageMapper activityPublishMessageMapper;

    @Autowired
    private StatusFactory statusFactory;

    @Override
    @Transactional
    public void publish(Integer userId, Integer activityPublishId) {
        // 根据用户ID进行权限检测

        // 查询当前状态
        ActivityPublish activityPublish = activityPublishMapper.selectById(activityPublishId);
        Integer state = activityPublish.getState();
        IStatus status = statusFactory.getStatus(String.valueOf(state));

        // 状态模式进行状态转移
        // 抛出异常则终止方法
        AbstractStatus status2 = status.start();

        activityPublish.setState(status2.getStateCode());

        ActivityPublishMessage activityPublishMessage = ActivityPublishMessage.builder()
                .id(activityPublish.getId())
                .state1(0)
                .state2(0)
                .state3(0)
                .state1ChangeDate(LocalDateTime.now())
                .state2ChangeDate(LocalDateTime.now())
                .state3ChangeDate(LocalDateTime.now())
                .createDate(LocalDateTime.now())
                .build();

        activityPublishMessageMapper.insert(activityPublishMessage);

        activityPublishMapper.updateById(activityPublish);
    }
}


    