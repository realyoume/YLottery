package com.yumi.lottery.domain.strategy.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yumi.lottery.domain.strategy.BaseLotteryStrategy;
import com.yumi.base.exception.YLotteryException;
import com.yumi.lottery.mapper.ActivityMapper;
import com.yumi.lottery.mapper.StrategyDetailMapper;
import com.yumi.lottery.model.entity.Activity;
import com.yumi.lottery.model.entity.StrategyDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author: xk
 * @description 弹性概率抽奖策略
 * @date: 2023/7/23 20:12
 */

@Component
public class FlexibleLotteryStrategy extends BaseLotteryStrategy {
    @Autowired
    private StrategyDetailMapper strategyDetailMapper;

    @Autowired
    private ActivityMapper activityMapper;


    @Override
    protected Map<Integer, Integer> getAwardPool(Integer activityId) {
        return initAwardPool(activityId);
    }

    private Map<Integer, Integer> initAwardPool(Integer activityId) {
        Map<Integer, Integer> awardPool = new HashMap<>();

        Activity activity = activityMapper.selectById(activityId);
        Integer strategyId = activity.getActivityStrategyId();
        LambdaQueryWrapper<StrategyDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StrategyDetail::getStrategyId, strategyId);
        List<StrategyDetail> strategyDetails = strategyDetailMapper.selectList(queryWrapper);

        double totalRate = 0;
        for (StrategyDetail strategyDetail : strategyDetails) {
            if (strategyDetail.getAwardStock() != 0){
                totalRate += strategyDetail.getAwardRate().doubleValue();
            }
        }

        if (totalRate == 0){
            YLotteryException.cast("活动奖品已为空");
        }

        int cursor = 0;
        for (StrategyDetail strategyDetail : strategyDetails) {
            if (strategyDetail.getAwardStock() > 0){
                Integer awardId = strategyDetail.getAwardId();
                int count = strategyDetail.getAwardRate().divide(new BigDecimal(totalRate), 2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).intValue();

                for (int i = cursor; i < cursor + count; ++i) {
                    awardPool.put(hashIndex(i), awardId);
                }

                cursor += count;
            }
        }


        return awardPool;
    }
}


    