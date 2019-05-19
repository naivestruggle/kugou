package com.hc.commons;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:
 * @Date:2019/4/19
 * @Description:com.hc.commons
 * @Version:1.0
 */
public class CookieUtils {
    private CookieUtils(){}

    /**
     * 多个值的cookie分隔符
     */
    public static final String SEPARATOR = "[cookie]";

    /**
     * 根据cookie的名字获取cookie的值
     * @param cookieName    cookie名
     * @param request   请求对象
     * @return  cookie值
     */
    public static String getCookieValueByName(String cookieName, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return null;
        }
        for (Cookie cookie:cookies){
            if (cookie.getName().equals(cookieName)){
                return cookie.getValue();
            }
        }
        return null;
    }

    /**
     * 移除cookie
     * @param cookieName cookie名
     * @param response  响应对象
     */
    public static void removeCookieByName(String cookieName, HttpServletResponse response) {
        Cookie cookie = new Cookie(cookieName,"");
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
    }
}
