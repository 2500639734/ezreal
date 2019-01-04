package com.ezreal.common.pojo;

import com.ezreal.common.enums.LayuiResponseEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: shenke
 * @date: 2018/11/11 15:25
 * @description: 请求响应包装类,统一Layui返回结构,名称及方式与Layui保持一致
 */
@Data
public class LayuiResponse<T extends List> implements Serializable {

    /**
     * 状态码 0成功
     */
    private int code;

    /**
     * 消息
     */
    private String msg;

    /**
     * 分页时数据总页数
     */
    private Long count;

    /**
     * 数据
     */
    private T data;

    public LayuiResponse(){

    }

    public LayuiResponse(int code, String msg, Long count, T data){
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    /**
     * 请求成功
     * @param count 分页时的总条数
     * @param data 返回数据 || null
     * @return
     */
    /**
     * 请求成功
     * @param data 返回数据 || null
     * @return
     */
    public static <T extends List> LayuiResponse<T> buidSuccess(Long count, T data){
        return new LayuiResponse(LayuiResponseEnum.SUCCESS.getCode(), LayuiResponseEnum.SUCCESS.getMsg(), count, data);
    }

}
