package com.hc.commons;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author ck
 * @create 2019-05-18 20:32
 */
public class QQHttpClient {

    public static final String APPID = "101581466";

    public static final String APPKEY = "6169bc6e4ed0704cdff86f3a4bad4549";

    public static final String CODE = "code";

    public static final String STATE = "state";

    /**
     * 返回码
     */
    public static final String RET = "ret";
    /**
     * 如果ret<0，会有相应的错误信息提示，返回数据全部用UTF-8编码。
     */
    public static final String MSG = "msg";

    /***
     * qq用户唯一表示
     */
    public static final String OPENID = "openid";
    /**
     * 用户在QQ空间的昵称。
     */
    public static final String NICKNAME = "nickname";
    /**
     * 大小为30×30像素的QQ空间头像URL。
     */
    public static final String FIGUREURL = "figureurl";
    /**
     * 大小为50×50像素的QQ空间头像URL。
     */
    public static final String FIGUREURL_1 = "figureurl_1";
    /**
     * 大小为100×100像素的QQ空间头像URL。
     */
    public static final String FIGUREURL_2 = "figureurl_2";
    /**
     * 大小为40×40像素的QQ头像URL。
     */
    public static final String FIGUREURL_QQ_1 = "figureurl_qq_1";
    /**
     * 大小为100×100像素的QQ头像URL。需要注意，不是所有的用户都拥有QQ的100x100的头像，但40x40像素则是一定会有。
     */
    public static final String FIGUREURL_QQ_2 = "figureurl_qq_2";
    /**
     * 性别。 如果获取不到则默认返回"男"
     */
    public static final String GENDER = "gender";


    /**
     * 对callback( {"client_id":"YOUR_APPID","openid":"YOUR_OPENID"} );格式的字符串解析,得到json格式的字符串
     * @param jsonp
     * @return
     */
    private static JSONObject parseJSONP(String jsonp){
        int startIndex = jsonp.indexOf("(");
        int endIndex = jsonp.lastIndexOf(")");

        String json = jsonp.substring(startIndex + 1,endIndex);

        return JSONObject.parseObject(json);
    }

    /**
     * 得到AccessToken
     * @param url
     * @return
     * @throws IOException
     */
    public static String getAccessToken(String url) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        String token = null;

        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        /**
         * 如果成功返回，即可在返回包中获取到Access Token。 如：
         * access_token=FE04************************CCE2&expires_in=7776000&refresh_token=88E4************************BE14
         */
        if(entity != null){
            String result = EntityUtils.toString(entity,"UTF-8");
            if(result.indexOf("access_token") >= 0){
                String[] array = result.split("&");
                for (String str : array){
                    if(str.indexOf("access_token") >= 0){
                        token = str.substring(str.indexOf("=") + 1);
                        break;
                    }
                }
            }
        }

        httpGet.releaseConnection();
        return token;
    }

    /**
     * 得到OpenID
     * @param url
     * @return
     * @throws IOException
     */
    public static String getOpenID(String url) throws IOException {
        JSONObject jsonObject = null;
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();

        //返回值为 callback( {"client_id":"YOUR_APPID","openid":"YOUR_OPENID"} );
        if(entity != null){
            String result = EntityUtils.toString(entity,"UTF-8");
            jsonObject = parseJSONP(result);
        }

        httpGet.releaseConnection();

        if(jsonObject != null){
            return jsonObject.getString("openid");
        }else {
            return null;
        }
    }

    /**
     * 得到用户信息
     * @param url
     * @return
     * {
     * "ret":0,
     * "msg":"",
     * "nickname":"Peter",
     * "figureurl":"http://qzapp.qlogo.cn/qzapp/111111/942FEA70050EEAFBD4DCE2C1FC775E56/30",
     * "figureurl_1":"http://qzapp.qlogo.cn/qzapp/111111/942FEA70050EEAFBD4DCE2C1FC775E56/50",
     * "figureurl_2":"http://qzapp.qlogo.cn/qzapp/111111/942FEA70050EEAFBD4DCE2C1FC775E56/100",
     * "figureurl_qq_1":"http://q.qlogo.cn/qqapp/100312990/DE1931D5330620DBD07FB4A5422917B6/40",
     * "figureurl_qq_2":"http://q.qlogo.cn/qqapp/100312990/DE1931D5330620DBD07FB4A5422917B6/100",
     * "gender":"男",
     * "is_yellow_vip":"1",
     * "vip":"1",
     * "yellow_vip_level":"7",
     * "level":"7",
     * "is_yellow_year_vip":"1"
     * }
     * @throws IOException
     */
    public static JSONObject getUserInfo(String url) throws IOException {
        JSONObject jsonObject = null;
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();


        if(entity != null){
            String result = EntityUtils.toString(entity,"UTF-8");
            jsonObject = JSONObject.parseObject(result);
        }

        httpGet.releaseConnection();

        return jsonObject;
    }
}
