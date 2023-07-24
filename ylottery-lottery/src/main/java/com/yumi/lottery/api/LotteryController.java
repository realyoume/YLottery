package com.yumi.lottery.api;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.yumi.lottery.model.dto.DrawResult;
import com.yumi.lottery.service.ILotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @version 1.0
 * @author: xk
 * @description 抽奖接口层
 * @date: 2023/7/20 22:08
 */

@RestController
public class LotteryController {
    @Autowired
    private ILotteryService lotteryService;

    @GetMapping("/draw")
    public DrawResult draw(){
        Integer userId = 1;
        Integer activityId = 1;

        return lotteryService.draw(userId, activityId);
    }
}


    