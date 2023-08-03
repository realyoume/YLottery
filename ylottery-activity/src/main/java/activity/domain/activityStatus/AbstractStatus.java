package activity.domain.activityStatus;

import com.yumi.base.exception.YLotteryException;

/**
 * @version 1.0
 * @author: xk
 * @description 活动状态抽象接口
 * @date: 2023/8/3 22:23
 */


public abstract class AbstractStatus {

    /**
     * 提交活动
     * @param status
     * @return
     */
    protected AbstractStatus commit(AbstractStatus status){
        YLotteryException.cast("状态变更异常");
        return null;
    }

    /**
     * 审核通过
     * @param status
     * @return
     */
    protected AbstractStatus approve(AbstractStatus status){
        YLotteryException.cast("状态变更异常");
        return null;
    }

    /**
     * 审核不通过
     * @param status
     * @return
     */
    protected AbstractStatus unapprove(AbstractStatus status){
        YLotteryException.cast("状态变更异常");
        return null;
    }

    /**
     * 上架
     * @param status
     * @return
     */
    protected AbstractStatus start(AbstractStatus status){
        YLotteryException.cast("状态变更异常");
        return null;
    }

    /**
     * 下架
     * @param status
     * @return
     */
    protected AbstractStatus stop(AbstractStatus status){
        YLotteryException.cast("状态变更异常");
        return null;
    }

    /**
     * 重新上架
     * @param status
     * @return
     */
    protected AbstractStatus restart(AbstractStatus status){
        YLotteryException.cast("状态变更异常");
        return null;
    }

}