package activity.common.enums;

/**
 * @version 1.0
 * @author: xk
 * @description 活动状态码
 * @date: 2023/8/3 22:45
 */


public enum ActivityStatusCode {
    UNCOMMITED("1", "未提交"),
    UNAPPROVAL("2", " 待审核"),
    APPROVED("3", "审核通过"),
    RUNNING("4", "活动上架"),
    STOPPED("5", "活动下架");

    private String code;
    private String name;

    ActivityStatusCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}


    