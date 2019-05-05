package com.hc.kugou.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.hc.commons.MvUtils;
import com.hc.kugou.bean.Music;
import com.hc.kugou.bean.Mv;
import com.hc.kugou.bean.Singer;
import com.hc.kugou.mapper.MusicMapper;
import com.hc.kugou.mapper.MvMapper;
import com.hc.kugou.mapper.SingerMapper;
import com.hc.kugou.service.IndexService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author:
 * @Date:2019/5/2
 * @Description:com.hc.kugou.controller
 * @Version:1.0
 */
@RestController
public class HelloController {
    @Autowired
    private SolrClient client;

    @PostMapping("find_music")
    public List<Music> findMusic(String queryStr)throws Exception{

        Long startTime = System.currentTimeMillis();

        //查询
        SolrQuery solrQuery = new SolrQuery();
        //关键词
        solrQuery.setQuery(queryStr);
        //过滤条件
//        solrQuery.setFilterQueries("author_name:出山");
//        solrQuery.setFilterQueries("filesize:[* TO 4000000]");
        //排序
        solrQuery.addSort("listener_count", SolrQuery.ORDER.desc);
        //分页
//        solrQuery.setStart(0);
//        solrQuery.setRows(100);
        //默认域
        solrQuery.set("df","audio_name");
        //只查询指定域
        solrQuery.set("fl","id,audio_id,audio_name,listener_count");
        //高亮
        //打开开关
        solrQuery.setHighlight(true);
        //指定高亮域
        solrQuery.addHighlightField("audio_name");
        //前缀
        solrQuery.setHighlightSimplePre("<font style='color:red;'>");
        //后缀
        solrQuery.setHighlightSimplePost("</font>");

        //执行查询
        QueryResponse response = client.query(solrQuery);
        //文档结果集
        SolrDocumentList docs = response.getResults();

        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        //Map K id  :  V Map
        //Map K 域名 ：V List
        //List  List.get(0)
        Long numFound = docs.getNumFound();
        Long endTime = System.currentTimeMillis();
        Long times = endTime - startTime;
        System.out.println("查到了："+numFound+",用时："+times+" ms");
        List<Music> musicList = new ArrayList<Music>();
        for(SolrDocument doc:docs){
            Map<String, List<String>> map = highlighting.get(doc.get("id"));
            List<String> list = map.get("audio_name");
            Music music = new Music();
            music.setMusicAudioName(list.get(0));
            musicList.add(music);
        }

        return musicList;
    }
}
