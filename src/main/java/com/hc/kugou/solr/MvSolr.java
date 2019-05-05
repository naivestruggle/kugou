package com.hc.kugou.solr;

import com.hc.kugou.bean.Music;
import com.hc.kugou.bean.Mv;
import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:
 * @Date:2019/4/5
 * @Description:com.hc.kugou.solr
 * @Version:1.0
 */
public class MvSolr {
    @Autowired
    private SolrClient client;

    private SolrManager<Mv> solrManager;


    public SolrBean<Mv> selectPopMvByClassName(String 华语, int i) {
        return null;
    }
}
