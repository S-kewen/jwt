package com.boot.jwt.controller;

import com.boot.jwt.annotation.PassToken;
import com.boot.jwt.annotation.UserToken;
import com.boot.jwt.entity.Token;
import com.boot.jwt.entity.User;
import com.boot.jwt.result.MyResult;
import com.boot.jwt.result.StatusCode;
import com.boot.jwt.result.StatusMsg;
import com.boot.jwt.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @PackageName: com.boot.jwt.controller
 * @ClassName: JwtController
 * @Description: This is JwtController class by Skwen.
 * @Author: Skwen
 * @Date: 2021-04-06 20:24
 */
@RestController
@RequestMapping("/v1/api/jwt")
public class JwtController {
    @Autowired
    private TokenUtil tokenUtil;
    @PassToken
    @RequestMapping("/create")
    public MyResult create() {
        User user = new User();
        user.setId(1L);
        user.setType(1);
        user.setState(1);
        user.setUsername("skwen");
        user.setPassword("123456");
        Map<String, Object> map = new HashMap<>();
        map.put("token", tokenUtil.createToken(user));
        return new MyResult(StatusCode.OK, StatusMsg.OK, map);
    }

    @UserToken
    @RequestMapping("/check")
    public MyResult check(@RequestHeader("Authorization") String authorization) {
        Token token = tokenUtil.parseToken(authorization);
        if (token != null) {
            return new MyResult(StatusCode.OK, StatusMsg.OK, token);
        }else{
            return new MyResult(StatusCode.ACCESSERROR,StatusMsg.ACCESSERROR);
        }
    }
}
