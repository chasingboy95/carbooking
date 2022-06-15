package com.yomean.carbooking.common.shiro;


import com.yomean.carbooking.bean.User;
import com.yomean.carbooking.bean.UserToken;
import com.yomean.carbooking.service.UserService;
import com.yomean.carbooking.service.UserTokenService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    /**
     * 授权(验证权限时调用)
     * 前端在请求带@RequiresPermissions注解 注解的方法时会调用 doGetAuthorizationInfo 这个方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();
        Long userId = user.getId();

        //用户权限列表
//        Set<String> permsSet = userService.getById(userId).getRoll();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     * 每次请求的时候都会调用这个方法验证token是否失效和用户是否被锁定
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();

        //根据accessToken，查询用户信息
        UserToken userToken = userTokenService.getByToken(accessToken);
        //token失效
        if (userToken == null || userToken.getExpireTime().getTime() < System.currentTimeMillis()) {
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }

        //查询用户信息
        User user = userService.getById(userToken.getUserId());
        //账号锁定
        if (user.getStatus() == 1) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
        return info;
    }
}
