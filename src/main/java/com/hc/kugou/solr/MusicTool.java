package com.hc.kugou.solr;

/**
 * @Author:
 * @Date:2019/4/5
 * @Description:com.hc.kugou.solr
 * @Version:1.0
 */
public class MusicTool {
    private MusicTool(){}

    /**
     * 域  music_listener_count
     */
    public static final String MUSIC_LISTENER_COUNT_Field = "music_listener_count";

    /**
     * 域  music_class_name
     */
    public static final String MUSIC_CLASS_NAME_Field = "music_class_name";

    /**
     * 查询指定域
     */
    public static final String[] POINT_FIELDS_ALL = {
            "id",
            "music_id",
            "music_author_id",
            "music_author_name",
            "music_audio_id",
            "music_audio_name",
            "music_song_name",
            "music_hash_code",
            "music_filesize",
            "music_timelength",
            "music_have_album",
            "music_album_id",
            "music_album_name",
            "music_have_mv",
            "music_video_id",
            "music_privilege",
            "music_privilege2",
            "music_play_url",
            "music_img",
            "music_lyrics",
            "music_listener_count",
            "music_class_name"

    };
}
