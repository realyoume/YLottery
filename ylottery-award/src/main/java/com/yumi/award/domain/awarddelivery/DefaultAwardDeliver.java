package com.yumi.award.domain.awarddelivery;

import com.yumi.award.common.enums.AwardDeliverType;
import com.yumi.award.domain.awarddelivery.impl.CouponsDeliver;
import com.yumi.award.domain.awarddelivery.impl.ExperienceCardDeliver;
import com.yumi.award.domain.awarddelivery.impl.PhysicalPrizeDeliver;
import com.yumi.award.model.entity.UserAward;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @author: xk
 * @description 奖品分发默认实现类
 * @date: 2023/8/2 20:28
 */

@Component
public class DefaultAwardDeliver implements AwardDeliver{

    Map<String ,AwardDeliver> map;

    public DefaultAwardDeliver(PhysicalPrizeDeliver physicalPrizeDeliver, CouponsDeliver couponsDeliver, ExperienceCardDeliver experienceCardDeliver) {
        map = new HashMap<>();
        map.put(AwardDeliverType.PHYSICAL_PEIZE.getCode(), physicalPrizeDeliver);
        map.put(AwardDeliverType.COUPONS.getCode(), couponsDeliver);
        map.put(AwardDeliverType.EXPERIENCE_CARD.getCode(), experienceCardDeliver);
    }

    @Override
    public boolean deliverAward(UserAward userAward) {
        String awardType = String.valueOf(userAward.getAwardType());

        boolean b = map.get(awardType).deliverAward(userAward);

        return b;
    }
}


    