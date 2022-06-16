package com.yomean.carbooking.common.shiro;


import com.yomean.carbooking.bean.User;
import com.yomean.carbooking.bean.UserToken;
import com.yomean.carbooking.common.ReturnCode;
import com.yomean.carbooking.common.ServiceException;
import com.yomean.carbooking.service.UserService;
import com.yomean.carbooking.service.UserTokenService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @Author hym
 * @Date 2022-06-16 0:08
 * @Version 1.0
 * @Description
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private UserTokenService userTokenService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }

    /**
     * 认证(登录时调用)
     * 每次请求的时候都会调用这个方法验证token是否失效和用户是否被锁定
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();

        //get user token info by token
        UserToken userToken = userTokenService.getByToken(accessToken);
        //token expire
        if (userToken == null || userToken.getExpireTime().toEpochSecond(ZoneOffset.of("+8"))
                < LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"))) {
            throw new ServiceException(ReturnCode.NOT_LOGIN);
        }

        //get user info
        User user = userService.getById(userToken.getUserId());
        //locked not support now
        if (user.getStatus() == 1) {
            throw new LockedAccountException("account has been locked");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
        return info;
    }
}
