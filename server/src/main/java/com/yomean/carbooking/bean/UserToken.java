package com.yomean.carbooking.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author hym
 * @Date 2022-06-16 0:21
 * @Version 1.0
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserToken implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    //用户ID
    private Long userId;
    //token
    private String token;
    //过期时间
    private LocalDateTime expireTime;
    //更新时间
    private LocalDateTime updateTime;

}
