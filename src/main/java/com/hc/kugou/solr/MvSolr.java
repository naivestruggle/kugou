package com.hc.kugou.solr;

import com.hc.kugou.bean.Mv;
import com.hc.kugou.bean.custombean.CustomMv;
import com.hc.kugou.solr.solrtool.MvTool;
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

    private SolrManager<CustomMv> solrManager;

    public MvSolr(){

    }

    /**
     * 查询热门Mv
     * @param n 查询数量
     * @return  结果集
     */
    public SolrBean<CustomMv> selectPopMv(int n) {
        if(this.solrManager == null) {
            this.solrManager = SolrManager.getInstance(CustomMv.class, client);
        }
        SolrBean<CustomMv> solrBean = solrManager.find("mv_class_name:*",null,new String[]{MvTool.MV_LISTENER_COUNT_FIELD},SolrManager.SORT_RULE_DESC,0,n,
                new String[]{MvTool.MV_CLASS_NAME_FIELD},MvTool.MV_POINT_FIELDS_ALL,null);
        return solrBean;
    }

    /**
     * 根据ID查询mv
     * @param mvId  mvID
     * @return  结果集
     */
    public SolrBean<CustomMv> selectMvById(Integer mvId) {
        if(this.solrManager == null) {
            this.solrManager = SolrManager.getInstance(CustomMv.class, client);
        }
        SolrBean<CustomMv> solrBean = solrManager.find(mvId+"",null,null,null,0,1,
                new String[]{MvTool.MV_ID_FIELD},MvTool.MV_POINT_FIELDS_ALL,null);
        return solrBean;
    }

    /**
     * 根据歌手名查询mv
     * @param singerName  歌手名
     * @return
     */
    public SolrBean<CustomMv> selectMvBySingerName(String singerName){
        if(this.solrManager == null) {
            this.solrManager = SolrManager.getInstance(CustomMv.class, client);
        }
        SolrBean<CustomMv> solrBean = solrManager.find(singerName,null,new String[]{MvTool.MV_LISTENER_COUNT_FIELD},1,0,10,
                new String[]{MvTool.MV_NAME_FIELD},MvTool.MV_POINT_FIELDS_ALL,null);
        return solrBean;

    }

    /**
     * 根据不同语种查询热门mv
     * @param className 语种
     * @param n  当前页数
     * @return
     */
    public SolrBean<CustomMv> selectHotMvByClassName(String className, int n){
        if(this.solrManager == null) {
            this.solrManager = SolrManager.getInstance(CustomMv.class, client);
        }
        SolrBean<CustomMv> solrBean = solrManager.find(className, null, new String[]{MvTool.MV_LISTENER_COUNT_FIELD}, 1, n, 20,
                new String[]{MvTool.MV_CLASS_NAME_FIELD}, MvTool.MV_POINT_FIELDS_ALL, null);
        return solrBean;
    }

    /**
     * 查询最新mv
     * @param n 当前页数
     * @return
     */
    public SolrBean<CustomMv> selectNewMv(int n) {
        if(this.solrManager == null) {
            this.solrManager = SolrManager.getInstance(CustomMv.class, client);
        }
        SolrBean<CustomMv> solrBean = solrManager.find("mv_class_name:*", null, new String[]{MvTool.MV_LISTENER_COUNT_FIELD}, 1, n, 20,
                new String[]{MvTool.MV_NAME_FIELD}, MvTool.MV_POINT_FIELDS_ALL, null);
        return solrBean;
    }


    /**
     * 5首轮播图上的mv
     * @return
     */
    public SolrBean<CustomMv> selectHotFiveMv() {
        if(this.solrManager == null) {
            this.solrManager = SolrManager.getInstance(CustomMv.class, client);
        }
        SolrBean<CustomMv> solrBean = solrManager.find("mv_class_name:*", null, new String[]{MvTool.MV_LISTENER_COUNT_FIELD}, 1, 0, 5,
                new String[]{MvTool.MV_NAME_FIELD}, MvTool.MV_POINT_FIELDS_ALL, null);
        return solrBean;
    }
}
