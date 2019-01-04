package com.ezreal.common.enums;

/**
 * @author: shenke
 * @date: 2018/11/11 15:25
 * @description: Layui请求响应枚举类,定义Layui请求状态枚举,名称及方式与Layui保持一致
 */
public enum LayuiResponseEnum {

    // 请求成功
    SUCCESS(0, "请求成功");

    private int code;
    private String msg;

    LayuiResponseEnum(int code, String msg){
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
