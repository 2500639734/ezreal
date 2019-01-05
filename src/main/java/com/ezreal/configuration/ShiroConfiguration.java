package com.ezreal.configuration;

import com.ezreal.common.config.AuthenticationConfig;
import com.ezreal.common.realm.EzrealRealm;
import com.ezreal.common.util.MapUtil;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Map;

/**
 * @author: shenke
 * @date: 2019/1/5 18:25
 * @description: shiro配置
 */
@Configuration
public class ShiroConfiguration {

    @Autowired
    private AuthenticationConfig authenticationConfig;

    /**
     * 自定义身份认证
     * @return
     */
    @Bean
    public EzrealRealm ezrealRealm(){
        return new EzrealRealm();
    }

    /**
     * 核心安全事务管理器
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm
        securityManager.setRealm(ezrealRealm());
        return securityManager;
    }

    /**
     * 核心安全适配器
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 设置登陆页url
        shiroFilterFactoryBean.setLoginUrl(authenticationConfig.getLoginUrl());

        // 设置权限过滤
        shiroFilterFactoryBean.setFilterChainDefinitionMap(MapUtil.reforeKeyValue(authenticationConfig.getFilterChainDefinitionMap()));

        return shiroFilterFactoryBean;
    }

}
