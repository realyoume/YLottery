package com.yumi.lottery.domain.process;

import com.yumi.lottery.common.enums.LotteryProcessType;
import com.yumi.lottery.domain.process.impl.CommonLotteryProcess;
import com.yumi.lottery.domain.process.impl.HighConcurrencyLotteryProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @author: xk
 * @description 抽奖过程工程类
 * @date: 2023/7/26 21:22
 */

@Component
public class ProcessFactory {

    Map<String, ILotteryProcess> lotteryProcessMap;

    public ProcessFactory(CommonLotteryProcess commonLotteryProcess, HighConcurrencyLotteryProcess highConcurrencyLotteryProcess) {
        lotteryProcessMap = new HashMap<>();
        lotteryProcessMap.put(LotteryProcessType.COMMON.getName(), commonLotteryProcess);
        lotteryProcessMap.put(LotteryProcessType.HIGH_CONCURRENCY.getName(), highConcurrencyLotteryProcess);
    }

    public ILotteryProcess getLotteryProcess(String beanName){
        return lotteryProcessMap.get(beanName);
    }
}


    