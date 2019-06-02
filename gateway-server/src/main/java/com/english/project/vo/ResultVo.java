package com.english.project.vo;

import java.io.Serializable;

/**
 * @Author: ZhuQing
 * @Date: 2019/4/2 17:44
 */

public class ResultVo<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    public ResultVo(){

    }

    public ResultVo(int code, String msg){
        this.code=code;
        this.msg=msg;
    }

    public ResultVo(int code, String msg, T data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
