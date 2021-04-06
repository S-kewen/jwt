package com.boot.jwt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @PackageName: com.boot.jwt.entity
 * @ClassName: Token
 * @Description: This is Token class by Skwen.
 * @Author: Skwen
 * @Date: 2021-04-06 20:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    private Long id;
    private String type;
    private String username;
    private String password;
    private String role;
    private Date add_time;
    private Date expire_time;
}
