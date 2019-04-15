package com.hc.commons;


import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @Author:杨鑫虎
 * @Date:2019/4/26
 * @Description:com.hc.commons.telephone
 * @Version:1.0
 */
public class Tel {

    private static String smsKey = "d41d8cd98f00b204e980";
    private static String smsUsername = "naivestruggleFLi";
    private static String smsUrl = "http://utf8.api.smschinese.cn";
    /**
     * 发送短信
     * @param toTel
     * @param content
     * @throws IOException
     * @throws HttpException
     * @throws UnsupportedEncodingException
     */
    public static void sendTelCode(String toTel,String content){
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(smsUrl);  //"http://utf8.api.smschinese.cn"
        post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");// 在头文件中设置转码
        NameValuePair[] data = { new NameValuePair("Uid",smsUsername ),  //"naivestruggle"
                new NameValuePair("Key", smsKey), //"d41d8cd98f00b204e980"
                new NameValuePair("smsMob", toTel), //"15570906290"
                new NameValuePair("smsText", content) };//"验证码：8888"
        post.setRequestBody(data);

        try {
            client.executeMethod(post);
        } catch (HttpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
//        System.out.println("statusCode:" + statusCode);
//        for (Header h : headers) {
//            System.out.println(h.toString());
//        }
        String result = null;
        try {
            result = new String(post.getResponseBodyAsString().getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        System.out.println(result); // 打印返回消息状态

        post.releaseConnection();
    }

}
