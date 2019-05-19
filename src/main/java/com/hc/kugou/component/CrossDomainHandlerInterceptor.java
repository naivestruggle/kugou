package com.hc.kugou.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:杨鑫虎
 * @Date:2019/4/26
 * @Description:  跨域访问  保证同一session
 * @Version:1.0
 */
public class CrossDomainHandlerInterceptor implements HandlerInterceptor {
    private static int flag = 1;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        if(flag == 1){
            Cookie cookie = new Cookie("JSESSIONID", "FC1A1F507F1B9E9D54BF9AF912825A01");
            cookie.setMaxAge(Integer.MAX_VALUE);
            response.addCookie(cookie);
            flag = 2;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
