package com.yumi.award.common.enums;

/**
 * @version 1.0
 * @author: xk
 * @description ID生成器类型枚举类
 * @date: 2023/8/1 22:57
 */
public enum IDGeneratorType {
    SNOW_FLAKE("100", "雪花算法"),
    SHORT_CODE("200", "短ID");


    private String code;
    private String msg;

    IDGeneratorType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
