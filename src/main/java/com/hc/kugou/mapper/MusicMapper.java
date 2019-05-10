package com.hc.kugou.mapper;

import com.hc.kugou.bean.Music;import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author:
 * @Date:2019/4/30
 * @Description:com.hc.kugou.mapper
 * @Version:1.0
 */
public interface MusicMapper {
    /**
     * 根据语种查询新发歌曲  查前n条
     *
     * @param className 语种
     * @param n         查询的条数
     * @return 歌曲集合
     */
    List<Music> selectNewMusicByClassName(@Param("className")String className, @Param("n")Integer n);

    /**
     * 根据ID查询歌曲对象
     *
     * @param id 歌曲ID
     * @return 歌曲对象
     */
    Music selectMusicById(@Param("id")Long id);

    /**
     * 根据语种查询推荐歌曲  查前n条
     * @param className 语种
     * @param n 查询的条数
     * @return  歌曲集合
     */
    List<Music> selectRecommendMusicByClassName(@Param("className")String className, @Param("n")int n);

    /**
     * 修改是否有mv
     *
     * @param isHasMv
     */
    void updateHasMv(@Param("id") Long id, @Param("mvId") Long mvId, @Param("isHasMv") int isHasMv, @Param("className")String className);

    /**
     * 查询music
     *
     * @param start 起始位置
     * @param end   终止位置
     * @return
     */
    List<Music> selectCopy(@Param("start")int start, @Param("end")int end);

    /**
     * 查询记录条数
     * @return  返回记录条数
     */
    @Select("select count(1) from kugou_music")
    Long selectMusicCount();

    /**
     * 修改歌名
     *
     * @param musicId
     * @param newName
     */
    @Update("update kugou_music set music_song_name=#{newName} where music_id=#{musicId}")
    void updateMusicSongName(@Param("musicId")long musicId, @Param("newName")String newName);

    @Update("update kugou_music set music_hash_code=#{hashCode} where music_id=#{id}")
    void updateHashCode(@Param("id")Integer id, @Param("hashCode")String hashCode);
}
