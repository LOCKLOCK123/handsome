package com.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * Created by linla on 2021/7/28.
 */
@RestController
public class LoginController{


    @PostMapping("/login")
    public String login(){
        String account = "lin";
        String password= "23333";
        UsernamePasswordToken token = new UsernamePasswordToken(account,password,false);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
            //此步将 调用realm的认证方法
        } catch(IncorrectCredentialsException e){
            //这最好把 所有的 异常类型都背会
            return "密码错误";
        } catch (AuthenticationException e) {
            return "登录失败";
        }
        return "index";


    }
}
