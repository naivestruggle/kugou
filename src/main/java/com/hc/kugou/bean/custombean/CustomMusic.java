package com.hc.kugou.bean.custombean;

import com.hc.kugou.bean.Music;
import lombok.Data;
import org.thymeleaf.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:
 * @Date:2019/4/6
 * @Description:com.hc.kugou.bean
 * @Version:1.0
 */
@Data
public class CustomMusic extends Music implements Serializable {
    /**
     * mv对象
     */
    private CustomMv mv;
    /**
     * 音乐的时间
     */
    private String musicTimes;
    public String getMusicTimes(){
        Long times = getMusicTimelength();
        if(times == null){
            return "";
        }
        //分钟
        String start = "";
        long min = times/60000;
        if(min < 10){
            start = "0"+min;
        }else {
            start = min + "";
        }
        //秒
        String end = "";
        long sec = times%60000;
        end = sec+"";
        if(end.length() > 2){
            end = end.substring(0,2);
        }
        if(end.length() < 2){
            end = end + "0";
        }
        return start + ":" + end;
    }

    /**
     * 歌词集合
     */
    private List<String[]> musicLyricsList;
    public List<String[]> getMusicLyricsList(){
        String string = getMusicLyrics();
        if(StringUtils.isEmpty(string)){
            return null;
        }
        String[] hang = string.split("\n");
        List<String[]> list = new ArrayList<String[]>();
        for(String hangString:hang){
            String[] arr = hangString.split("]");
            arr[0] = arr[0].replace("[","");
            list.add(arr);
        }
        return list;
    }


    @Override
    public String getMusicAuthorName(){
        String str = super.getMusicAuthorName();
        return str.substring(0,str.length()-1);
    }

    @Override
    public String getMusicAlbumName(){
        String str = super.getMusicAlbumName();
        return str.substring(0,str.length()-1);
    }
}
