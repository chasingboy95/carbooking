package com.yomean.carbooking.service;

import com.yomean.carbooking.bean.UserToken;

/**
 * @Author hym
 * @Date 2022-06-16 0:26
 * @Version 1.0
 * @Description
 */
public interface UserTokenService {

    void addUserToken(UserToken userToken);

    void updateUserToken(UserToken userToken);

    void deleteByUserId(Long userId);

    UserToken getByUserId(Long userId);

    UserToken getByToken(String token);

}
