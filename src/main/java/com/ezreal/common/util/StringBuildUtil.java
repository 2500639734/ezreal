package com.ezreal.common.util;

/**
 * @author: shenke
 * @date: 2018/11/11 15:25
 * @description: StringBuild构建工具,单例&非线程安全
 */
public class StringBuildUtil {

    private StringBuildUtil(){
    }

    private static StringBuilder stringBuilder;

    public static StringBuilder getStringBuilder(){
        clear();
        if(stringBuilder == null){
            return stringBuilder =  new StringBuilder();
        }
        return stringBuilder;
    }

    private static void clear(){
        if(stringBuilder != null){
            stringBuilder.delete(0, stringBuilder.length());
        }
    }

}
