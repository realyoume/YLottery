package com.yumi.award.domain.id.impl;

import com.yumi.award.domain.id.IDGenerator;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * @version 1.0
 * @author: xk
 * @description 短ID生成
 * @date: 2023/8/1 22:43
 */

@Component
public class ShortCode implements IDGenerator {
    @Override
    public String nextId() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String s = dateFormat.format(date);

        return s;
    }
}



    