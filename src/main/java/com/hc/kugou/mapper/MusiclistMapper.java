package com.hc.kugou.mapper;

import com.hc.kugou.bean.custombean.CustomMusicList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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


    /**
     * 根据用户id和歌单名统计歌单数
     * @param customMusicList 歌单对象
     * @return
     */
    @Select("select count(1) from kugou_musiclist where music_list_user_id = #{musicListUserId} and music_list_name = #{musicListName}")
    Integer selectMusiclistCountByIdName(CustomMusicList customMusicList);

    /**
     * 添加歌单
     * @param customMusicList 歌单对象
     */
    void addSongSheet(CustomMusicList customMusicList);

    /**
     * 删除当前歌单下的所有歌曲
     * @param musicListId 歌单id
     */
    @Delete("delete from kugou_musiclist_music where music_list_id = #{musicListId}")
    void delAllSongInList(Integer musicListId);

    /**
     * 删除歌单
     * @param musicListId 歌单id
     * @param userId 用户id
     */
    @Delete("delete from kugou_musiclist where music_list_id = #{musicListId} and music_list_user_id = #{userId}")
    void delSongSheet(@Param("musicListId") Integer musicListId,@Param("userId") Integer userId);

    /**
     * 修改歌单信息
     * @param customMusicList 歌单对象
     */
    void updateSongSheet(CustomMusicList customMusicList);
}
