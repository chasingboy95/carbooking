package com.yomean.carbooking.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author hym
 * @Date 2022-06-16 0:21
 * @Version 1.0
 * @Description
 */
@Data
public class UserToken implements Serializable {
    private static final long serialVersionUID = 1L;

    //用户ID
    private Long userId;
    //token
    private String token;
    //过期时间
    private Date expireTime;
    //更新时间
    private Date updateTime;

}
