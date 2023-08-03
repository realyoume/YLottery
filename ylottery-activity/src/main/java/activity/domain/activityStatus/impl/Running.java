package activity.domain.activityStatus.impl;

import activity.domain.activityStatus.AbstractStatus;
import ch.qos.logback.classic.pattern.Abbreviator;
import com.yumi.base.exception.YLotteryException;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @author: xk
 * @description 上架状态
 * @date: 2023/8/3 22:33
 */

@Component
public class Running extends AbstractStatus {

    @Override
    public AbstractStatus stop(AbstractStatus status) {
        return new Stopped();
    }

}


    