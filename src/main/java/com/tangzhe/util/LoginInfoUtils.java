package com.tangzhe.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 唐哲
 * 2018-06-26 9:17
 */
public class LoginInfoUtils {

    /**
     * 获取当前登录用户id
     */
    public static String getLoginUserId(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(authorization)) {
            JWTUtils.JWTResult result = JWTUtils.getInstance().checkToken(authorization);
            if (result.isStatus()) {
                return result.getUid();
            }
        }
        return null;
    }

}
