package com.hc.kugou.service;

import com.hc.kugou.service.exception.qq.StateErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author ck
 * @create 2019-05-19 20:10
 */
public interface QqService {
    /**
     * qq请求
     * @param session
     * @return
     */
    String qqRequest(HttpSession session);


    /**
     * qq回调
     * @param request
     * @throws Exception
     */
    void qqCallback(HttpServletRequest request) throws Exception;
}
