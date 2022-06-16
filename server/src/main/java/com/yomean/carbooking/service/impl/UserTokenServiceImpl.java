package com.yomean.carbooking.service.impl;

import com.yomean.carbooking.bean.UserToken;
import com.yomean.carbooking.dao.UserTokenDao;
import com.yomean.carbooking.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author hym
 * @Date 2022-06-16 0:27
 * @Version 1.0
 * @Description
 */
@Service
public class UserTokenServiceImpl implements UserTokenService {

    @Autowired
    private UserTokenDao userTokenDao;

    @Override
    public void addUserToken(UserToken userToken) {
        userTokenDao.addUserToken(userToken);
    }

    @Override
    public void updateUserToken(UserToken userToken) {
        userTokenDao.updateUserToken(userToken);

    }

    @Override
    public UserToken getByUserId(Long userId) {
        return userTokenDao.getByUserId(userId);
    }

    @Override
    public UserToken getByToken(String token) {
        return userTokenDao.getByToken(token);
    }
}
