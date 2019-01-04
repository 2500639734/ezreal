package com.ezreal.common.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: shenke
 * @date: 2018/12/15 16:51
 * @description: springboot参数校验解析工具类
 */
public class BindingResultUtil {

    private BindingResultUtil(){

    }

    /**
     * 新增时校验参数,忽略主键名称为id的字段校验
     * 校验的实体主键名称必须为id,否则会错误的对id进行校验,新增时id是系统生成,不需要校验的
     * @param bindResult
     * @return 返回第一条作为msg提示即可,而不是返回全部错误
     */
    public static String checkParamsAdd(BindingResult bindResult){
        if(bindResult.hasErrors()){
            List<FieldError> fieldErrors = bindResult.getFieldErrors();
            for(FieldError fieldError : fieldErrors){
                if(fieldError.getField().equals("id")){
                    continue;
                }
                return fieldError.getDefaultMessage();
            }
        }
        return null;
    }

    /**
     * 更新时校验参数
     * @param bindResult
     * @return 返回第一条作为msg提示即可,而不是返回全部错误
     */
    public static String checkParamsEdit(BindingResult bindResult){
        if(bindResult.hasErrors()){
            List<FieldError> fieldErrors = bindResult.getFieldErrors();
            if(fieldErrors != null && fieldErrors.size() > 0){
                return fieldErrors.get(0).getDefaultMessage();
            }
        }
        return null;
    }

    /**
     * 校验参数
     * @param bindResult 返回全部错误
     * @return
     */
    public static Map<String, String> checkParamsReturnAll(BindingResult bindResult){
        Map<String, String> paramErrors = new HashMap<>();
        if(bindResult.hasErrors()){
            List<FieldError> fieldErrors = bindResult.getFieldErrors();
            fieldErrors.stream().forEach(fieldError -> {
                paramErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
            });
        }
        return paramErrors;
    }

}
