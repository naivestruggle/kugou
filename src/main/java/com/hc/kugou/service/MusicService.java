package com.hc.kugou.service;

import com.hc.kugou.bean.custombean.CustomMusic;
import com.hc.kugou.service.exception.music.NotFoundMusicException;
import com.hc.kugou.solr.SolrBean;

/**
 * @Author:
 * @Date:2019/4/19
 * @Description:com.hc.kugou.service
 * @Version:1.0
 */
public interface MusicService {
    /**
     * 根据查询关键词查询单曲
     * @param searchKey 查询关键词
     * @return  返回solrbean
     */
    SolrBean<CustomMusic> selectMusicBySearchKey(String searchKey);
}
