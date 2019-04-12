package com.hc.kugou.service.impl;

import com.hc.kugou.bean.custombean.CustomUser;
import com.hc.kugou.mapper.UserMapper;
import com.hc.kugou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:
 * @Date:2019/4/11
 * @Description:com.hc.kugou.service.impl
 * @Version:1.0
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    /**
     * 验证用户输入的登录信息
     *
     * @param user 存储登录信息的用户对象
     */
    @Override
    public void regxLoginInputInfo(CustomUser user) {
        //登录支持QQ 微信 手机号 酷狗号 + 密码   验证格式
    }

    /**
     * 登录
     *
     * @param user 用户输入的登录信息
     */
    @Override
    public void loginService(CustomUser user) {
        //查询数据库  看用户是否存在
        //登录支持QQ 微信 手机号 酷狗号 + 密码
        CustomUser keyUser = new CustomUser();
        try {
            CustomUser resultUser = userMapper.selectByNotNullFields(keyUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证用户输入的注册信息
     *
     * @param user 存储注册信息的用户对象
     */
    @Override
    public void regxRegistInputInfo(CustomUser user) {
        //注册  验证格式
    }

    /**
     * 注册
     *
     * @param user 用户输入的注册信息
     */
    @Override
    public void registService(CustomUser user) {
        //各种判空

        //如果能注册   向数据库中添加用户记录
        userMapper.insertUser(user);

    }
}
