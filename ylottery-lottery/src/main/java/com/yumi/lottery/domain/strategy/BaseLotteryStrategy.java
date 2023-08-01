package com.yumi.lottery.domain.strategy;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yumi.base.exception.YLotteryException;
import com.yumi.lottery.common.enums.DrawResultStatus;
import com.yumi.lottery.mapper.ActivityMapper;
import com.yumi.lottery.mapper.StrategyDetailMapper;
import com.yumi.lottery.mapper.UserActivityMapper;
import com.yumi.lottery.model.dto.AwardInfo;
import com.yumi.lottery.model.dto.DrawResult;
import com.yumi.lottery.model.entity.Activity;
import com.yumi.lottery.model.entity.StrategyDetail;
import com.yumi.lottery.model.entity.UserActivity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @version 1.0
 * @author: xk
 * @description 抽奖策略抽象实现类
 * @date: 2023/7/23 20:19
 */


public abstract class BaseLotteryStrategy implements ILotteryStrategy {
    @Autowired
    protected ActivityMapper activityMapper;

    @Autowired
    protected UserActivityMapper userActivityMapper;

    @Autowired
    private StrategyDetailMapper strategyDetailMapper;

    /**
     * 斐波那契散列增量，逻辑：黄金分割点：(√5 - 1) / 2 = 0.6180339887，Math.pow(2, 32) * 0.6180339887 = 0x61c88647
     */
    private final int HASH_INCREMENT = 0x61c88647;

    /**
     * 数组初始化长度 128，保证数据填充时不发生碰撞的最小初始化值
     */
    private final int RATE_TUPLE_LENGTH = 128;

    private static final Logger logger = LoggerFactory.getLogger(BaseLotteryStrategy.class);

    @Override
    public DrawResult draw(Integer userId, Integer activityId) {
        //logger.info("开始抽奖，用户ID：{}，活动ID：{}", userId, activityId);

        // 检查资格
        // 废弃，检查放在领取活动时
        //checkValid(userId, activityId);

        // 抽奖
        AwardInfo awardInfo = getAward(activityId);

        // 包装结果
        return generateResult(userId,activityId, awardInfo);
    }

    protected DrawResult generateResult(Integer userId,Integer activityId, AwardInfo awardInfo) {
        if (awardInfo == null) {
            return DrawResult.builder()
                    .userId(userId)
                    .awardId(null)
                    .awardName(null)
                    .activityId(activityId)
                    .code(DrawResultStatus.FAILURE.getCode())
                    .msg("可惜了，奖品没了")
                    .build();
        }else {
            return DrawResult.builder()
                    .userId(userId)
                    .awardId(awardInfo.getAwardId())
                    .awardName(awardInfo.getAwardName())
                    .activityId(activityId)
                    .code(DrawResultStatus.SUCCESS.getCode())
                    .msg("恭喜中奖")
                    .build();
        }
    }

    private void checkValid(Integer userId, Integer activityId) {
        // 活动是否在时间内
        Activity activity = activityMapper.selectById(activityId);
        if (!LocalDateTime.now().isAfter(activity.getValidTime())
                || !LocalDateTime.now().isBefore(activity.getExpireTime())){
            YLotteryException.cast("不在活动时间内");
        }

        // 用户是否持有该活动
        LambdaQueryWrapper<UserActivity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserActivity::getUserId, userId).eq(UserActivity::getActivityId, activityId);
        UserActivity userActivity = userActivityMapper.selectOne(queryWrapper);

        if (userActivity == null){
            YLotteryException.cast("无活动权限");
        }

        // 用户的抽奖次数是否大于0
        if (userActivity.getLotteryCount() <= 0){
            YLotteryException.cast("抽奖次数为空");
        }
    }

    private boolean decrStock(Integer activityId, Integer awardId) {
        Activity activity = activityMapper.selectById(activityId);
        Integer strategyId = activity.getActivityStrategyId();
        LambdaQueryWrapper<StrategyDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StrategyDetail::getStrategyId, strategyId).eq(StrategyDetail::getAwardId, awardId);
        StrategyDetail strategyDetail = strategyDetailMapper.selectOne(queryWrapper);

        Integer awardStock = strategyDetail.getAwardStock();
        if (awardStock <= 0){
            return false;
        }

        strategyDetail.setAwardStock(awardStock-1);
        strategyDetailMapper.updateById(strategyDetail);

        return true;
    }

    protected AwardInfo getAward(Integer activityId) {
        Map<Integer, Integer> awardPool = getAwardPool(activityId);

        int val = (int) Math.floor(Math.random() * 100);

        Integer awardId = awardPool.get(hashIndex(val));

        boolean b = decrStock(activityId, awardId);
        if (!b){
            return null;
        }

        LambdaQueryWrapper<StrategyDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StrategyDetail::getAwardId, awardId);
        StrategyDetail strategyDetail = strategyDetailMapper.selectOne(queryWrapper);

        return AwardInfo.builder()
                .awardName(strategyDetail.getAwardName())
                .awardId(awardId)
                .build();
    }

    protected abstract Map<Integer, Integer> getAwardPool(Integer activityId);

    protected int hashIndex(int val){
        int hashCode = val * HASH_INCREMENT + HASH_INCREMENT;
        return hashCode & (RATE_TUPLE_LENGTH - 1);
    }
}


    