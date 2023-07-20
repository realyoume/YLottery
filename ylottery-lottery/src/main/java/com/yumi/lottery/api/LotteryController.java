package com.yumi.lottery.api;

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

    @GetMapping("/process")
    public String process(){
        double random = Math.random();
        String ans = "hi_" + random;

        return ans;
    }
}


    