package com.boot.jwt.config;

import cn.hutool.crypto.SecureUtil;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.boot.jwt.annotation.PassToken;
import com.boot.jwt.annotation.UserToken;
import com.boot.jwt.entity.Token;
import com.boot.jwt.entity.User;
import com.boot.jwt.result.MyResult;
import com.boot.jwt.result.StatusCode;
import com.boot.jwt.result.StatusMsg;
import com.boot.jwt.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @PackageName: com.boot.jwt.config
 * @ClassName: AuthenticationInterceptor
 * @Description: This is AuthenticationInterceptor class by Skwen.
 * @Author: Skwen
 * @Date: 2021-04-06 20:16
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenUtil tokenUtil;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String tokenStr = httpServletRequest.getHeader("Authorization");
        // 从 http 请求头中取出 token
        if (!(object instanceof HandlerMethod)) {
            // 如果不是映射到方法直接通过
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserToken.class)) {
            UserToken userToken = method.getAnnotation(UserToken.class);
            if (userToken.required()) {
                // 执行认证
                if (tokenStr == null || tokenStr == "") {
                    returnJson(httpServletResponse, new MyResult(StatusCode.ACCESSERROR, StatusMsg.ACCESSERROR));
                    return false;
                }
                // 获取 token 中的 username
                Token token = new Token();
                try {
                    token = tokenUtil.parseToken(tokenStr);
                } catch (JWTDecodeException j) {
                    returnJson(httpServletResponse, new MyResult(StatusCode.ACCESSERROR, StatusMsg.ACCESSERROR));
                    return false;
                }
                if (token == null) {
                    returnJson(httpServletResponse, new MyResult(StatusCode.ACCESSERROR, StatusMsg.ACCESSERROR));
                    return false;
                }
                if (new Date().after(token.getExpire_time())) {
                    returnJson(httpServletResponse, new MyResult(StatusCode.ACCESSERROR, StatusMsg.ACCESSERROR));
                    return false;
                }
                User user = new User();
                user.setUsername(token.getUsername());
                //以下為校驗過程，可以自定義規則，此處省略
//                user = userService.getByUsername(user);
//                if (user == null) {
//                    returnJson(httpServletResponse, new MyResult(StatusCode.ACCESSERROR, StatusMsg.ACCESSERROR));
//                    return false;
//                } else {
//                    if (user.getState() != 1) {
//                        returnJson(httpServletResponse, new MyResult(StatusCode.ACCESSERROR, StatusMsg.ACCESSERROR));
//                        return false;
//                    }
//                    if (!token.getPassword().equals(SecureUtil.md5(user.getPassword()))) {
//                        returnJson(httpServletResponse, new MyResult(StatusCode.ACCESSERROR, StatusMsg.ACCESSERROR));
//                        return false;
//                    }
//                }
                // 验证 token
                return true;
            }
        }
        return true;
    }

    private void returnJson(HttpServletResponse response, MyResult json) {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);
        } catch (IOException e) {
            System.out.println(e.toString());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
