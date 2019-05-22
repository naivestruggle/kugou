package com.hc.kugou.mapper;

import com.hc.kugou.bean.custombean.CustomMusicList;
import org.apache.ibatis.annotations.*;

import java.util.List;
import com.hc.kugou.bean.custombean.CustomMusicList;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 查询该歌单是否存在
     * @param musicListId 歌单id
     * @return 歌单对象
     */
    CustomMusicList queryMusicListById(@Param("musicListId") Integer musicListId);

    /**
     * 将歌曲添加到歌单中
     * @param musicId 歌曲id
     * @param musicListId 歌单id
     */
    @Insert("insert into kugou_musiclist_music(music_list_id,music_id) values(#{musicListId},#{musicId})")
    void addMusicToSongSheet(@Param("musicListId") Integer musicListId,@Param("musicId") Integer musicId);

    /**
     * 将歌曲从歌单中删除
     * @param musicId 歌曲id
     * @param musicListId 歌单id
     */
    @Delete("delete from kugou_musiclist_music where music_list_id = #{musicListId} and music_id = #{musicId}")
    void delMusicFromSongSheet(@Param("musicListId") Integer musicListId,@Param("musicId") Integer musicId);

    /**
     * 查询该歌单中是否已经存在该歌曲
     * @param musicId 歌曲id
     * @param musicListId 歌单id
     * @return
     */
    @Select("select count(1) from kugou_musiclist_music where music_list_id = #{musicListId} and music_id = #{musicId}")
    Integer queryMusicIsExists(@Param("musicListId") Integer musicListId,@Param("musicId") Integer musicId);

    /**
     * 根据用户id查询该用户的歌单
     * @param userId 用户id
     * @return
     */
    @Select("select music_list_id,music_list_name,music_list_music_count,music_list_mood,music_list_describe,music_list_head_image from kugou_musiclist where music_list_user_id = ${userId}")
    List<CustomMusicList> querySongSheet(@Param("userId") Integer userId);

    /**
     * 歌单歌曲数+1
     * @param musicListId 歌单id
     */
    @Update("update kugou_musiclist set music_list_music_count = music_list_music_count + 1 where music_list_id = #{musicListId}")
    void incrMusicCount(@Param("musicListId") Integer musicListId);

    /**
     * 歌单歌曲数-1
     * @param musicListId 歌单id
     */
    @Update("update kugou_musiclist set music_list_music_count = music_list_music_count - 1 where music_list_id = #{musicListId}")
    void decrMusicCount(Integer musicListId);

    /**
     * 查询热门歌单  根据歌单的播放数倒序
     * @return 热门歌单集合
     */
    @Select("select music_list_id,music_list_name,music_list_user_username,music_list_describe,music_list_head_image from kugou_musiclist order by music_list_listener_count desc LIMIT 0,40")
    List<CustomMusicList> queryHotListenerSongSheet();

    /**
     * 查询热藏歌单
     * @return 热藏歌单集合
     */
    @Select("select music_list_id,music_list_name,music_list_user_username,music_list_describe,music_list_head_image from kugou_musiclist order by music_list_collect_count desc LIMIT 0,40")
    List<CustomMusicList> queryHotCollectSongSheet();

    /**
     * 收藏歌单
     * @param musicListId 歌单id
     * @param userId 用户id
     */
    @Insert("insert into kugou_collect(music_list_id,user_id) values(#{musicListId},#{userId})")
    void collectSongSheet(@Param("musicListId") Integer musicListId,@Param("userId") Integer userId);

    /**
     * 查询歌单是否已被收藏
     * @param musicListId 歌单id
     * @param userId 用户id
     * @return
     */
    @Select("select count(1) from kugou_collect where music_list_id = #{musicListId} and user_id = #{userId}")
    Integer querySongSheetIsCollected(@Param("musicListId") Integer musicListId,@Param("userId") Integer userId);
}
