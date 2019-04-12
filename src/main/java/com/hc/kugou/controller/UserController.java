package com.hc.kugou.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hc.kugou.bean.custombean.CustomUser;
import com.hc.kugou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:
 * @Date:2019/4/11
 * @Description:com.hc.kugou.controller
 * @Version:1.0
 */
@Controller
public class UserController {
    private JSONObject jsonObject;

    @Autowired
    private UserService userService;

    /**
     * 校验用户在登录框输入的信息
     * @param user  输入的信息
     * @return  返回验证结果信息
     */
    @ResponseBody
    @PostMapping("user.regxLoginInputInfo.ajax")
    public JSONObject regxLoginInputInfo(CustomUser user){
        //验证业务
        userService.regxLoginInputInfo(user);
        return null;
    }

    /**
     * 登录
     * @param user  用户输入的登录信息
     * @return  返回登录结果信息
     */
    @ResponseBody
    @PostMapping("user.login.ajax")
    public JSONObject loginAjax(CustomUser user){
        //登录业务
        userService.loginService(user);
        return null;
    }

    /**
     * 退出登录
     * @return  退出登录结果信息
     */
    @ResponseBody
    @PostMapping("user.loginOut.ajax")
    public JSONObject loginOutAjax(){
        return null;
    }


    /**
     * 用户输入注册信息校验
     * @param user  输入的注册信息
     * @return  返回校验结果
     */
    @ResponseBody
    @PostMapping("user.regxRegistInputInfo.ajax")
    public JSONObject regxRegistInputInfo(CustomUser user){
        //验证业务
        userService.regxRegistInputInfo(user);
        return null;
    }

    @ResponseBody
    @PostMapping("user.regist.ajax")
    public JSONObject registAjax(CustomUser user){
        userService.registService(user);
        return null;
    }
}
