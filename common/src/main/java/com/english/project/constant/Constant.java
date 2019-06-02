package com.english.project.constant;

import java.io.Serializable;

/**
 * @Author ZhuQing
 * @Date: 2019/5/19  17:07
 */
public class Constant implements Serializable {

    /**
     * 未删除
     */
    public static final Byte DEL_FLAG_ZERO = 0;

    /**
     * 删除
     */
    public static final Byte DEL_FLAG_ONE = 1;

    /**
     * 手机号正则
     */
    public static final String PHONE_REX="^$|^1[0-9]{10}$";
    /**
     * 手机号验证错误提示
     */
    public static final String PHONE_REX_ERROR="请输入正确的手机号码";
    /**
     * 密码正则 6到12位的字母或数字
     */
    public static final String PASSWORD_REX="^[0-9a-zA-Z]{6,12}$";
    /**
     * 密码验证错误提示
     */
    public static final String PASSWORD_REX_ERROR="请输入，6到12位的字母或数字";

}
