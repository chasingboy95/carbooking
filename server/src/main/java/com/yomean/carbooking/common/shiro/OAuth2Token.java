package com.yomean.carbooking.common.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author hym
 * @Date 2022-06-16 0:05
 * @Version 1.0
 * @Description
 */
public class OAuth2Token implements AuthenticationToken {
    private String token;

    public OAuth2Token(String token) {
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}

