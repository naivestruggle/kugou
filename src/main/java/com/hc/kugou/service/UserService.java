package com.hc.kugou.service;

import com.hc.kugou.bean.custombean.CustomUser;
import com.hc.kugou.service.exception.login.LoginException;
import com.hc.kugou.service.exception.tel.UnknownTelException;

/**
 * @Author:
 * @Date:2019/4/11
 * @Description:com.hc.kugou.service
 * @Version:1.0
 */
public interface UserService {

    /**
     * 验证用户输入的登录信息
     * @param account  账号
     * @param password 密码
     */
    void regxLoginInputInfo(String account,String password) throws Exception;

    /**
     * 登录
     * @param account  账号
     * @param password 密码
     */
    CustomUser loginService(String account,String password) throws Exception;

    /**
     * 验证用户输入的注册信息
     *      * @param user  存储注册信息的用户对象
     */
    void regxRegistInputInfo(CustomUser user) throws Exception;

    /**
     * 注册
     * @param user  用户输入的注册信息
     */
    void registService(CustomUser user,String sessionVerifyCode) throws Exception;

    /**
     * 发送登录验证码
     * @param account
     * @return
     */
    String sendLoginVerifyCodeService(String account) throws Exception;

    /**
     * 发送注册验证码
     * @param account
     * @return
     */
    String sendRegistVerifyCodeService(String account)throws Exception;

    /**
     * 发送修改密码验证码
     * @param account
     * @return
     */
    String sendAlterPwdVerifyCodeService(String account)throws Exception;

    /**
     * 发送绑定邮箱验证码
     * @param account
     * @return
     */
    String sendBindEmailCodeService(String account)throws Exception;
}
