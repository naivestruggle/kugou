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
    private Integer id;

    /**
     * 作者ID
     */
    private Long authorId;

    /**
     * 作者姓名
     */
    private String authorName;

    /**
     * 音频ID
     */
    private Long audioId;

    /**
     * 音频名
     */
    private String audioName;

    /**
     * 歌曲名
     */
    private String songName;

    /**
     * 哈希值
     */
    private String hashCode;

    /**
     * 歌曲文件大小
     */
    private Long filesize;

    /**
     * 歌曲时长
     */
    private Long timelength;


    /**
     * 是否有唱片集
     */
    private Integer haveAlbum;


    /**
     * 唱片集ID
     */
    private Long albumId;

    /**
     * 唱片集名
     */
    private String albumName;

    /**
     * 是否有mv
     */
    private Integer haveMv;


    /**
     * mvID
     */
    private Integer videoId;


    /**
     * 权限
     */
    private Integer privilege;
    /**
     * 权限2
     */
    private Integer privilege2;

    /**
     * 歌曲播放链接
     */
    private String playUrl;

    /**
     * 图片路径
     */
    private String img;

    /**
     * 歌词
     */
    private String lyrics;

    /**
     * 听这首歌的人数
     */
    private Long listenerCount;

    /**
     * 语种
     */
    private String className;
}
