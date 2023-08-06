package com.yumi.activity.domain.activityStatus;

import com.yumi.base.exception.YLotteryException;

/**
 * @version 1.0
 * @author: xk
 * @description 活动状态接口
 * @date: 2023/8/6 12:23
 */
public interface IStatus {
    /**
     * 提交活动
     * @return
     */
    AbstractStatus commit();

    /**
     * 审核通过
     * @return
     */
    AbstractStatus approve();

    /**
     * 审核不通过
     * @return
     */
    AbstractStatus unapprove();

    /**
     * 上架
     * @return
     */
    AbstractStatus start();

    /**
     * 下架
     * @return
     */
    AbstractStatus stop();

    /**
     * 重新上架
     * @return
     */
    AbstractStatus restart();

    Integer getStateCode();
}
