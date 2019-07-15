package com.sxlc.project.utils;

import java.util.regex.Pattern;

/**
 * @ Author : Andy
 * @ Date : Cteated in 16:08 2019/6/18
 * @ Description : 正则工具类
 * @ Version : v1.0
 */
public final class RegexUtils {

    /**
     * @param rex 正则表达式
     * @param str 需要匹配的字符串
     * @return
     */
    public static boolean matches(String rex, String str) {
        return Pattern.matches(rex, str);
    }
}
