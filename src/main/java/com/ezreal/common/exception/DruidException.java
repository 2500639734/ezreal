package com.ezreal.common.exception;

/**
 * @author: shenke
 * @date: 2018/11/11 15:25
 * @description: Druid 连接池异常类,手动编写Druid出现错误时抛出该异常
 */
public class DruidException extends RuntimeException {

    public DruidException(String message) {
        super(message);
    }

    public DruidException(String message, Throwable cause) {
        super(message, cause);
    }

    public DruidException(Throwable cause) {
        super(cause);
    }

}
