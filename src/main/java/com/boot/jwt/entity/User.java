package com.boot.jwt.entity;

import lombok.Data;

import java.util.Date;

/**
 * @PackageName: com.boot.jwt.entity
 * @ClassName: User
 * @Description: This is User class by Skwen.
 * @Author: Skwen
 * @Date: 2021-04-06 20:21
 */
@Data
public class User {
    private Long id;

    private int type;

    private int state;

    private String username;

    private String password;

    private String email;

    private String phone;

    private String remark;

    private Date add_time;
}
