package com.hc.kugou.controller;

import com.hc.kugou.service.QqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author ck
 * @create 2019-05-19 20:04
 */
@Controller
public class QqController {

    @Autowired
    private QqService qqService;

    /**
     * 发起请求
     * @param session
     * @return
     */
    @GetMapping("/qq/oauth")
    public String qq(HttpSession session){

        String url = qqService.qqRequest(session);

        return "redirect:" + url;
    }

    /**
     * QQ回调
     * @param request
     * @return
     */
    @GetMapping("/qq/callback")
    public String qqCallback(HttpServletRequest request) {

        try {
            qqService.qqCallback(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/index";
    }

}
