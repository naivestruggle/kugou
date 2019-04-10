package com.hc.kugou.bean.custombean;

import com.hc.kugou.solr.SolrBean;
import lombok.Data;

import java.util.Map;

/**
 * @author ck
 * @create 2019-05-08 17:07
 */
@Data
public class MvViewBean {

    /**
     * 5首轮播图mv
     */
    //private SolrBean<CustomMv> slideGraphCollect;

    /**
     * 热播mv 10首
     */
    private SolrBean<CustomMv> topTenMvCollect;


    /**
     * 热门mv  20首
     */
    private Map<String,SolrBean<CustomMv>> hotMvCollect;

}
