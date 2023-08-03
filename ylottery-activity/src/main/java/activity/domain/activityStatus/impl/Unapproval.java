package activity.domain.activityStatus.impl;

import activity.domain.activityStatus.AbstractStatus;
import com.yumi.base.exception.YLotteryException;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @author: xk
 * @description 待审核
 * @date: 2023/8/3 22:32
 */

@Component
public class Unapproval extends AbstractStatus {
    @Override
    public AbstractStatus approve(AbstractStatus status) {
        return new Approved();
    }
}


    