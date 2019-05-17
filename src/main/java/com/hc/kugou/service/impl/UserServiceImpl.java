package com.hc.kugou.service.impl;

import com.hc.commons.Code;
import com.hc.commons.MD5;
import com.hc.commons.Regx;
import com.hc.commons.Tel;
import com.hc.kugou.bean.custombean.CustomUser;
import com.hc.kugou.mapper.UserMapper;
import com.hc.kugou.service.UserService;
import com.hc.kugou.service.exception.email.EmailException;
import com.hc.kugou.service.exception.email.UnknownEmailException;
import com.hc.kugou.service.exception.login.LoginException;
import com.hc.kugou.service.exception.password.PasswordException;
import com.hc.kugou.service.exception.password.UnknownPasswordException;
import com.hc.kugou.service.exception.register.RegisterErrorException;
import com.hc.kugou.service.exception.register.RegisterException;
import com.hc.kugou.service.exception.sex.SexException;
import com.hc.kugou.service.exception.tel.TelException;
import com.hc.kugou.service.exception.tel.TelExistsException;
import com.hc.kugou.service.exception.tel.UnknownTelException;
import com.hc.kugou.service.exception.user.UnknownUserException;
import com.hc.kugou.service.exception.user.UserException;
import com.hc.kugou.service.exception.verify.UnknownVerifyException;
import com.hc.kugou.service.exception.verify.VerifyException;
import com.hc.kugou.service.exception.verify.VerifySendException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author:
 * @Date:2019/4/11
 * @Description:com.hc.kugou.service.impl
 * @Version:1.0
 */
@Service("userService")
@Transactional(rollbackFor = {UserException.class, TelException.class, SexException.class, VerifyException.class,
        RegisterException.class,PasswordException.class, EmailException.class})
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    /**
     * 验证用户输入的登录信息
     *
     * @param account 账号
     * @param password 密码
     */
    @Override
    public void regxLoginInputInfo(String account,String password) throws Exception {
        //登录支持QQ 微信 手机号 酷狗号 + 密码   验证格式
        if(account == null || "".equals(account)){
            throw new UnknownUserException("账号不能为空");
        }else if(password == null || "".equals(password)){
            throw new UnknownPasswordException("密码不能为空");
        }
    }

    /**
     * 登录
     *
     * @param account 账号
     * @param password 密码
     */
    @Override
    public CustomUser loginService(String account,String password) throws Exception {
        //登录支持QQ 微信 手机号 酷狗号 + 密码
        CustomUser keyUser = new CustomUser();
        account = account.trim();
        //查询数据库  看用户是否存在
        if(Regx.regxTelphone(account)){
            //如果是电话
            keyUser.setUserTel(account);
        }else if(Regx.regxEmail(account)){
            //如果是邮箱
            keyUser.setUserEmail(account);
        }else{
            //酷狗号
            keyUser.setUserAccount(account);
        }

        //两次加密密码
        keyUser.setUserPassword(MD5.parseMD5(MD5.parseMD5(password.trim())));

        CustomUser resultUser = userMapper.selectByNotNullFields(keyUser);

        if(resultUser == null){
            throw new LoginException("账号或密码错误");
        }

        return resultUser;
    }

    /**
     * 验证用户输入的注册信息
     *
     * @param user 存储注册信息的用户对象
     */
    @Override
    public void regxRegistInputInfo(CustomUser user) throws Exception {
        //注册  验证格式
        if(user == null){
            throw new UnknownUserException("用户错误");
        }
        if(user.getUserTel() != null && !Regx.regxTelphone(user.getUserTel().trim())){
            throw new UnknownTelException("手机号格式不正确");
        }

        if(user.getUserPassword() != null && !Regx.regxPassword(user.getUserPassword().trim())){
            throw new UnknownPasswordException("密码格式不正确,6-18位！");
        }

        if(user.getUserPassword2() != null && user.getUserPassword() != null && !user.getUserPassword().trim().equals(user.getUserPassword2().trim())){
            throw new UnknownPasswordException("两次密码不一致");
        }
    }

    /**
     * 注册
     * @param user 用户输入的注册信息
     */
    @Override
    public void registService(CustomUser user,String sessionVerifyCode) throws Exception{
        //各种判空
        if(user == null){
            throw new UnknownUserException("用户为空");
        }
        if(user.getUserTel() == null || "".equals(user.getUserTel().trim())){
            throw new UnknownTelException("手机号不能为空");
        }
        if(user.getVerifyCode() == null || "".equals(user.getVerifyCode().trim())){
            throw new UnknownVerifyException("验证码不能为空");
        }
        if(user.getUserPassword() == null || "".equals(user.getUserPassword().trim())){
            throw new UnknownPasswordException("密码不能为空");
        }
        if(user.getUserPassword2() == null || "".equals(user.getUserPassword2().trim())){
            throw new UnknownPasswordException("确认密码不能为空");
        }
        //验证验证码
        if(!Regx.regxVerifCode(user.getVerifyCode().trim(),sessionVerifyCode)){
            throw new UnknownVerifyException("验证码错误");
        }

        //如果能注册   向数据库中添加用户记录
        //密码加密两次
        user.setUserPassword(MD5.parseMD5(MD5.parseMD5(user.getUserPassword().trim())));
        //设置用户名
        user.setUserUsername(Code.createUserName("音梦人",""));
        //设置用户账号
        user.setUserAccount(Code.createUserAccount());
        userMapper.insertUser(user);
        //查询用户
        Integer userId = user.getUserId();
        CustomUser customUser = userMapper.selectById(userId);
        //如果用户不存在则抛异常
        if(customUser == null){
            throw new RegisterErrorException("系统繁忙，注册失败，请稍后再试");
        }

    }

    /**
     * 发送登录验证码
     * @param account
     * @return
     * @throws Exception
     */
    @Override
    public String sendLoginVerifyCodeService(String account) throws Exception {
        return null;
    }

    /**
     * 发送注册验证码
     * @param account
     * @return
     * @throws Exception
     */
    @Override
    public String sendRegistVerifyCodeService(String account) throws Exception {
        //判断手机格式是否正确
        if(!Regx.regxTelphone(account)){
            throw new UnknownTelException("手机号格式不正确");
        }
        //去数据库查是否已存在此用户
        Integer count = userMapper.selectByTelToCount(account);
        if(count != null && count >= 1){
            throw new TelExistsException("手机号已注册");
        }

        // 生成验证码
        String verifyCode = Code.createVerifyCode(6, Code.VERIFY_CODE_TYPE_TEL);

        try {
            // 发送验证码
            Tel.sendTelCode(account, "您的验证码：" + verifyCode + "，请勿将验证码泄露给他人！");
        } catch (Exception e) {
            e.printStackTrace();
            throw new VerifySendException("验证码发送失败，系统异常，请稍后再试！");
        }

        // 返回验证码
        return verifyCode;
    }

    /**
     * 发送修改密码验证码
     * @param account
     * @return
     * @throws Exception
     */
    @Override
    public String sendAlterPwdVerifyCodeService(String account) throws Exception {
        return null;
    }

    /**
     * 发送绑定邮箱验证码
     * @param account
     * @return
     * @throws Exception
     */
    @Override
    public String sendBindEmailCodeService(String account) throws Exception {
        return null;
    }
}
