package com.ezreal.pojo;

import lombok.Data;

/**
 * @author: shenke
 * @date: 2018/12/31 23:33
 * @description:
 */
@Data
public class DruidConfig extends DbConfig {

    public static final Integer MAX_WAIT = 10;
    public static final Integer CONNECTION_ERROR_RETRY_ATTEMPTS = 0;
    public static final Boolean BREAK_FATER_ACQUIRE_FAILURE = true;

    /**
     * 超时等待时间
     */
    private Integer maxWait;

    /**
     * 重试次数
     */
    private Integer connectionErrorRetryAttempts;

    /**
     * 是否自动重试
     */
    private Boolean breakAfterAcquireFailure;

}
