package com.hc.kugou.service.impl;

import com.hc.commons.*;
import com.hc.kugou.bean.custombean.CustomUser;
import com.hc.kugou.mapper.UserMapper;
import com.hc.kugou.service.UserService;
import com.hc.kugou.service.exception.email.EmailException;
import com.hc.kugou.service.exception.email.UnknownEmailException;
import com.hc.kugou.service.exception.file.FileException;
import com.hc.kugou.service.exception.file.FileNotUploadException;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

/**
 * @Author:
 * @Date:2019/4/11
 * @Description:com.hc.kugou.service.impl
 * @Version:1.0
 */
@Service("userService")
@Transactional(rollbackFor = {UserException.class, TelException.class, SexException.class, VerifyException.class,
        RegisterException.class,PasswordException.class, EmailException.class, FileException.class})
public class UserServiceImpl implements UserService {
    /**
     * 图片保存路径
     */
    @Value("${image.root.path}")
    private String rootPath;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailUtils mailUtils;

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
        keyUser.setUserPassword(MD5.parseMD5(password));

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
        if(user.getUserTel() != null && !Regx.regxTelphone(user.getUserTel())){
            throw new UnknownTelException("手机号格式不正确");
        }

        if(user.getUserPassword() != null && !Regx.regxPassword(user.getUserPassword())){
            throw new UnknownPasswordException("密码格式不正确,6-18位！");
        }

