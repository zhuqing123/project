package com.sxlc.project.exception;

/**
 * @Author : Andy
 * @Date : Cteated in 18:13 2019/6/18
 * @Description : 自定义异常
 * @Version : v1.0
 */
public class CustomException extends Exception {

    public CustomException() {

    }

    public CustomException(String s) {
        super(s);
    }

    public CustomException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public CustomException(Throwable throwable) {
        super(throwable);
    }
}
