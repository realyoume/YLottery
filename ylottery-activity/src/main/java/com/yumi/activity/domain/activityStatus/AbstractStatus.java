package com.yumi.activity.domain.activityStatus;

import com.yumi.base.exception.YLotteryException;

/**
 * @version 1.0
 * @author: xk
 * @description 活动状态抽象接口
 * @date: 2023/8/3 22:23
 */


public abstract class AbstractStatus implements IStatus {

    /**
     * 提交活动
     * @return
     */
    public AbstractStatus commit(){
        YLotteryException.cast("状态变更异常");
        return null;
    }

    /**
     * 审核通过
     * @return
     */
    public AbstractStatus approve(){
        YLotteryException.cast("状态变更异常");
        return null;
    }

    /**
     * 审核不通过
     * @return
     */
    public AbstractStatus unapprove(){
        YLotteryException.cast("状态变更异常");
        return null;
    }

    /**
     * 上架
     * @return
     */
    public AbstractStatus start(){
        YLotteryException.cast("状态变更异常");
        return null;
    }

    /**
     * 下架
     * @return
     */
    public AbstractStatus stop(){
        YLotteryException.cast("状态变更异常");
        return null;
    }

    /**
     * 重新上架
     * @return
     */
    public AbstractStatus restart(){
        YLotteryException.cast("状态变更异常");
        return null;
    }

}