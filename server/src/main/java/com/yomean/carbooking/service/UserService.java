package com.yomean.carbooking.service;

import com.yomean.carbooking.bean.User;
import com.yomean.carbooking.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getById(Long id){
        return userDao.getById(id);
    }


}
