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
     * @throws Exception
     * @return
     */
    CustomMusicList selectMusicListById(Integer musicListId) throws Exception;


    /**
     * 添加歌单
     * @param musicListName 歌单名
     * @param session 里面存储着用户信息
     * @throws Exception
     * @return 歌单对象
     */
    CustomMusicList addSongSheet(String musicListName,HttpSession session) throws Exception;

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
     * @throws Exception
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

    /**
     * 添加歌曲到歌单中
     * @param musicId   歌曲id
     * @param musicListId  歌单id
     * @param session
     * @throws Exception
     */
    void addMusicToSongSheet(Integer musicListId, Integer musicId, HttpSession session) throws Exception;

    /**
     * 将歌曲从歌单中删除
     * @param musicId 歌曲id
     * @param musicListId  歌单id
     * @param session
     * @throws Exception
     */
    void delMusicFromSongSheet(Integer musicListId,Integer musicId, HttpSession session)throws Exception;

    /**
     * 根据用户id查询该用户下的所有歌单
     * @param userId 用户id
     * @param session
     * @return
     */
    List<CustomMusicList> querySongSheet(Integer userId, HttpSession session) throws Exception;


    /**
     * 根据歌单id查询用户歌单信息
     * @param musicListId 歌单id
     * @return
     * @throws Exception
     */
    CustomMusicList queryMySongSheetList(Integer musicListId)throws Exception;

    /**
     * 查询最热歌单
     * @return
     * @throws Exception
     */
    List<CustomMusicList> queryHotListenerSongSheet() throws Exception;

    /**
     * 查询热藏歌单
     * @return
     * @throws Exception
     */
    List<CustomMusicList> queryHotCollectSongSheet() throws Exception;

    /**
     * 将歌单收藏
     * @param musicListId 歌单id
     * @param session
     * @throws Exception
     */
    void collectSongSheet(Integer musicListId, HttpSession session) throws Exception;
}
