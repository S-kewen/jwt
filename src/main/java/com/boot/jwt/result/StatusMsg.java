package com.boot.jwt.result;

/**
 * @PackageName: com.boot.jwt.result
 * @ClassName: StatusMsg
 * @Description: This is StatusMsg class by Skwen.
 * @Author: Skwen
 * @Date: 2021-04-06 20:15
 */
public class StatusMsg {
    public static final String OK = "success";//成功
    public static final String NOTFOUND = "the request was not found!";//404
    public static final String ERROR = "request error!";//失败
    public static final String ACCESSERROR = "token invalid,please login again!";//token无效
}
