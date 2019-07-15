package com.sxlc.project.constant;

/**
 * @ Author : Andy
 * @ Date : Cteated in 16:03 2019/6/18
 * @ Description : 正则常量
 * @ Version : v1.0
 */
public final class RegexpConstant {

    /**
     * 手机号正则
     */
    public static final String PHONE_REX = "^$|^1[0-9]{10}$";

    /**
     * 密码正则 6到12位的字母或数字
     */
    public static final String PASSWORD_REX = "^[0-9a-zA-Z]{6,12}$";

    /**
     * 邮件正则
     */
    public static final String EMAIL_REX = "^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$";


}
