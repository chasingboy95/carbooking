package com.yomean.carbooking.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("User entity")
public class User {

    @ApiModelProperty("primary key")
    private Long id;

    @ApiModelProperty("user name")
    private String name;

    @ApiModelProperty("user phone number")
    private String phoneNumber;

    @ApiModelProperty("password")
    private String password;

    @ApiModelProperty("role")
    private int role;

    @ApiModelProperty("status")
    private int status;

}
