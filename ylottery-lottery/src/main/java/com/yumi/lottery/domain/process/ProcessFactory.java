package com.yumi.lottery.domain.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @version 1.0
 * @author: xk
 * @description 抽奖过程工程类
 * @date: 2023/7/26 21:22
 */

@Component
public class ProcessFactory {
    @Autowired
    Map<String, ILotteryProcess> lotteryProcessMap;

    public ILotteryProcess getLotteryProcess(String beanName){
        return lotteryProcessMap.get(beanName);
    }
}


    