package com.hc.kugou.solr;

import com.hc.kugou.bean.custombean.CustomMusic;
import com.hc.kugou.bean.custombean.CustomMv;
import com.hc.kugou.solr.solrtool.MusicTool;
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



    private SolrManager<CustomMusic> solrManager;



    /**
     * 查询新歌   按照播放量排序
     * @param className 歌曲语种
     * @param n 查询前n条
     * @return  查询的结果集
     */
    public SolrBean<CustomMusic> selectNewMusicByClassName(String className, int n) {
        if(this.solrManager == null) {
            this.solrManager = SolrManager.getInstance(CustomMusic.class, client);
        }
        SolrBean<CustomMusic> solrBean = solrManager.find(className,null,new String[]{MusicTool.MUSIC_AUDIO_ID_FIELD,MusicTool.MUSIC_LISTENER_COUNT_FIELD},SolrManager.SORT_RULE_DESC,0,n,
                new String[]{MusicTool.MUSIC_CLASS_NAME_FIELD},MusicTool.MUSIC_POINT_FIELDS_ALL,null);
        return solrBean;
    }

    /**
     * 根据ID查询歌曲
     * @param musicId
     * @return
     */
    public SolrBean<CustomMusic> selectMusicById(Integer musicId) {
        if(this.solrManager == null) {
            this.solrManager = SolrManager.getInstance(CustomMusic.class, client);
        }
        SolrBean<CustomMusic> solrBean = solrManager.find(musicId+"",null,null,null,0,1,
                new String[]{MusicTool.MUSIC_ID_FIELD},MusicTool.MUSIC_POINT_FIELDS_ALL,null);
        return solrBean;
    }

    /**
     * 根据传入的musicId查询music集合
     * @param musicId   id  多值以英文逗号分割
     * @return  SolrBean
     */
    public SolrBean<CustomMusic> selectMusicByMusicPlayListMusicId(String musicId){
        return null;
    }
}
