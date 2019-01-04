package com.ezreal.common.enums;

/**
 * @author: shenke
 * @date: 2018/12/12 23:25
 * @description: 普通请求||ajax请求统一响应枚举
 */
public enum ResponseEnum {

    // 请求成功
    SUCCESS(200, "请求成功"),
    // 请求失败
    FAILURE(500, "请求失败"),
    // 参数错误
    PARAM_ERROR(400, "参数错误");

    private int code;
    private String msg;

    ResponseEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }

}
