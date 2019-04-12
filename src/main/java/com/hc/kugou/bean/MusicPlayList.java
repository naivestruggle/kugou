package com.hc.kugou.bean;

import lombok.Data;

import java.sql.Date;

/**
 * @Author:
 * @Date:2019/4/11
 * @Description:com.hc.kugou.bean  播放列表
 * @Version:1.0
 */
@Data
public class MusicPlayList {
    /**
     * hashcode  solr中的
     */
    private String hashCode;
    /**
     * 播放列表id
     */
    private Integer musicPlayListId;
    /**
     * hashcode  mysql中的
     */
    private String musicPlayListHashCode;
    /**
     * 播放列表对应的用户id
     */
    private Integer musicPlayListUserId;
    /**
     * 播放列表对应的音乐集合
     */
    private String musicPlayListMusicId;
    /**
     * 播放列表最后更新时间
     */
    private Date musicPlayListUpdateTime;
}
