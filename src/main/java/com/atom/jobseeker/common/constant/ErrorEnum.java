package com.atom.jobseeker.common.constant;

/**
 * @author wayan,sunlei
 */
public enum ErrorEnum {
    JOB_PUSH_ERROR(501, "岗位推送出现异常"),
    JOB_DOWN_ERROR(502, "岗位下架出现异常"),
    JOB_RE_DOWN_ERROR(503, "未上线无需下架"),
    JOB_RE_PUSH_ERROR(503, "请勿重新上线"),

    HOUSE_PUSH_ERROR(501, "房屋推送出现异常"),
    HOUSE_DOWN_ERROR(502, "房屋下架出现异常"),
    HOUSE_RE_DOWN_ERROR(503, "未上线无需下架"),
    HOUSE_RE_PUSH_ERROR(503, "请勿重新上线");


    private int code;
    private String msg;

    ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
