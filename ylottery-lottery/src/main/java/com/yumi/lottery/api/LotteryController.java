package com.yumi.lottery.api;

import com.yumi.lottery.entity.QueryHistory;
import com.yumi.lottery.service.IQueryHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @version 1.0
 * @author: xk
 * @description 抽奖接口层
 * @date: 2023/7/20 22:08
 */

@RestController
public class LotteryController {

    @Autowired
    private IQueryHistoryService queryHistoryService;

    @GetMapping("/process")
    public String process(){
        double random = Math.random();
        String ans = "hi_" + random;

        QueryHistory queryHistory = new QueryHistory();
        queryHistory.setRandom(ans);
        queryHistory.setCreateTime(LocalDateTime.now());

        queryHistoryService.save(queryHistory);

        return ans;
    }
}


    