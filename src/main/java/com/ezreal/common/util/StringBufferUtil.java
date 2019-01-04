package com.ezreal.common.util;

/**
 * @author: shenke
 * @date: 2018/11/11 15:25
 * @description: StringBuffer构建工具,单例&线程安全
 */
public class StringBufferUtil {

    private StringBufferUtil(){
    }

    private static class SingletonHolder{
        private static StringBuffer stringBuffer = new StringBuffer();
    }

    public static StringBuffer getStringBuffer(){
        clear();
        return SingletonHolder.stringBuffer;
    }

    private static void clear(){
        if(SingletonHolder.stringBuffer != null){
            SingletonHolder.stringBuffer.delete(0, SingletonHolder.stringBuffer.length());
        }
    }

}
