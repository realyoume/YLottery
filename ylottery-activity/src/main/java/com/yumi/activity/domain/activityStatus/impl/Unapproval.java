package com.yumi.activity.domain.activityStatus.impl;

import com.yumi.activity.common.enums.ActivityStatusCode;
import com.yumi.activity.domain.activityStatus.AbstractStatus;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @author: xk
 * @description 待审核
 * @date: 2023/8/3 22:32
 */

@Component
public class Unapproval extends AbstractStatus {
    @Override
    public AbstractStatus approve() {
        return new Approved();
    }

    @Override
    public Integer getStateCode() {
        return Integer.valueOf(ActivityStatusCode.UNAPPROVAL.getCode());
    }
}


    