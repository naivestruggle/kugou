package com.hc.kugou.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hc.commons.Code;
import com.hc.commons.MD5;
import com.hc.commons.QQHttpClient;
import com.hc.commons.StringUtils;
import com.hc.kugou.bean.custombean.CustomMusicList;
import com.hc.kugou.bean.custombean.CustomUser;
import com.hc.kugou.mapper.MusiclistMapper;
import com.hc.kugou.mapper.UserMapper;
import com.hc.kugou.service.QqService;
import com.hc.kugou.service.exception.qq.StateErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * @author ck
 * @create 2019-05-19 20:10
 */
@Service("qqService")
public class QqServiceImpl implements QqService {

    @Value("${qq.oauth.http}")
    private String http;

    @Resource
    private UserMapper userMapper;

    @Autowired
    private MusiclistMapper musiclistMapper;

    @Override
    public String qqRequest(HttpSession session) {

        //http://www.sansheng2019.cn/qq/callback 回调地址
        String backUrl = http + "/qq/callback";

        //用于第三方应用防止CSRF攻击
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        session.setAttribute(QQHttpClient.STATE,uuid);

        //Step1：获取Authorization Code
        String url = "https://graph.qq.com/oauth2.0/authorize?response_type=code"+
                "&client_id=" + QQHttpClient.APPID +
                "&redirect_uri=" + URLEncoder.encode(backUrl) +
                "&state=" + uuid;

        return url;
    }

    @Override
    public void qqCallback(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();

        String code = request.getParameter(QQHttpClient.CODE);
        String state = request.getParameter(QQHttpClient.STATE);
        String uuid = (String) session.getAttribute(QQHttpClient.STATE);

        if(uuid != null){
            if(!uuid.equals(state)){
                throw new StateErrorException("QQ,state错误");
            }
        }


        //Step2：通过Authorization Code获取Access Token
        String backUrl = http + "/qq/callback";
        String url = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code"+
                "&client_id=" + QQHttpClient.APPID +
                "&client_secret=" + QQHttpClient.APPKEY +
                "&code=" + code +
                "&redirect_uri=" + backUrl;

        String access_token = QQHttpClient.getAccessToken(url);

        //Step3: 获取回调后的 openid 值
        url = "https://graph.qq.com/oauth2.0/me?access_token=" + access_token;
        String openid = QQHttpClient.getOpenID(url);

        //Step4：获取QQ用户信息
        url = "https://graph.qq.com/user/get_user_info?access_token=" + access_token +
                "&oauth_consumer_key="+ QQHttpClient.APPID +
                "&openid=" + openid;

        JSONObject jsonObject = QQHttpClient.getUserInfo(url);


        //根据openid查数据库
        Integer integer = userMapper.queryByOpenIdCount(openid);
        CustomUser loginedUser = new CustomUser();
        if(integer == 0){
            //如果用户不存在
            loginedUser.setUserUsername((String)jsonObject.get(QQHttpClient.NICKNAME));
            loginedUser.setUserImgpath((String)jsonObject.get(QQHttpClient.FIGUREURL_QQ_2));
            loginedUser.setUserPassword(MD5.parseMD5(Code.DEFAULT_PWD));
            loginedUser.setUserAccount(Code.createUserAccount());
            loginedUser.setUserQq(openid);
            //存入数据库
            userMapper.insertUser(loginedUser);

            //为用户创建一个默认歌单 《我喜欢的音乐》
            CustomMusicList customMusicList = new CustomMusicList();
            customMusicList.setMusicListName(Code.DEFAULT_MUSIC_LIST_NAME);
            customMusicList.setMusicListUserId(loginedUser.getUserId());
            customMusicList.setMusicListUserUsername(loginedUser.getUserUsername());
            customMusicList.setMusicListUpdateTime(new java.sql.Date(System.currentTimeMillis()));
            customMusicList.setMusicListHashCode(UUID.randomUUID().toString().replaceAll("-",""));
            musiclistMapper.addSongSheet(customMusicList);
        }else{
            //用户存在，查询用户id
            loginedUser = userMapper.queryByOpenId(openid);
        }


        session.setAttribute(StringUtils.LOGINED_USER,loginedUser);
    }
}
