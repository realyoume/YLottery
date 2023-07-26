package com.yumi.lottery.domain.pick.impl;

import com.yumi.lottery.domain.pick.IPickActivity;
import com.yumi.lottery.model.dto.PickResult;
import com.yumi.lottery.model.entity.Activity;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @author: xk
 * @description 常规活动领取方案
 * @date: 2023/7/26 20:11
 */

@Component
public class CommonPickActivity implements IPickActivity {
    @Override
    public PickResult pick(Integer userId, Integer activityId) {
        return null;
    }

    @Override
    public Activity checkActivityValid(Integer activityId) {
        return null;
    }
}


    