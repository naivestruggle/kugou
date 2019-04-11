package com.hc.kugou.solr;

import com.hc.kugou.bean.Singer;
import com.hc.kugou.bean.custombean.CustomSinger;
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

    private SolrManager<CustomSinger> solrManager;

    public SingerSolr(){

    }
    /**
     * 查找热门歌手
     * @param className 语种
     * @param n 查询条数
     * @return  集合
     */
    public SolrBean<CustomSinger> selectSingerByClassName(String className, int n) {
        if(this.solrManager == null) {
            this.solrManager = SolrManager.getInstance(Singer.class,client);
        }
        SolrBean<CustomSinger> solrBean = solrManager.find(className,null,new String[]{SingerTool.SINGER_LISTENER_COUNT_FIELD},SolrManager.SORT_RULE_DESC,0,n,
            new String[]{SingerTool.SINGER_CLASS_NAME_FIELD},SingerTool.SINGER_POINTER_FIELD,null);
        return solrBean;
    }

    /**
     * 查询展示Singer页面所需要的数据
     * @param singerClassName  语种
     * @param singerSindex     A-Z
     * @param page             起始行
     *
     */
    public SolrBean<CustomSinger> singer(int singerClassName, String singerSindex, int page) {
        if(this.solrManager == null) {
            this.solrManager  = SolrManager.getInstance(Singer.class,client);
        }
        if("ALL".equals(singerSindex)){
            singerSindex = "*";
        }
        SolrBean<CustomSinger> singerSolrBean = solrManager.find(singerClassName + "", new String[]{"singer_sindex:" + singerSindex}, new String[]{SingerTool.SINGER_LISTENER_COUNT_FIELD}, SolrManager.SORT_RULE_DESC, page, 36,
                new String[]{SingerTool.SINGER_FLAG_FIELD}, SingerTool.SINGER_POINTER_FIELD, null);
        return singerSolrBean;

    }

    /**
     * 查询所有歌手
     * @param singerSindex
     * @param page 起始行
     */
    public  SolrBean<CustomSinger> queryAllSinger(String singerSindex, int page) {
        if(this.solrManager == null) {
            this.solrManager  = SolrManager.getInstance(Singer.class,client);
        }
        if("ALL".equals(singerSindex)){
            singerSindex = "*";
        }
        SolrBean<CustomSinger> singerSolrBean = solrManager.find("singer_class_name:*", new String[]{"singer_sindex:" + singerSindex}, new String[]{SingerTool.SINGER_LISTENER_COUNT_FIELD}, SolrManager.SORT_RULE_DESC, page, 36,
                    new String[]{SingerTool.SINGER_CLASS_NAME_FIELD}, SingerTool.SINGER_POINTER_FIELD, null);
        return singerSolrBean;
    }
}
