package com.yumi.award.domain.id;

import com.yumi.award.common.enums.IDGeneratorType;
import com.yumi.award.domain.id.impl.ShortCode;
import com.yumi.award.domain.id.impl.SnowFlake;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @author: xk
 * @description ID生成器MAP
 * @date: 2023/8/1 22:55
 */

@Component
public class IDGeneratorMap {

    Map<String, IDGenerator> stringIDGeneratorMap;

    public IDGeneratorMap(SnowFlake snowFlake, ShortCode shortCode) {
        stringIDGeneratorMap = new HashMap<>();
        stringIDGeneratorMap.put(IDGeneratorType.SNOW_FLAKE.getCode(), snowFlake);
        stringIDGeneratorMap.put(IDGeneratorType.SHORT_CODE.getCode(), shortCode);
    }

    public IDGenerator getIDGenerator(String code){
        return stringIDGeneratorMap.get(code);
    }
}


    