package com.yumi.award.domain.awarddelivery.impl;

import com.yumi.award.domain.awarddelivery.AwardDeliver;
import com.yumi.award.model.entity.UserAward;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @author: xk
 * @description 体验卡分发
 * @date: 2023/8/2 20:33
 */

@Component
public class ExperienceCardDeliver implements AwardDeliver {
    @Override
    public boolean deliverAward(UserAward userAward) {

        System.out.println("发出体验卡，" + userAward.getUserId());

        return true;
    }
}


    