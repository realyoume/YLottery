package com.yumi.activity.domain.activityStatus.impl;

import com.yumi.activity.common.enums.ActivityStatusCode;
import com.yumi.activity.domain.activityStatus.AbstractStatus;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @author: xk
 * @description 上架状态
 * @date: 2023/8/3 22:33
 */

@Component
public class Running extends AbstractStatus {

    @Override
    public AbstractStatus stop() {
        return new Stopped();
    }

    @Override
    public Integer getStateCode() {
        return Integer.valueOf(ActivityStatusCode.RUNNING.getCode());
    }

}


    