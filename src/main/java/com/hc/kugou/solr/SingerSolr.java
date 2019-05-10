package com.hc.kugou.solr;

import com.hc.kugou.bean.Singer;
import com.hc.kugou.solr.solrtool.SingerTool;
import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:
 * @Date:2019/4/5
 * @Description:com.hc.kugou.solr
 * @Version:1.0
 */
public class SingerSolr {
    @Autowired
    private SolrClient client;

    private SolrManager<Singer> solrManager;

    public SingerSolr(){

    }
    /**
     * 查找热门歌手
     * @param className 语种
     * @param n 查询条数
     * @return  集合
     */
    public SolrBean<Singer> selectSingerByClassName(String className, int n) {
        if(this.solrManager == null) {
            this.solrManager = solrManager = SolrManager.getInstance(Singer.class,client);
        }
        SolrBean<Singer> solrBean = solrManager.find(className,null,new String[]{SingerTool.SINGER_LISTENER_COUNT_FIELD},SolrManager.SORT_RULE_DESC,0,n,
            new String[]{SingerTool.SINGER_CLASS_NAME_FIELD},SingerTool.SINGER_POINTER_FIELD,null);
        return solrBean;
    }

    /**
     * 查询展示Singer页面所需要的数据
     * @param singerClassName  语种
     * @param singerSindex     A-Z
     * @param page             当前页
     *
     *  第一页 50 位歌手  后面页 63 位歌手
     */
    public void singer(int singerClassName, String singerSindex, int page) {


    }
}
