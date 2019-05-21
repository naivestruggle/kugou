package com.hc.kugou.controller;

import com.alibaba.fastjson.JSONObject;
import com.hc.commons.CookieUtils;
import com.hc.commons.ResponseUtils;
import com.hc.commons.StringUtils;
import com.hc.commons.XMLUtils;
import com.hc.kugou.bean.custombean.CustomUser;
import com.hc.kugou.bean.custombean.Verification;
import com.hc.kugou.service.UserService;
import com.hc.kugou.service.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

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
    private static final String BIND_EMAIL_VERIFY_CODE_STRING = "sessionBindEmailVerifyCode";
    /**
     * 原绑定手机号的验证码
     */
    private static final String BIND_OLD_TEL_VERIFY_CODE_STRING = "sessionBindOldTelVerifyCode";
    /**
     * 新的绑定手机的验证码
     */
    private static final String BIND_NEW_TEL_VERIFY_CODE_STRING = "sessionBindNewTelVerifyCode";

    /**
     * 验证码图片保存路径
     */
    @Value("${image.root.path}")
    private String rootPath;

    /**
     * 自动登录
     */
    private static final String AUTO_LOGIN = "indream_autoLogin";

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
    @RequestMapping("user.login.ajax")
    public JSONObject loginAjax(String account,String password,HttpSession session){
        jsonObject = new JSONObject();
        //登录业务
        try {
            CustomUser loginedUser = userService.loginService(account, password);
            //将用户信息存入session
            session.setAttribute(StringUtils.LOGINED_USER,loginedUser);
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
    @GetMapping("user.loginOut.ajax")
    public String loginOutAjax(HttpSession session, HttpServletResponse response){
        session.removeAttribute(StringUtils.LOGINED_USER);
        CookieUtils.removeCookieByName("indream_autoLogin",response);
        return "redirect:index.html";
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
        //得到登录对象
        CustomUser loginedUser = (CustomUser)session.getAttribute(StringUtils.LOGINED_USER);
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
                    //发送绑定邮箱的验证码
                    verifyCodeValue = userService.sendBindEmailCodeService(account);
                    verifyCodeKey = account + BIND_EMAIL_VERIFY_CODE_STRING;
                    break;
                case "5":
                    //发送原来的绑定手机的验证码
                    account = loginedUser.getUserTel();
                    verifyCodeValue = userService.sendBindOldTelVerifyCodeService(account);
                    verifyCodeKey = account + BIND_OLD_TEL_VERIFY_CODE_STRING;
                    break;
                case "6":
                    //发送新的绑定手机的验证码
                    verifyCodeValue = userService.sendBindNewTelVerifyCodeService(account,loginedUser.getUserTel());
                    verifyCodeKey = account + BIND_NEW_TEL_VERIFY_CODE_STRING;
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

    /**
     * 跳转到个人信息页面
     * @param model model对象
     * @param session   会话对象
     * @return  页面地址
     */
    @GetMapping("user.showinfo")
    public String fun1(Model model,HttpSession session){
        //获取到当前登录对象
        CustomUser loginedUser = (CustomUser)session.getAttribute(StringUtils.LOGINED_USER);
        if(loginedUser == null){
            model.addAttribute("errorMsg","出错了");
            return "error/404";
        }
        System.out.println(loginedUser);
        model.addAttribute("loginedUser",loginedUser);
        Map<String, String> provinceMap = XMLUtils.xmlProvince(this.getClass());
        model.addAttribute("provinceMap",provinceMap);
        return "user_info";
    }

    /**
     * 根据省ID创建市
     * @param provinceId  省id
     * @return  json对象
     */
    @ResponseBody
    @PostMapping("user.createCity.ajax")
    public JSONObject fun2(String provinceId){
        JSONObject jsonObject = new JSONObject();
        Map<String, String> cityMap = XMLUtils.xmlCities(provinceId,this.getClass());
        jsonObject.put("cityMap",cityMap);
        return jsonObject;
    }

    /**
     * 根据市ID创建县
     * @param cityId    市ID
     * @return  json对象
     */
    @ResponseBody
    @PostMapping("user.createDistricts.ajax")
    public JSONObject fun3(String cityId){
        JSONObject jsonObject = new JSONObject();
        Map<String, String> districtsMap = XMLUtils.xmlDistricts(cityId,this.getClass());
        jsonObject.put("districtsMap",districtsMap);
        return jsonObject;
    }


    /**
     * 生成验证码
     * @param session   会话对象
     * @param verifyType    验证码类型   1：修改信息  2：修改密码  3：修改邮箱
     * @param request   请求对象
     * @return  jsonobject对象
     */
    @ResponseBody
    @PostMapping("verify.createVerifyImage.ajax")
    public JSONObject fun4(HttpSession session,Integer verifyType,HttpServletRequest request){
        CustomUser loginedUser = (CustomUser)session.getAttribute(StringUtils.LOGINED_USER);
        JSONObject jsonObject = new JSONObject();
        // 创建验证码对象
        Verification verification = new Verification();
        // 生成验证码图片
        BufferedImage image = verification.getImage();
        // 将验证码文本保存在session中
        String sessionKey = StringUtils.getVerifySessionKey(loginedUser,verifyType);
        session.setAttribute(sessionKey,verification.getText());

        //得到保存路径
        String dir = StringUtils.getVerifySavePath(loginedUser,verifyType,rootPath,verification.getText());
        //将图片写入到硬盘
        try {
            ImageIO.write(image,"jpg",new File(rootPath+dir));
        } catch (IOException e) {
            e.printStackTrace();
        }
        jsonObject.put("verifyImage",dir);
        return jsonObject;
    }

    /**
     * 上传用户头像
     * @param file  头像文件
     * @param model 模型对象
     * @param session   会话对象
     * @return  跳转页面
     */
    @PostMapping("user.uploadImage")
    public String fun5(@RequestParam("file") MultipartFile file,Model model,HttpSession session){
        CustomUser loginedUser = (CustomUser)session.getAttribute(StringUtils.LOGINED_USER);
        try {
            //上传头像
            userService.uploadUserHeadImage(loginedUser,file);
            //更新用户
            loginedUser = userService.updateLoginedUser(loginedUser);
            session.setAttribute(StringUtils.LOGINED_USER,loginedUser);
            model.addAttribute("msg","设置成功");
        } catch (Exception e) {
            if(e instanceof CustomException){
                model.addAttribute("msg",e.getMessage());
            }else {
                model.addAttribute("msg","系统繁忙，请稍后再试");
            }
        }
        return fun1(model,session);
    }

    /**
     * 修改用户信息
     * @param info  用户信息
     * @param session   会话对象
     * @return  jsonobject对象
     */
    @ResponseBody
    @PostMapping("user.updateInfo.ajax")
    public JSONObject fun6(CustomUser info,HttpSession session){
        JSONObject jsonObject = new JSONObject();
        CustomUser loginedUser = (CustomUser)session.getAttribute(StringUtils.LOGINED_USER);
        if(loginedUser == null){
            //跳转404
        }
        //取出修改信息的验证码会话key
        String sessionKey = StringUtils.getVerifySessionKey(loginedUser,1);
        //取出会话中的验证码
        String sessionVerifyCode = (String)session.getAttribute(sessionKey);
        try {
            //修改信息
            userService.updateUserInfo(loginedUser.getUserId(),info,sessionVerifyCode);
            //更新登录用户
            loginedUser = userService.updateLoginedUser(loginedUser);
            session.setAttribute(StringUtils.LOGINED_USER,loginedUser);
            //返回用户信息
            jsonObject.put("code",1);
            session.removeAttribute(sessionKey);
            jsonObject.put("loginedUser",loginedUser);
        } catch (Exception e) {
            if(e instanceof CustomException){
                jsonObject.put("code",0);
                jsonObject.put("errorMsg",e.getMessage());
            }else {
                e.printStackTrace();
                jsonObject.put("code",-1);
            }
        }
        return jsonObject;
    }

    /**
     * 通过旧密码修改新密码
     * @param info  信息
     * @param session   会话对象
     * @return jsonobject对象
     */
    @ResponseBody
    @PostMapping("user.alterPwd.ajax")
    public JSONObject fun7(CustomUser info,HttpSession session){
        JSONObject jsonObject = new JSONObject();
        CustomUser loginedUser = (CustomUser)session.getAttribute(StringUtils.LOGINED_USER);
        if(loginedUser == null){
            //跳转404
        }
        //取出修改信息的验证码会话key
        String sessionKey = StringUtils.getVerifySessionKey(loginedUser,2);
        //取出会话中的验证码
        String sessionVerifyCode = (String)session.getAttribute(sessionKey);
        try {
            //修改密码
            userService.alterPwdVerifyByOldPwd(info,loginedUser.getUserId(),sessionVerifyCode);
            //退出登录
            session.removeAttribute(StringUtils.LOGINED_USER);
            //返回信息
            jsonObject.put("code",1);
            session.removeAttribute(sessionKey);
        } catch (Exception e) {
            if(e instanceof CustomException){
                jsonObject.put("code",0);
                jsonObject.put("errorMsg",e.getMessage());
            }else {
                e.printStackTrace();
                jsonObject.put("code",-1);
            }
        }

        return jsonObject;
    }

    /**
     * 修改邮箱
     * @param account   邮箱
     * @param verifyCode    验证码
     * @param oldPwd    密码
     * @return  jsonobject对象
     */
    @ResponseBody
    @PostMapping("user.alterEmail.ajax")
    public JSONObject fun8(String account,String verifyCode,String oldPwd,HttpSession session){
        JSONObject jsonObject = new JSONObject();
        //得到会话中的验证码
        String sessionVerifyCode = (String)session.getAttribute(account+BIND_EMAIL_VERIFY_CODE_STRING);
        //得到登录对象
        CustomUser loginedUser = (CustomUser)session.getAttribute(StringUtils.LOGINED_USER);
        if(loginedUser == null){
            //跳转404
        }
        try {
            //修改邮箱
            userService.updateEmail(loginedUser.getUserUsername(),loginedUser.getUserTel(),loginedUser.getUserId(),verifyCode,sessionVerifyCode,oldPwd,account);
            //更新当前登录用户
            loginedUser = userService.updateLoginedUser(loginedUser);
            session.setAttribute(StringUtils.LOGINED_USER,loginedUser);
            //返回结果
            jsonObject.put("code",1);
            session.removeAttribute(account+BIND_EMAIL_VERIFY_CODE_STRING);
            jsonObject.put("loginedUser",loginedUser);
        } catch (Exception e) {
            ResponseUtils.responseException(jsonObject,e);
        }
        return jsonObject;
    }

    /**
     * 绑定手机号之前的页面校验当前手机号的验证码
     * @param verifyCode    验证码
     * @param session 会话对象
     * @return  jsonobject对象
     */
    @ResponseBody
    @PostMapping("user.bindTelBeforeRegxVerifyCode.ajax")
    public JSONObject fun9(String verifyCode,HttpSession session){
        JSONObject jsonObject = new JSONObject();
        //得到当前登录用户对象
        CustomUser loginedUser = (CustomUser)session.getAttribute(StringUtils.LOGINED_USER);
        //得到会话中的验证码
        String sessionVerifyCode = (String)session.getAttribute(loginedUser.getUserTel()+BIND_OLD_TEL_VERIFY_CODE_STRING);
        if(StringUtils.isEmpty(verifyCode) || StringUtils.isEmpty(sessionVerifyCode) || !verifyCode.equals(sessionVerifyCode)){
            jsonObject.put("code",0);
            jsonObject.put("msg","请输入正确的验证码");
        }else {
            jsonObject.put("code",1);
            session.removeAttribute(loginedUser.getUserTel()+BIND_OLD_TEL_VERIFY_CODE_STRING);
        }
        return jsonObject;
    }

    /**
     * 绑定新的手机号
     * @param account   新手机号
     * @param verifyCode    验证码
     * @param session   会话对象
     * @return  jsonobject对象
     */
    @ResponseBody
    @PostMapping("user.bindTel.ajax")
    public JSONObject fun10(String account,String verifyCode,HttpSession session){
        JSONObject jsonObject = new JSONObject();
        //得到当前登录用户对象
        CustomUser loginedUser = (CustomUser)session.getAttribute(StringUtils.LOGINED_USER);
        //得到会话中的验证码
        String sessionVerifyCode = (String)session.getAttribute(account+BIND_NEW_TEL_VERIFY_CODE_STRING);
        try {
            //绑定手机号
            userService.updateUserTel(loginedUser.getUserEmail(),loginedUser.getUserUsername(),loginedUser.getUserId(),verifyCode,sessionVerifyCode,account);
            //更新登录信息
            loginedUser = userService.updateLoginedUser(loginedUser);
            session.setAttribute(StringUtils.LOGINED_USER,loginedUser);
            //返回信息
            jsonObject.put("code",1);
            session.removeAttribute(account+BIND_NEW_TEL_VERIFY_CODE_STRING);
            jsonObject.put("loginedUser",loginedUser);
        } catch (Exception e) {
            ResponseUtils.responseException(jsonObject,e);
        }
        return jsonObject;
    }

    /**
     * 设置密保
     * @param userPwd   用户密码
     * @param question  密保问题
     * @param answer    密保答案
     * @param verifyCode    验证码
     * @param session   会话对象
     * @return  jsonobject对象
     */
    @ResponseBody
    @PostMapping("user.addEnsurePwd.ajax")
    public JSONObject fun11(String userPwd,String question,String answer,String verifyCode,HttpSession session){
        JSONObject jsonObject = new JSONObject();
        //得到当前登录用户
        CustomUser loginedUser = (CustomUser)session.getAttribute(StringUtils.LOGINED_USER);
        //取出session中的验证码
        String sessionKey = StringUtils.getVerifySessionKey(loginedUser,3);
        String sessionVerifyCode = (String)session.getAttribute(sessionKey);

        try {
            //设置密保
            userService.updateEnsureQuestion(loginedUser,sessionVerifyCode,verifyCode,answer,question,userPwd);
            //更新登录用户
            loginedUser = userService.updateLoginedUser(loginedUser);
            session.setAttribute(StringUtils.LOGINED_USER,loginedUser);
            //返回信息
            jsonObject.put("code",1);
            jsonObject.put("loginedUser",loginedUser);
            //删除会话中的验证码
            session.removeAttribute(sessionKey);
        } catch (Exception e) {
            ResponseUtils.responseException(jsonObject,e);
        }
        return jsonObject;
    }
}