        if(user.getUserPassword2() != null && user.getUserPassword() != null && !user.getUserPassword().equals(user.getUserPassword2())){
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
        if(user.getUserTel() == null || "".equals(user.getUserTel())){
            throw new UnknownTelException("手机号不能为空");
        }
        if(user.getVerifyCode() == null || "".equals(user.getVerifyCode())){
            throw new UnknownVerifyException("验证码不能为空");
        }
        if(user.getUserPassword() == null || "".equals(user.getUserPassword())){
            throw new UnknownPasswordException("密码不能为空");
        }
        if(user.getUserPassword2() == null || "".equals(user.getUserPassword2())){
            throw new UnknownPasswordException("确认密码不能为空");
        }
        //验证验证码
        if(!Regx.regxVerifCode(user.getVerifyCode(),sessionVerifyCode)){
            throw new UnknownVerifyException("验证码错误");
        }

        //如果能注册   向数据库中添加用户记录
        //密码加密两次
        user.setUserPassword(MD5.parseMD5(user.getUserPassword()));
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
     * @param account   邮箱
     * @return  验证码
     * @throws Exception    抛出异常
     */
    @Override
    public String sendBindEmailCodeService(String account) throws Exception {
        if(!Regx.regxEmail(account)){
            throw new UnknownEmailException("请输入正确的邮箱");
        }
        Integer count = userMapper.selectByEmailToCount(account);
        if(count >= 1){
            throw new UnknownEmailException("该邮箱已被其他用户绑定");
        }
        String code = Code.createVerifyCode(6,Code.VERIFY_CODE_TYPE_TEL);
        mailUtils.sendSimpleMail(account,"音梦-因梦而声","您的验证码是【"+code+"】，工作人员不会向您索取验证码，请勿泄露！");
        return code;
    }

    /**
     * 上传用户头像
     *
     * @param loginedUser 登录用户
     * @param file        头像对象
     */
    @Override
    public void uploadUserHeadImage(CustomUser loginedUser, MultipartFile file) throws Exception {
        //保存在数据库的路径
        String sqlPath = StringUtils.getHeadImageSavePath(loginedUser,file.getOriginalFilename());
        //没有文件名的路径
        String filePath = rootPath+sqlPath.substring(0,sqlPath.lastIndexOf("/")+1);
        //文件名
        String fileName = sqlPath.substring(sqlPath.lastIndexOf("/"));

        try {
            //保存到硬盘
            FileUtils.uploadFile(file.getBytes(),filePath,fileName);
        } catch (IOException e) {
            throw new FileNotUploadException("系统繁忙，头像上传失败！");
        }

        //修改数据库
        userMapper.updateHeadImage(loginedUser.getUserId(),sqlPath);
    }

    /**
     * 更新登录用户
     *
     * @param loginedUser 登录用户
     * @return 用户对象
     */
    @Override
    public CustomUser updateLoginedUser(CustomUser loginedUser) throws Exception {
        return userMapper.selectById(loginedUser.getUserId());
    }

    /**
     * 修改用户信息
     *
     * @param userId 用户id
     * @param info   用户新信息
     * @param sessionVerifyCode  会话中的验证码
     */
    @Override
    public void updateUserInfo(Integer userId, CustomUser info,String sessionVerifyCode) throws Exception {
        //验证码
        String verifyCode = info.getVerifyCode();
        System.out.println(verifyCode+".....修改信息....."+sessionVerifyCode);
        if(StringUtils.isEmpty(verifyCode) || StringUtils.isEmpty(sessionVerifyCode) || !verifyCode.equalsIgnoreCase(sessionVerifyCode)){
            throw new UnknownVerifyException("请输入正确的验证码");
        }

        //昵称
        String userUsername = info.getUserUsername();
        if(StringUtils.isEmpty(userUsername)){
            throw new UnknownUserException("请输入昵称");
        }

        //性别
        Integer userSex = info.getUserSex();
        if(userSex == null){
            throw new UnknownUserException("请选择性别");
        }

        //生日
        Date userBirthday = info.getUserBirthday();
        if(userBirthday.getTime() > System.currentTimeMillis()){
            throw new UnknownUserException("请选择小于当前时间的日期");
        }

        //地址
        String userAddr = info.getUserAddr();
        if(StringUtils.isEmpty(userAddr)) {
            throw new UnknownUserException("请输入地址");
        }

        info.setUserId(userId);
        //修改信息
        userMapper.updateUserInfo(info);
    }

    /**
     * 通过原密码修改密码
     *
     * @param info   用户信息
     * @param userId 用户id
     */
    @Override
    public void alterPwdVerifyByOldPwd(CustomUser info, Integer userId,String sessionVerifyCode) throws Exception{
        //验证码
        String verifyCode = info.getVerifyCode();
        System.out.println(verifyCode+".....修改密码....."+sessionVerifyCode);
        if(StringUtils.isEmpty(verifyCode) || StringUtils.isEmpty(sessionVerifyCode) || !verifyCode.equalsIgnoreCase(sessionVerifyCode)){
            throw new UnknownVerifyException("请输入正确的验证码");
        }

        //原密码
        String oldPwd = info.getUserPassword();
        if(!Regx.regxPassword(oldPwd)){
            throw new UnknownPasswordException("请输入正确的原密码");
        }

        //新密码
        String newPwd = info.getUserPassword1();
        if(!Regx.regxPassword(newPwd)){
            throw new UnknownPasswordException("请输入正确格式的新密码");
        }

        //确认密码
        String agaPwd = info.getUserPassword2();
        if(!newPwd.equals(agaPwd)){
            throw new UnknownPasswordException("两次输入的密码不一致");
        }

        //原密码加密
        oldPwd = MD5.parseMD5(oldPwd);

        //查询原密码是否正确
        Integer count = userMapper.selectUserCountByIdAndPwd(userId,oldPwd);
        if(count != 1){
            throw new UnknownPasswordException("请输入正确的原密码");
        }

        //新密码加密
        newPwd = MD5.parseMD5(newPwd);

        //修改密码
        userMapper.updatePwdById(userId,newPwd);
    }

    /**
     * 修改邮箱
     *
     * @param userId            用户id
     * @param verifyCode        用户输入的验证码
     * @param sessionVerifyCode session中的验证码
     * @param oldPwd            原密码
     * @param userUsername            用户名
     * @param userTel            手机号
     * @throws Exception 抛出异常
     */
    @Override
    public void updateEmail(String userUsername,String userTel,Integer userId, String verifyCode, String sessionVerifyCode, String oldPwd,String email) throws Exception {
        //验证码
        System.out.println(verifyCode+".....修改邮箱....."+sessionVerifyCode);
        if(StringUtils.isEmpty(verifyCode) || StringUtils.isEmpty(sessionVerifyCode) || !verifyCode.equalsIgnoreCase(sessionVerifyCode)){
            throw new UnknownVerifyException("请输入正确的验证码");
        }

        //原密码
        if(!Regx.regxPassword(oldPwd)){
            throw new UnknownPasswordException("请输入正确的密码");
        }

        //密码加密
        oldPwd = MD5.parseMD5(oldPwd);
        //查询密码是否正确
        Integer count = userMapper.selectUserCountByIdAndPwd(userId,oldPwd);
        if(count != 1){
            throw new UnknownPasswordException("请输入正确的密码");
        }

        //修改邮箱
        userMapper.updateEmailById(userId,email);

        if(!StringUtils.isEmpty(userTel)){
            Tel.sendTelCode(userTel,userUsername+"，您好！您成功绑定了邮箱："+email);
        }
        if(!StringUtils.isEmpty(email)){
            mailUtils.sendSimpleMail(email,"音梦-音梦而声",userUsername+"，您好！您成功绑定了邮箱："+email);
        }
    }

    /**
     * 发送原绑定手机号的验证码
     *
     * @param account 手机号
     * @return 验证码
     */
    @Override
    public String sendBindOldTelVerifyCodeService(String account) throws Exception {
        String code = Code.createVerifyCode(6,Code.VERIFY_CODE_TYPE_TEL);
        Tel.sendTelCode(account,"您的验证码是【"+code+"】，工作人员不会向您索取验证码，请勿泄露。");
        return code;
    }

    /**
     * 发送新绑定手机的验证码
     *
     * @param account 手机号
     * @return 验证码
     * @throws Exception 抛出异常
     */
    @Override
    public String sendBindNewTelVerifyCodeService(String account,String userTel) throws Exception {
        //判断手机号格式
        if(!Regx.regxTelphone(account)){
            throw new UnknownTelException("请输入正确格式的手机号");
        }
        //判断是否是当前登录的手机号
        if(account.equals(userTel)){
            throw new UnknownTelException("您当前绑定的就是这个手机号，无需换绑");
        }
        //判断数据库中是否已经有数据
        Integer count = userMapper.selectByTelToCount(account);
        if(count >= 1){
            throw new UnknownTelException("此手机号已被其他账号绑定");
        }
        //发送验证码
        String code = Code.createVerifyCode(6,Code.VERIFY_CODE_TYPE_TEL);
        Tel.sendTelCode(account,"您的验证码是【"+code+"】，工作人员不会向您索取验证码，请勿泄露。");
        return code;
    }

    /**
     * 绑定新的手机号
     *
     * @param userId            用户id
     * @param verifyCode        用户输入的验证码
     * @param sessionVerifyCode 会话中的验证码
     * @param account           手机号
     * @param userEmail           邮箱
     * @param userUsername           用户名
     * @throws Exception 抛出异常
     */
    @Override
    public void updateUserTel(String userEmail,String userUsername,Integer userId, String verifyCode, String sessionVerifyCode, String account) throws Exception {
        //验证码
        System.out.println(verifyCode+".....修改手机号....."+sessionVerifyCode);
        if(StringUtils.isEmpty(verifyCode) || StringUtils.isEmpty(sessionVerifyCode) || !verifyCode.equalsIgnoreCase(sessionVerifyCode)){
            throw new UnknownVerifyException("请输入正确的验证码");
        }

        //换绑
        userMapper.updateUserTelById(userId,account);

        if(!StringUtils.isEmpty(account)){
            Tel.sendTelCode(account,userUsername+"，您好！您成功绑定了手机号："+account);
        }
        if(!StringUtils.isEmpty(userEmail)){
            mailUtils.sendSimpleMail(userEmail,"音梦-音梦而声",userUsername+"，您好！您成功绑定了手机号："+account);
        }
    }

    /**
     * 设置密保问题
     *
     * @param loginedUser       登录用户
     * @param sessionVerifyCode 会话中的验证码
     * @param verifyCode        用户输入的验证码
     * @param answer            密保答案
     * @param question          密保问题
     * @param userPwd           用户输入的密码
     * @throws Exception 抛出异常
     */
    @Override
    public void updateEnsureQuestion(CustomUser loginedUser, String sessionVerifyCode, String verifyCode, String answer, String question, String userPwd) throws Exception {
        //验证码
        System.out.println(verifyCode+".....设置密保....."+sessionVerifyCode);
        if(StringUtils.isEmpty(verifyCode) || StringUtils.isEmpty(sessionVerifyCode) || !verifyCode.equalsIgnoreCase(sessionVerifyCode)){
            throw new UnknownVerifyException("请输入正确的验证码");
        }
        //密码判空
        if(!Regx.regxPassword(userPwd)){
            throw new UnknownPasswordException("请输入正确的密码");
        }
        //密保问题判空
        if(StringUtils.isEmpty(question)){
            throw new UnknownPasswordException("请选择密保问题");
        }
        //密保答案判空
        if(StringUtils.isEmpty(answer)){
            throw new UnknownPasswordException("请输入密保答案");
        }
        //判断密码是否正确
        userPwd = MD5.parseMD5(userPwd);
        if(!userPwd.equals(loginedUser.getUserPassword())){
            throw new UnknownPasswordException("请输入正确的密码");
        }
        //存入数据库的密保
        String userSafeQuestion = question+answer;

        //设置密保
        userMapper.updateEnsureQuestion(loginedUser.getUserId(),userSafeQuestion);
    }


}
