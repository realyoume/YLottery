package com.yumi.lottery.domain.strategy.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yumi.lottery.domain.strategy.BaseLotteryStrategy;
import com.yumi.lottery.mapper.ActivityMapper;
import com.yumi.lottery.mapper.StrategyDetailMapper;
import com.yumi.lottery.model.entity.Activity;
import com.yumi.lottery.model.entity.StrategyDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 1.0
 * @author: xk
 * @description 固定概率抽奖策略
 * @date: 2023/7/23 20:12
 */

@Component
public class FixedLotteryStrategy extends BaseLotteryStrategy {
    @Autowired
    private StrategyDetailMapper strategyDetailMapper;

    @Autowired
    private ActivityMapper activityMapper;

    private Map<Integer, Map<Integer, Integer>> awardPoolMap = new ConcurrentHashMap<>();

    @Override
    protected Map<Integer, Integer> getAwardPool(Integer activityId) {
        Map<Integer, Integer> map = awardPoolMap.get(activityId);
        if (map == null){
            map = initAwardPool(activityId);
            awardPoolMap.put(activityId, map);
        }

        return map;
    }


    private Map<Integer, Integer> initAwardPool(Integer activityId){
        Map<Integer, Integer> awardPool = new ConcurrentHashMap<>();

        Activity activity = activityMapper.selectById(activityId);

        Integer strategyId = activity.getActivityStrategyId();

        LambdaQueryWrapper<StrategyDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StrategyDetail::getStrategyId, strategyId);
        List<StrategyDetail> strategyDetails = strategyDetailMapper.selectList(queryWrapper);

        int cursor = 0;
        for (StrategyDetail strategyDetail : strategyDetails) {
            Integer awardId = strategyDetail.getAwardId();
            int count = strategyDetail.getAwardRate().multiply(new BigDecimal(100)).intValue();

            for (int i = cursor; i < cursor + count; ++i) {
                awardPool.put(hashIndex(i), awardId);
            }

            cursor += count;
        }

        return awardPool;
    }


}


    