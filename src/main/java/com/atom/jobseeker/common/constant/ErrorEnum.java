package com.atom.jobseeker.common.constant;

/**
 * @author wayan,sunlei
 */
public enum ErrorEnum {
    JOB_PUSH_ERROR(501, "岗位推送出现异常"),
    JOB_DOWN_ERROR(502, "岗位下架出现异常"),
    JOB_RE_DOWN_ERROR(503, "未上线无需下架"),
    JOB_RE_PUSH_ERROR(503, "请勿重新上线"),
    JOB_SAVE_ERROR(504, "发布岗位信息失败，请重新发布"),

    HOUSE_PUSH_ERROR(501, "房屋推送出现异常"),
    HOUSE_DOWN_ERROR(502, "房屋下架出现异常"),
    HOUSE_RE_DOWN_ERROR(503, "未上线无需下架"),
    HOUSE_RE_PUSH_ERROR(503, "请勿重新上线"),

    GEN_DUP_HANDLE_ERROR(505,"重复Community数据处理异常"),
    GEN_HOUSE_POST_ERROR(506,"推送house到正式表失败"),
    GEN_CMY_POST_ERROR(507,"推送community到正式表失败");




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
