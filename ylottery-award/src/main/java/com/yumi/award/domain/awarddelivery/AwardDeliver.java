package com.yumi.award.domain.awarddelivery;

import com.yumi.award.model.entity.UserAward;

/**
 * @version 1.0
 * @author: xk
 * @description 奖品分发接口
 * @date: 2023/8/2 20:25
 */
public interface AwardDeliver {
    boolean deliverAward(UserAward userAward);
}
