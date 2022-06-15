package com.yomean.carbooking.dao;

import com.yomean.carbooking.bean.UserToken;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author hym
 * @Date 2022-06-16 0:27
 * @Version 1.0
 * @Description
 */
@Mapper
public interface UserTokenDao {

    int addUserToken(UserToken userToken);

    int updateUserToken(UserToken userToken);

    int deleteByUserId(Long UserId);

    UserToken getByUserId(Long UserId);

    UserToken getByToken(String token);
}
