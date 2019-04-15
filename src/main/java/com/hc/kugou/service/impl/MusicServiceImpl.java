package com.hc.kugou.service.impl;

import com.hc.kugou.bean.custombean.CustomMusic;
import com.hc.kugou.service.MusicService;
import com.hc.kugou.service.exception.music.NotFoundMusicException;
import com.hc.kugou.solr.MusicSolr;
import com.hc.kugou.solr.SolrBean;
import com.hc.kugou.solr.SolrManager;
import com.hc.kugou.solr.solrtool.MusicTool;
import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:
 * @Date:2019/4/19
 * @Description:com.hc.kugou.service.impl
 * @Version:1.0
 */
@Service("musicService")
public class MusicServiceImpl implements MusicService {
    @Autowired
    private MusicSolr musicSolr;
    @Autowired
    private SolrClient client;
    /**
     * 根据查询关键词查询单曲
     *
     * @param searchKey 查询关键词
     * @return 返回solrbean
     */
    @Override
    public SolrBean<CustomMusic> selectMusicBySearchKey(String searchKey){
        SolrBean<CustomMusic> solrBean = musicSolr.selectMusicBySearchKey(searchKey,50);
        return solrBean;
    }
}
