package com.hc.kugou.service;

import com.hc.kugou.bean.custombean.CustomUser;

/**
 * @Author:
 * @Date:2019/4/11
 * @Description:com.hc.kugou.service
 * @Version:1.0
 */
public interface UserService {

    /**
     * 验证用户输入的登录信息
     * @param user  存储登录信息的用户对象
     */
    void regxLoginInputInfo(CustomUser user);

    /**
     * 登录
     * @param user  用户输入的登录信息
     */
    void loginService(CustomUser user);

    /**
     * 验证用户输入的注册信息
     * @param user  存储注册信息的用户对象
     */
    void regxRegistInputInfo(CustomUser user);

    /**
     * 注册
     * @param user  用户输入的注册信息
     */
    void registService(CustomUser user);
}