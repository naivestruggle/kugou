package com.hc.kugou.bean;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * @Date:2019/4/30
 * @Description:com.hc.kugou.bean  音乐类
 * @Version:1.0
 */
@Data
public class Music implements Serializable {
    /**
     * ID
     */
    private Integer musicId;

    /**
     * 作者ID
     */
    private Long musicAuthorId;

    /**
     * 作者姓名
     */
    private String musicAuthorName;

    /**
     * 音频ID
     */
    private Long musicAudioId;

    /**
     * 音频名
     */
    private String musicAudioName;

    /**
     * 歌曲名
     */
    private String musicSongName;

    /**
     * 哈希值
     */
    private String musicHashCode;

    /**
     * 歌曲文件大小
     */
    private Long musicFilesize;

    /**
     * 歌曲时长
     */
    private Long musicTimelength;


    /**
     * 是否有唱片集
     */
    private Integer musicHaveAlbum;


    /**
     * 唱片集ID
     */
    private Long musicAlbumId;

    /**
     * 唱片集名
     */
    private String musicAlbumName;

    /**
     * 是否有mv
     */
    private Integer musicHaveMv;


    /**
     * mvID
     */
    private Integer musicVideoId;


    /**
     * 权限
     */
    private Integer musicPrivilege;
    /**
     * 权限2
     */
    private Integer musicPrivilege2;

    /**
     * 歌曲播放链接
     */
    private String musicPlayUrl;

    /**
     * 图片路径
     */
    private String musicImg;

    /**
     * 歌词
     */
    private String musicLyrics;

    /**
     * 听这首歌的人数
     */
    private Long musicListenerCount;

    /**
     * 语种
     */
    private String musicClassName;
}
