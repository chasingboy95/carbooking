package com.yomean.carbooking.service.impl;

import com.yomean.carbooking.bean.User;
import com.yomean.carbooking.dao.UserDao;
import com.yomean.carbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public User getById(Long id) {
        return userDao.getById(id);
    }


}
