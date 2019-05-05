package com.hc.kugou.solr;

import com.hc.kugou.bean.Music;
import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:
 * @Date:2019/4/5
 * @Description:com.hc.kugou.solr
 * @Version:1.0
 */
public class MusicSolr {
    @Autowired
    private SolrClient client;



    private SolrManager<Music> solrManager;

    {
        solrManager = SolrManager.getInstance(Music.class,client);
    }

    /**
     * 查询新歌   按照播放量排序
     * @param className 歌曲语种
     * @param n 查询前n条
     * @return  查询的结果集
     */
    public SolrBean<Music> selectNewMusicByClassName(String className, int n) {
        solrManager = SolrManager.getInstance(Music.class,client);
        SolrBean<Music> solrBean = solrManager.find(className,null,MusicTool.MUSIC_LISTENER_COUNT_Field,SolrManager.SORT_RULE_DESC,0,n,
                new String[]{MusicTool.MUSIC_CLASS_NAME_Field},MusicTool.POINT_FIELDS_ALL,null);
        return solrBean;
    }
}
