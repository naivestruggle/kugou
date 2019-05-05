package com.hc.kugou.bean;

import com.hc.kugou.solr.SolrBean;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author:
 * @Date:2019/4/30
 * @Description:com.hc.kugou.bean
 * @Version:1.0
 */
@Data
public class IndexViewBean implements Serializable {
    /**
     * 新歌集合  4种  华语China 欧美EAA 韩国Korea  日本japan   每种24首
     */
    private Map<String, SolrBean<Music>> newMusicCollect;

    /**
     * 热门歌手 4种  华语China 欧美EAA 韩国Korea  日本japan   每种15人
     */
    private Map<String,List<Singer>> popSingerCollect;

    /**
     * 推荐Mv
     */
    private Map<String,SolrBean<Mv>> recommendMvCollect;
}
