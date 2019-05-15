package com.hc.kugou.service;

import com.hc.kugou.bean.custombean.CustomMusicList;
import com.hc.kugou.solr.SolrBean;

import java.util.List;

import javax.servlet.http.HttpSession;

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
    CustomMusicList selectMusicListById(Integer musicListId) throws Exception;
//    /**
//     * 根据歌单id查询
//     * @param musicListId  歌单id
//     * @throws Exception
//     * @return
//     */
//    CustomMusicList selectMusicListById(Integer musicListId) throws Exception;

    /**
     * 添加歌单
     * @param customMusicList 歌单对象
     * @param session 里面存储着用户信息
     * @throws Exception
     */
    void addSongSheet(CustomMusicList customMusicList,HttpSession session) throws Exception;

    /**
     * 根据关键字查询歌单集合
     * @param searchKey 关键字
     * @return  歌单集合
     */
    SolrBean<CustomMusicList> selectMusicListSearchBySearchKey(String searchKey);
    /**
     * 删除歌单
     * @param musicListId 歌单id
     * @param session
     */
    void delSongSheet(Integer musicListId, HttpSession session) throws Exception;

    /**
     * 修改歌单信息
     * @param customMusicList 歌单对象
     * @param session
     * @return
     * @throws Exception
     */
    CustomMusicList updateSongSheet(CustomMusicList customMusicList, HttpSession session) throws Exception;
}
