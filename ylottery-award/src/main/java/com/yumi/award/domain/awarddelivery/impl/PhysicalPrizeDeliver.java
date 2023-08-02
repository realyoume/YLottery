package com.yumi.award.domain.awarddelivery.impl;

import com.yumi.award.domain.awarddelivery.AwardDeliver;
import com.yumi.award.model.entity.UserAward;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @author: xk
 * @description 实物奖品分发
 * @date: 2023/8/2 20:30
 */

@Component
public class PhysicalPrizeDeliver implements AwardDeliver {
    @Override
    public boolean deliverAward(UserAward userAward) {

        System.out.println("发出实物奖品，" + userAward.getUserId());

        return true;
    }
}


    