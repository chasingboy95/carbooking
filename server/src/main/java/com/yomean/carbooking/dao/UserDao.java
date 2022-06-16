package com.yomean.carbooking.dao;

import com.yomean.carbooking.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    User getById(Long id);

    boolean checkPassword(Long id, String password);
}
