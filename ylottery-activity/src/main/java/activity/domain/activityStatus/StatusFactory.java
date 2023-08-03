package activity.domain.activityStatus;

import activity.common.enums.ActivityStatusCode;
import activity.domain.activityStatus.impl.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @author: xk
 * @description 活动工厂类，用于通过状态码获得状态
 * @date: 2023/8/3 22:48
 */

@Component
public class StatusFactory {

    private Map<String , AbstractStatus> map;

    public StatusFactory(Uncommited uncommited, Unapproval unapproval, Approved approved, Running running, Stopped stopped){
        map = new HashMap<>();
        map.put(ActivityStatusCode.UNCOMMITED.getCode(), uncommited);
        map.put(ActivityStatusCode.UNAPPROVAL.getCode(), unapproval);
        map.put(ActivityStatusCode.APPROVED.getCode(), approved);
        map.put(ActivityStatusCode.RUNNING.getCode(), running);
        map.put(ActivityStatusCode.STOPPED.getCode(), stopped);
    }

    public AbstractStatus getStatus(String code){
        return map.get(code);
    }
}


    