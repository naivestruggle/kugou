package com.hc.kugou.solr;

import com.hc.kugou.bean.custombean.CustomMusicList;
import com.hc.kugou.bean.custombean.CustomMusicPlayList;
import com.hc.kugou.solr.SolrBean;
import com.hc.kugou.solr.SolrManager;
import com.hc.kugou.solr.solrtool.MusicListTool;
import com.hc.kugou.solr.solrtool.MusicTool;
import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:
 * @Date:2019/4/15
 * @Description:com.hc.kugou.solr.solrtool
 * @Version:1.0
 */
public class MusicListSolr {
    @Autowired
    private SolrClient client;
    private SolrManager<CustomMusicList> manager;

    /**
     * 根据关键词搜索歌单集合
     * @param searchKey 关键词
     * @param n 搜索前多少条
     * @return  歌单集合
     */
    public SolrBean<CustomMusicList> selectMusicListSearchBySearchKey(String searchKey, int n) {
        if(manager == null){
            manager = SolrManager.getInstance(CustomMusicList.class,client);
        }
        //"music_audio_name:毛不易 or music_song_name:毛不易 or music_class_name:毛不易 or music_album_name:毛不易 or music_author_name:毛不易";
        return manager.find("music_list_name:"+searchKey,null, new String[]{MusicListTool.MUSIC_LIST_LISTENER_COUNT},
                SolrManager.SORT_RULE_DESC,0,50,null, MusicListTool.MUSIC_LIST_ALL,MusicListTool.MUSIC_LIST_NAME,"<em>","</em>");
    }
}
