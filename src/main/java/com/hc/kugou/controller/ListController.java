package com.hc.kugou.controller;

import com.alibaba.fastjson.JSONObject;
import com.hc.commons.ResponseUtils;
import com.hc.kugou.bean.custombean.CustomMusic;
import com.hc.kugou.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ck
 * @create 2019-05-27 16:08
 */
@RestController
public class ListController {

    private JSONObject jsonObject;

    @Autowired
    private ListService listService;

    /**
     * 展示初始化榜单页面
     * @return
     */
    @PostMapping("list.showInitHtml.ajax")
    public JSONObject showInitHtml(){
        jsonObject = new JSONObject();

        try {
            List<CustomMusic> musicList = listService.querySoarList();

            jsonObject.put("code",1);
            jsonObject.put("musicList",musicList);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseUtils.responseException(jsonObject,e);
        }

        return jsonObject;
    }

    /**
     * 查询top500歌曲
     * @return
     */
    @PostMapping("list.queryTop500.ajax")
    public JSONObject queryTop500(){
        jsonObject = new JSONObject();

        try {
            List<CustomMusic> musicList = listService.queryTop500();

            jsonObject.put("code",1);
            jsonObject.put("musicList",musicList);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseUtils.responseException(jsonObject,e);
        }

        return jsonObject;
    }

    /**
     * 查询新歌榜
     * @return
     */
    @PostMapping("list.queryNewList.ajax")
    public JSONObject queryNewList(Integer className){
        jsonObject = new JSONObject();

        try {
            List<CustomMusic> musicList = listService.queryNewCNList(className);
            jsonObject.put("code",1);
            jsonObject.put("musicList",musicList);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseUtils.responseException(jsonObject,e);
        }

        return jsonObject;
    }

}
