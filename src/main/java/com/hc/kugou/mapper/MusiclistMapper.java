package com.hc.kugou.mapper;

import com.hc.kugou.bean.custombean.CustomMusicList;

/**
 * @Author:
 * @Date:2019/4/5
 * @Description:com.hc.kugou.mapper
 * @Version:1.0
 */
public interface MusiclistMapper {

    /**
     * 查询记录条数
     * @return  记录条数
     */
    Long selectMusiclistCount();

    /**
     * 根据歌单id查询歌曲集合
     * @param musicListId 歌曲id
     * @return 歌曲集合
     */
    CustomMusicList selectMusicListById(Integer musicListId);
}
