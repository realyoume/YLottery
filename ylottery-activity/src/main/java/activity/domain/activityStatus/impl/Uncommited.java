package activity.domain.activityStatus.impl;

import activity.domain.activityStatus.AbstractStatus;
import com.yumi.base.exception.YLotteryException;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @author: xk
 * @description 未提交状态
 * @date: 2023/8/3 22:31
 */

@Component
public class Uncommited extends AbstractStatus {
    @Override
    public AbstractStatus commit(AbstractStatus status) {
        return new Unapproval();
    }
}


    