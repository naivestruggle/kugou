package com.hc.kugou.service;

import com.hc.kugou.bean.custombean.CustomMusicList;
import com.hc.kugou.solr.SolrBean;

import java.util.List;

/**
 * @author ck
 * @create 2019-05-14 19:30
 */
public interface SongSheetService {

    /**
     * 根据id查询歌单对象
     * @param musicListId
     * @return
     */
    CustomMusicList selectMusicListById(Integer musicListId);

    /**
     * 根据关键字查询歌单集合
     * @param searchKey 关键字
     * @return  歌单集合
     */
    SolrBean<CustomMusicList> selectMusicListSearchBySearchKey(String searchKey);
}
