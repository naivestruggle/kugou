package com.hc.kugou.controller;

import com.alibaba.fastjson.JSONObject;
import com.hc.commons.ResponseUtils;
import com.hc.kugou.bean.custombean.CustomMusicList;
import com.hc.kugou.service.SongSheetService;
import com.hc.kugou.solr.SolrBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ck
 * @create 2019-05-14 19:28
 */

@Controller
public class SongSheetController {

    @Autowired
    private SongSheetService songSheetService;

    private JSONObject jsonObject;

    @GetMapping("songsheetlist/{musicListId}")
    private String querySongSheetList(@PathVariable("musicListId") Integer musicListId, Model model){

        try {
            CustomMusicList customMusicList = songSheetService.selectMusicListById(musicListId);

            model.addAttribute("customMusicList",customMusicList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "songsheetlist";
    }

    /**
     * 搜索页面  异步获取查询到的歌单对象
     * @param searchKey 查询关键字
     * @return    查询结果
     */
    @ResponseBody
    @PostMapping("musiclist.search.ajax")
    public JSONObject fun1(String searchKey){
        System.out.println("进来了："+searchKey);
        JSONObject jsonObject = new JSONObject();
        SolrBean<CustomMusicList> lists = songSheetService.selectMusicListSearchBySearchKey(searchKey);
        jsonObject.put("lists",lists);
        return jsonObject;
    }

}
