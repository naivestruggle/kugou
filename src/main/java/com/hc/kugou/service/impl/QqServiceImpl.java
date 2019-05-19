package com.hc.kugou.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hc.commons.QQHttpClient;
import com.hc.commons.StringUtils;
import com.hc.kugou.bean.custombean.CustomUser;
import com.hc.kugou.service.QqService;
import com.hc.kugou.service.exception.qq.StateErrorException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

        //也可以放到Redis和mysql中
        CustomUser loginedUser = new CustomUser();
        loginedUser.setUserUsername((String)jsonObject.get(QQHttpClient.NICKNAME));
        loginedUser.setUserImgpath((String)jsonObject.get(QQHttpClient.FIGUREURL_QQ_2));

        session.setAttribute(StringUtils.LOGINED_USER,loginedUser);
        session.setAttribute(QQHttpClient.OPENID,openid);

    }
}
