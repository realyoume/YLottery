package com.yumi.activity.domain.activityStatus.impl;

import com.yumi.activity.common.enums.ActivityStatusCode;
import com.yumi.activity.domain.activityStatus.AbstractStatus;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @author: xk
 * @description 下架状态
 * @date: 2023/8/3 22:34
 */

@Component
public class Stopped extends AbstractStatus {

    @Override
    public AbstractStatus restart() {
        return new Running();
    }

    @Override
    public Integer getStateCode() {
        return Integer.valueOf(ActivityStatusCode.STOPPED.getCode());
    }
}


    