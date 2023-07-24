package com.yumi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @version 1.0
 * @author: xk
 * @description 抽奖类启动类
 * @date: 2023/7/20 22:14
 */

@SpringBootApplication
@MapperScan("com.yumi.lottery.mapper")
public class LotteryApplication {
    public static void main(String[] args) {
        SpringApplication.run(LotteryApplication.class, args);
    }
}


    