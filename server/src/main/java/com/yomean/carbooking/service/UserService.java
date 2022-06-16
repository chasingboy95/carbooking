package com.yomean.carbooking.service;

import com.yomean.carbooking.bean.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User getById(Long id);

    String login(User user);

    void logout(String token);

}
