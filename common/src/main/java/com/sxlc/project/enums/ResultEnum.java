package com.sxlc.project.enums;

/**
 * @Author ZhuQing
 * @Date: 2019/4/16  10:50
 */
public enum ResultEnum {

    SUCCESS(200, "操作成功"),

    FAil(-1, "操作失败"),

    LOGIN_NAME_EXIST(1001, "手机号码已经存在"),

    USER_NAME_EXIST(1002, "用户昵称已经存在"),

    EMAIL_ERROR(1003, "请输入正确的邮箱"),

    SEND_EMAIL_ERROR(1004, "发送邮件失败");

    private ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;

    }

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
