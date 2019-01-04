package com.ezreal.common.pojo;

import com.ezreal.common.enums.LayuiResponseEnum;
import com.ezreal.common.enums.ResponseEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: shenke
 * @date: 2018/12/12 23:24
 * @description: 普通请求||ajax请求统一返回结构
 */
@Data
public class Response<T> implements Serializable {

    /**
     * 状态码 200成功
     */
    private int code;

    /**
     * 消息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    public Response(){

    }

    public Response(int code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 请求成功
     * @param data 返回数据 || null
     * @return
     */
    public static <T> Response<T> buidSuccess(T data){
        return new Response(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMsg(), data);
    }

    /**
     * 请求失败
     * @param data 返回数据 || null
     * @return
     */
    public static <T> Response<T> buidFailure(T data){
        return new Response(ResponseEnum.FAILURE.getCode(), ResponseEnum.FAILURE.getMsg(), data);
    }

    /**
     * 参数错误
     * @param msg 提示消息
     * @return
     */
    public static Response buidParamError(String msg){
        return new Response(ResponseEnum.PARAM_ERROR.getCode(), msg, null);
    }

}
