package com.yumi.lottery.domain.pick;

import com.yumi.lottery.model.dto.PickResult;
import com.yumi.lottery.model.entity.Activity;

/**
 * @version 1.0
 * @author: xk
 * @description 领取活动接口
 * @date: 2023/7/26 20:06
 */
public interface IPickActivity {

    PickResult pick(Integer userId, Integer activityId);

    Activity checkActivityValid(Integer activityId);
}
