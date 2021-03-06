package com.hc.commons;

import com.alibaba.fastjson.JSONObject;
import com.hc.kugou.bean.Mv;
import org.springframework.beans.factory.annotation.Value;
import org.thymeleaf.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author:
 * @Date:2019/4/7
 * @Description:com.hc.commons
 * @Version:1.0
 */
public class PythonUtils {
    @Value("${python.path.type}")
    private String pythonPathType;

    @Value("${python.path.linux}")
    private String linuxPath;
    /**
     * 根据歌曲hashcode获取播放地址
     * @param musicHashCode 哈希值
     * @return  播放地址
     */
    public String getMusicPlayUrl(String musicHashCode){
        System.out.println("----------------根据歌曲的哈希值获取播放地址开始---------------------");
        System.out.println("python脚本的根路径:"+pythonPathType);
        System.out.println("音乐的哈希值："+musicHashCode);
        String exe = "python";
        String command = "";
        if("W".equals(pythonPathType)){
            command = PythonUtils.class.getResource("/python/download_songUrl.py").toString().replace("file:/","").replace("/", File.separator);
        }else if("L".equals(pythonPathType)){
            command = linuxPath+"download_songUrl.py";
        }
        System.out.println("python脚本真实路径："+command);
        String[] cmdArr = new String[] { exe, command,musicHashCode };
        StringBuffer sb = new StringBuffer();
        Process process = null;
        InputStream inputStream = null;
        String result = "";
        try {
            process = Runtime.getRuntime().exec(cmdArr);
            inputStream = process.getInputStream();
            byte[] buff = new byte[1024*1024];
            int len = 0;
            while( ( len = inputStream.read(buff) ) != -1 ) {
                sb.append(new String(buff,0,len,"gbk"));
            }
            result = sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("----------------根据歌曲的哈希值获取播放地址结束---------------------");
        return result;
    }

    /**
     * 通过传入的音频名称查询Mv
     * @param audioName 音频名称
     * @return  Mv对象
     */
    public Mv getMv(String audioName){
        Mv mv = new Mv();
        mv.setMvHashCode(UUID.randomUUID().toString().replace("-","").toUpperCase());
        String exe = "python";
        String command = "";
        if("W".equals(pythonPathType)){
            command = PythonUtils.class.getResource("/python/mvSpider.py").toString().replace("file:/","").replace("/","\\");
        }else if("L".equals(pythonPathType)){
            command = linuxPath+"mvSpider.py";
        }
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
            if (StringUtils.isEmpty(result)){
                return null;
            }
            Map<String, Map<String,Object>> map = (Map<String, Map<String, Object>>) JSONObject.parse(result);
            for(Iterator<Map.Entry<String,Map<String,Object>>> it = map.entrySet().iterator(); it.hasNext();){
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
                        mv.setMvHeadImage((String)value1);
                    }else{
                        List<Map<String,String>> list = (List<Map<String, String>>) JSONObject.parse(value1.toString());
                        for(Map<String,String> map2 : list){
                            for(Iterator<Map.Entry<String,String>> it2 = map2.entrySet().iterator();it2.hasNext();){
                                Map.Entry<String,String> me2 = it2.next();
                                String key2 = me2.getKey();
                                String value2 = me2.getValue();
                                if(key2.equals("流畅")){
                                    mv.setMvHcUrl(value2);
                                }else if(key2.equals("高清")){
                                    mv.setMvHdUrl(value2);
                                }else if(key2.equals("超清")){
                                    mv.setMvBdUrl(value2);
                                }
                            }
                        }
                    }
                }
            }
            //设置更新该条数据时间
            mv.setMvUpdateTime(new Date(System.currentTimeMillis()));
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mv;
    }
}
