package com.yumi.activity.domain.activityStatus.impl;

import com.yumi.activity.common.enums.ActivityStatusCode;
import com.yumi.activity.domain.activityStatus.AbstractStatus;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @author: xk
 * @description 未提交状态
 * @date: 2023/8/3 22:31
 */

@Component
public class Uncommited extends AbstractStatus {
    @Override
    public AbstractStatus commit() {
        return new Unapproval();
    }

    @Override
    public Integer getStateCode() {
        return Integer.valueOf(ActivityStatusCode.UNCOMMITED.getCode());
    }
}


    