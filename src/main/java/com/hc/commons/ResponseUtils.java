package com.hc.commons;

import com.alibaba.fastjson.JSONObject;
import com.hc.kugou.service.exception.CustomException;


/**
 * 处理返回信息
 */
public class ResponseUtils {

    private ResponseUtils(){}

    /**
     * 出现异常响应处理
     * 返回结果码为 0（业务异常） 或 -1（系统异常）
     * @param responseBean  响应对象
     * @param e 异常对象
     */
    public static void responseException(JSONObject responseBean,Exception e){
        if(e instanceof CustomException){
            responseBean.put("code", 0);
            responseBean.put("msg", e.getMessage());
        }else{
            responseBean.put("code", -1);
        }

    }

    /**
     * 没有出现异常
     * 返回结果码为1
     * @param responseBean  响应对象
     */
    public static void responseNoException(JSONObject responseBean){
        responseBean.put("code", 1);
    }

}
