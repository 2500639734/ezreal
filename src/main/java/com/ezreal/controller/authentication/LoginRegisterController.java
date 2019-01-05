package com.ezreal.controller.authentication;

import com.ezreal.common.pojo.Response;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: shenke
 * @date: 2019/1/5 19:19
 * @description: 登录接口
 */
@RestController
@RequestMapping("/authentication")
public class LoginRegisterController {

    @RequestMapping("/login")
    public Response<Boolean> login(String username, String password){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (AuthenticationException e){
            return Response.buidFailure(false);
        }
        return Response.buidSuccess(true);
    }


}
