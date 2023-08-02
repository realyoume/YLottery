package com.yumi.award.domain.id.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import com.yumi.award.domain.id.IDGenerator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


/**
 * @version 1.0
 * @author: xk
 * @description 雪花算法生成ID
 * @date: 2023/8/1 22:38
 */

@Component
public class SnowFlake implements IDGenerator {

    private Snowflake snowflake;

    @PostConstruct
    public void init() {
        // 0 ~ 31 位，可以采用配置的方式使用
        long workerId;
        try {
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        } catch (Exception e) {
            workerId = NetUtil.getLocalhostStr().hashCode();
        }

        workerId = workerId >> 16 & 31;

        long dataCenterId = 1L;
        snowflake = IdUtil.createSnowflake(workerId, dataCenterId);
    }

    @Override
    public synchronized String nextId() {
        return String.valueOf(snowflake.nextId());
    }


}


    