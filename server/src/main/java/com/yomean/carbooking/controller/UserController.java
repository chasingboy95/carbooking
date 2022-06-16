package com.yomean.carbooking.controller;

import com.yomean.carbooking.bean.User;
import com.yomean.carbooking.common.ReturnMessage;
import com.yomean.carbooking.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "UserController", tags = "user controller")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation("do login")
    public ReturnMessage<String> login(@RequestBody User user) {
        return ReturnMessage.success(userService.login(user));
    }

    @PostMapping("/logout")
    @ApiOperation("do logout")
    public ReturnMessage<Void> logout(String token) {
        userService.logout(token);
        return ReturnMessage.success();
    }

    @GetMapping("/user/{id}")
    @ApiOperation("get user by id")
    public ReturnMessage<User> getUserById(@PathVariable("id") Long id) {
        return ReturnMessage.success(userService.getById(id));
    }
}
