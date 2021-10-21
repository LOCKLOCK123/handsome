package com.example.demo;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Created by linla on 2021/7/27.
 */
public class JWTToken implements AuthenticationToken {

    private String token;

    private String exipreAt;

    public Object getPrincipal() {
        return token;
    }

    public Object getCredentials() {
        return token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExipreAt() {
        return exipreAt;
    }

    public void setExipreAt(String exipreAt) {
        this.exipreAt = exipreAt;
    }
}
