package com.boot.jwt.result;

/**
 * @PackageName: com.boot.jwt.result
 * @ClassName: StatusCode
 * @Description: This is StatusCode class by Skwen.
 * @Author: Skwen
 * @Date: 2021-04-06 20:15
 */
public class StatusCode {
    public static final int OK = 200;//成功
    public static final int NOTFOUND = 404;//404
    public static final int ERROR = 500;//失败
    public static final int ACCESSERROR = -1000;//权限不足
}
