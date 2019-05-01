package com.hc.commons;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.hc.kugou.bean.Mv;
import com.sun.javafx.collections.MappingChange;
import org.springframework.boot.json.GsonJsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author:
 * @Date:2019/4/30
 * @Description:com.hc.commons
 * @Version:1.0
 */
public class MvUtils {
    private MvUtils(){}

    /**
     * 通过传入的音频名称查询Mv
     * @param audioName 音频名称
     * @return  Mv对象
     */
    public static Mv getMv(String audioName){
        Mv mv = new Mv();
        mv.setId(UUID.randomUUID().toString().replace("-",""));
        String exe = "python";
        String command = MvUtils.class.getResource("/python/mvSpider.py").toString().replace("file:/","").replace("/","\\");
        String[] cmdArr = new String[] { exe, command,audioName };
        StringBuffer sb = new StringBuffer();
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(cmdArr);
            InputStream inputStream = process.getInputStream();
            byte[] buff = new byte[1024*1024];
            int len = 0;
            while( ( len = inputStream.read(buff) ) != -1 ) {
                sb.append(new String(buff,0,len,"gbk"));
            }
            String result = sb.toString();
            Map<String,Map<String,Object>> map = (Map<String, Map<String, Object>>) JSONObject.parse(result);
            for(Iterator<Map.Entry<String,Map<String,Object>>> it = map.entrySet().iterator();it.hasNext();){
                Map.Entry<String,Map<String,Object>> me = it.next();
                String key = me.getKey();
                Map<String,Object> value = me.getValue();
                //设置mv名
                mv.setMvName(key);
                for(Iterator<Map.Entry<String,Object>> it1 = value.entrySet().iterator();it1.hasNext();){
                    Map.Entry<String,Object> me1 = it1.next();
                    String key1 = me1.getKey();
                    Object value1 = me1.getValue();
                    if(key1.equals("headImage")){
                        //设置封面
                        mv.setHeadImage((String)value1);
                    }else{
                        List<Map<String,String>> list = (List<Map<String, String>>) JSONObject.parse(value1.toString());
                        for(Map<String,String> map2 : list){
                            for(Iterator<Map.Entry<String,String>> it2 = map2.entrySet().iterator();it2.hasNext();){
                                Map.Entry<String,String> me2 = it2.next();
                                String key2 = me2.getKey();
                                String value2 = me2.getValue();
                                if(key2.equals("流畅")){
                                    mv.setHcUrl(value2);
                                }else if(key2.equals("高清")){
                                    mv.setHdUrl(value2);
                                }else if(key2.equals("超清")){
                                    mv.setBdUrl(value2);
                                }
                            }
                        }
                    }
                }
            }
            //设置更新该条数据时间
            mv.setUpdateTime(new Date(System.currentTimeMillis()));
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mv;
    }
}
