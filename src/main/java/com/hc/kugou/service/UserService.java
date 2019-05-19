package com.hc.kugou.service;

import com.hc.kugou.bean.custombean.CustomUser;
import com.hc.kugou.service.exception.file.FileNotUploadException;
import com.hc.kugou.service.exception.login.LoginException;
import com.hc.kugou.service.exception.tel.UnknownTelException;
import com.hc.kugou.service.exception.verify.UnknownVerifyException;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 上传用户头像
     * @param loginedUser  登录用户
     * @param file  头像对象
     */
    void uploadUserHeadImage(CustomUser loginedUser, MultipartFile file) throws Exception;

    /**
     * 更新登录用户
     * @param loginedUser   登录用户
     * @return  用户对象
     */
    CustomUser updateLoginedUser(CustomUser loginedUser) throws Exception;

    /**
     * 修改用户信息
     * @param userId    用户id
     * @param info  用户新信息
     * @param sessionVerifyCode  会话中的验证码
     */
    void updateUserInfo(Integer userId, CustomUser info,String sessionVerifyCode) throws Exception;

    /**
     * 通过原密码修改密码
     * @param info  用户信息
     * @param userId    用户id
     */
    void alterPwdVerifyByOldPwd(CustomUser info, Integer userId,String sessionVerifyCode) throws Exception;

    /**
     * 修改邮箱
     * @param userId    用户id
     * @param verifyCode    用户输入的验证码
     * @param sessionVerifyCode session中的验证码
     * @param oldPwd    原密码
     * @param userUsername    用户名
     * @param userTel    手机号
     * @throws Exception    抛出异常
     */
    void updateEmail(String userUsername,String userTel,Integer userId, String verifyCode, String sessionVerifyCode, String oldPwd,String email)throws Exception;

    /**
     * 发送原绑定手机号的验证码
     * @param account   手机号
     * @return  验证码
     * @throws Exception 抛出异常
     */
    String sendBindOldTelVerifyCodeService(String account)throws Exception;

    /**
     * 发送新绑定手机的验证码
     * @param account   手机号
     * @param userTel  当前登录用户的手机号
     * @return  验证码
     * @throws Exception 抛出异常
     */
    String sendBindNewTelVerifyCodeService(String account,String userTel)throws Exception;

    /**
     * 绑定新的手机号
     * @param userId  用户id
     * @param verifyCode    用户输入的验证码
     * @param sessionVerifyCode 会话中的验证码
     * @param account   手机号
     * @param userEmail   邮箱
     * @param userUsername   用户名
     * @throws Exception    抛出异常
     */
    void updateUserTel(String userEmail,String userUsername,Integer userId, String verifyCode, String sessionVerifyCode, String account)throws Exception;

    /**
     * 设置密保问题
     * @param loginedUser   登录用户
     * @param sessionVerifyCode     会话中的验证码
     * @param verifyCode    用户输入的验证码
     * @param answer    密保答案
     * @param question  密保问题
     * @param userPwd   用户输入的密码
     * @throws Exception    抛出异常
     */
    void updateEnsureQuestion(CustomUser loginedUser, String sessionVerifyCode, String verifyCode, String answer, String question, String userPwd)throws Exception;
}
