package com.hc.kugou.controller;

import com.alibaba.fastjson.JSONObject;
import com.hc.commons.ResponseUtils;
import com.hc.kugou.bean.custombean.CustomUser;
import com.hc.kugou.service.UserService;
import com.hc.kugou.service.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Author:
 * @Date:2019/4/11
 * @Description:com.hc.kugou.controller
 * @Version:1.0
 */
@Controller
public class UserController {

    /**
     * 登录验证码key值字符串
     */
    private static final String LONG_VERFIY_CODE_STRING = "sessionLoginVerifyCode";
    /**
     * 注册验证码key值字符串
     */
    private static final String REGIST_VERFIY_CODE_STRING = "sessionRegistVerifyCode";
    /**
     * 修改密码验证码key值字符串
     */
    private static final String ALTER_PWD_VERFIY_CODE_STRING = "sessionAlterPwdVerifyCode";

    /**
     * 绑定邮箱的验证码
     */
    private static final String Bind_Email_VerifyCode = "sessionBindEmailVerifyCode";

    @Autowired
    private UserService userService;

    private JSONObject jsonObject;
    /**
     * 校验用户在登录框输入的信息
     * @param account  账号
     * @param password 密码
     * @return  返回验证结果信息
     */
    @ResponseBody
    @PostMapping("user.regxLoginInputInfo.ajax")
    public JSONObject regxLoginInputInfo(String account,String password){
        jsonObject = new JSONObject();
        //验证业务
        try {
            userService.regxLoginInputInfo(account,password);
            //返回信息
            ResponseUtils.responseNoException(jsonObject);
        } catch (Exception e) {
            ResponseUtils.responseException(jsonObject,e);
        }
        return jsonObject;
    }

    /**
     * 登录
     * @param account  账号
     * @param password 密码
     * @return  返回登录结果信息
     */
    @ResponseBody
    @PostMapping("user.login.ajax")
    public JSONObject loginAjax(String account,String password,HttpSession session){
        jsonObject = new JSONObject();
        //登录业务
        try {
            CustomUser loginedUser = userService.loginService(account, password);
            //将用户信息存入session
            session.setAttribute("loginedUser",loginedUser);
            //返回信息
            ResponseUtils.responseNoException(jsonObject);
        } catch (Exception e) {
            ResponseUtils.responseException(jsonObject,e);
        }
        return jsonObject;
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
        jsonObject = new JSONObject();
        //验证业务
        try {
            userService.regxRegistInputInfo(user);

            //返回信息
            ResponseUtils.responseNoException(jsonObject);
        } catch (Exception e) {
            ResponseUtils.responseException(jsonObject,e);
        }
        return jsonObject;
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping("user.regist.ajax")
    public JSONObject registAjax(CustomUser user,HttpSession session){
        jsonObject = new JSONObject();

        //得到session中的验证码
        String sessionVerifyCode = (String) session.getAttribute(user.getUserTel() + REGIST_VERFIY_CODE_STRING);

        //注册
        try {
            userService.registService(user,sessionVerifyCode);

            //清除session中的验证码
            session.removeAttribute(user.getUserTel() + REGIST_VERFIY_CODE_STRING);

            //返回信息
            ResponseUtils.responseNoException(jsonObject);
        } catch (Exception e) {
            ResponseUtils.responseException(jsonObject,e);
        }
        return jsonObject;
    }


    /**
     * 发送验证码
     *
     * @param account 账号
     * @param type    验证码类型
     * @return 响应对象
     */
    @ResponseBody
    @PostMapping("user.sendVerifyCode.ajax")
    public JSONObject sendVerifyCode(String account, String type, HttpSession session) {
        //创建一个响应对象
        jsonObject = new JSONObject();
        //
        String verifyCodeValue = null;
        String verifyCodeKey = null;
        try {
            switch (type) {
                case "1":
                    //发送登录验证码
                    verifyCodeValue = userService.sendLoginVerifyCodeService(account);
                    verifyCodeKey = account + LONG_VERFIY_CODE_STRING;
                    break;
                case "2":
                    //发送注册验证码
                    verifyCodeValue = userService.sendRegistVerifyCodeService(account);
                    verifyCodeKey = account + REGIST_VERFIY_CODE_STRING;
                    break;
                case "3":
                    //发送修改密码验证码
                    verifyCodeValue = userService.sendAlterPwdVerifyCodeService(account);
                    verifyCodeKey = account + ALTER_PWD_VERFIY_CODE_STRING;
                    break;
                case "4":
                    verifyCodeValue = userService.sendBindEmailCodeService(account);
                    verifyCodeKey = account + Bind_Email_VerifyCode;
                    break;
                default:
                    //出现错误
                    jsonObject.put("code", -1);
                    jsonObject.put("errorMsg", "hello 程序员 404 type(验证码类型) 传入值有误 ");
                    return jsonObject;
            }
            //将验证码保存在session中
            session.setAttribute(verifyCodeKey,verifyCodeValue);
            //返回信息
            ResponseUtils.responseNoException(jsonObject);
        } catch (Exception e) {
            ResponseUtils.responseException(jsonObject,e);
        }

        return jsonObject;
    }

}
