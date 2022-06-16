package com.yomean.carbooking.service.impl;

import com.yomean.carbooking.bean.User;
import com.yomean.carbooking.bean.UserToken;
import com.yomean.carbooking.common.ReturnCode;
import com.yomean.carbooking.common.ServiceException;
import com.yomean.carbooking.common.shiro.TokenGenerator;
import com.yomean.carbooking.dao.UserDao;
import com.yomean.carbooking.service.UserService;
import com.yomean.carbooking.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserTokenService userTokenService;

    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public String login(User user) {
        if (Objects.isNull(user.getId())) {
            throw new ServiceException(ReturnCode.EMPTY_ID_OR_PASSWORD);
        }
        if (!userDao.checkPassword(user.getId(), user.getPassword())) {
            throw new ServiceException(ReturnCode.INVALID_ID_OR_PASSWORD);
        }
        UserToken userToken = userTokenService.getByUserId(user.getId());
        LocalDateTime updateTime = LocalDateTime.now();
        LocalDateTime expireTime = updateTime.plusHours(1);
        String token = TokenGenerator.generateValue();
        if (Objects.isNull(userToken)) {
            userToken = UserToken.builder()
                    .userId(user.getId())
                    .token(token)
                    .expireTime(expireTime)
                    .updateTime(updateTime)
                    .build();
            userTokenService.addUserToken(userToken);
        } else {
            userToken.setToken(token);
            userToken.setExpireTime(expireTime);
            userToken.setUpdateTime(updateTime);
            userTokenService.updateUserToken(userToken);
        }
        return token;
    }

    @Override
    public void logout(String token) {
        UserToken userToken = userTokenService.getByToken(token);
        if (Objects.isNull(userToken)) {
            throw new ServiceException(ReturnCode.NOT_LOGIN);
        }
        userToken.setExpireTime(LocalDateTime.now());
        userToken.setUpdateTime(LocalDateTime.now());
        userTokenService.updateUserToken(userToken);
    }


}
