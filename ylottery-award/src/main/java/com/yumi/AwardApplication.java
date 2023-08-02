package com.yumi;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @version 1.0
 * @author: xk
 * @description Award启动类
 * @date: 2023/8/1 21:04
 */

@SpringBootApplication
@MapperScan("com.yumi.award.mapper")
public class AwardApplication {
    public static void main(String[] args) {
        SpringApplication.run(AwardApplication.class, args);
    }
}


    