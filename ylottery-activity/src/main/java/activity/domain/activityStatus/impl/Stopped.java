package activity.domain.activityStatus.impl;

import activity.domain.activityStatus.AbstractStatus;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @author: xk
 * @description 下架状态
 * @date: 2023/8/3 22:34
 */

@Component
public class Stopped extends AbstractStatus {

    @Override
    public AbstractStatus restart(AbstractStatus status) {
        return new Running();
    }
}


    