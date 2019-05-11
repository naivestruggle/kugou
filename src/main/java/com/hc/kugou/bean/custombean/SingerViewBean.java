package com.hc.kugou.bean.custombean;

import com.hc.kugou.solr.SolrBean;
import lombok.Data;

import java.util.Map;

/**
 * @author ck
 * @create 2019-05-09 16:24
 */
@Data
public class SingerViewBean {

    /**
     * 歌手集合
     */
    Map<String,SolrBean<CustomSinger>> singerCollect;

    /**
     * 歌手语种
     */
    Integer singerClassName;

    /**
     * A-Z
     */
    String singerSindex;

    /**
     * 当前页
     */
    Integer page;


    /**
     * 总页数
     */
    Integer totalPage;
}
