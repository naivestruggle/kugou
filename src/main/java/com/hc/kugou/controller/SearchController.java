package com.hc.kugou.controller;

import com.alibaba.fastjson.JSONObject;
import com.hc.commons.StringUtils;
import com.hc.kugou.bean.custombean.CustomMusic;
import com.hc.kugou.bean.custombean.CustomMv;
import com.hc.kugou.service.MusicService;
import com.hc.kugou.service.MvService;
import com.hc.kugou.solr.SolrBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:
 * @Date:2019/4/16
 * @Description:com.hc.kugou.controller
 * @Version:1.0
 */
@Controller
public class SearchController {
    @Autowired
    private MusicService musicService;

    @Autowired
    private MvService mvService;

    @PostMapping("search.getSearchInfo.ajax")
    @ResponseBody
    public JSONObject fun1(String searchKey){
        JSONObject jsonObject = new JSONObject();
        SolrBean<CustomMusic> solrBean = musicService.selectMusicBySearchKey(searchKey,5);
        SolrBean<CustomMv> solrBean1 = mvService.selectMvBySearchKey(searchKey,2);
        jsonObject.put("musicList",solrBean);
        jsonObject.put("mvList",solrBean1);
        return jsonObject;
    }
}
