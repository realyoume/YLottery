package com.yumi.activity.domain.activityStatus.impl;

import com.yumi.activity.common.enums.ActivityStatusCode;
import com.yumi.activity.domain.activityStatus.AbstractStatus;
import com.yumi.base.exception.YLotteryException;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @author: xk
 * @description 审核通过
 * @date: 2023/8/3 22:32
 */

@Component
public class Approved extends AbstractStatus {

    @Override
    public AbstractStatus start() {
        return new Running();
    }

    @Override
    public Integer getStateCode() {
        return Integer.valueOf(ActivityStatusCode.APPROVED.getCode());
    }
}


    