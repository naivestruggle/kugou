package com.hc.kugou.mapper;

import com.hc.kugou.bean.custombean.CustomMusic;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ck
 * @create 2019-05-27 16:21
 */
public interface ListMapper {
    /**
     * 查询飙升榜单
     * @return
     */
    @Select("select music_id,music_audio_name,music_timelength,music_incr_listener from kugou_music ORDER BY music_incr_listener desc LIMIT 0,20")
    List<CustomMusic> querySoarList();

    /**
     * 查询top500歌曲
     * @return
     */
    @Select("select music_id,music_audio_name,music_timelength,music_incr_listener from kugou_music ORDER BY music_audio_id desc LIMIT 0,20")
    List<CustomMusic> queryTop500();
}
