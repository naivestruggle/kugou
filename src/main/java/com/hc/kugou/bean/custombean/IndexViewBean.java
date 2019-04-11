package com.hc.kugou.bean.custombean;

import com.hc.kugou.bean.Mv;
import com.hc.kugou.bean.Singer;
import com.hc.kugou.bean.custombean.CustomMusic;
import com.hc.kugou.solr.SolrBean;
import lombok.Data;

import java.io.Serializable;
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
    private Map<String, SolrBean<CustomMusic>> newMusicCollect;

    /**
     * 热门歌手 4种  华语China 欧美EAA 韩国Korea  日本japan   每种15人
     */
    private Map<String,SolrBean<CustomSinger>> popSingerCollect;

    /**
     * 推荐Mv
     */
    private SolrBean<CustomMv> recommendMvCollect;
}
