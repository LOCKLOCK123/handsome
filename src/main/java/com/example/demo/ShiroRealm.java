package com.example.demo;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by linla on 2021/7/27.
 */
public class ShiroRealm extends AuthorizingRealm {

    //进行权限校验
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("开始权限校验");
        // 获取用户名
        User user = (User) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 给该用户设置角色，角色信息存在 t_role 表中取
        Set<String> s_roles = new HashSet<String>();
        Set<String> s_permissions = new HashSet<String>();
        s_roles.add("admin");
        s_permissions.add("sys:add");
        authorizationInfo.addRoles(s_roles);
        // 给该用户设置权限，权限信息存在 t_permission 表中取
        authorizationInfo.addStringPermissions(s_permissions);
        return authorizationInfo;

    }

    //进行登录身份认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("开始验证登录");
        User user = new User("lin", "23333");
        AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
        return authcInfo;
    }
}
