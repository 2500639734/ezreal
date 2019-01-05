package com.ezreal.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: shenke
 * @date: 2019/1/5 20:30
 * @description:
 */
@Component
@ConfigurationProperties(prefix="authentication")
@Data
public class AuthenticationConfig {

    /**
     * 登录页url,如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
     */
    private String loginUrl;

    /**
     * 身份认证设置
     */
    private LinkedHashMap<String, String> filterChainDefinitionMap;

}
