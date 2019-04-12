package com.hc.kugou.bean;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * @Author:
 * @Date:2019/4/10
 * @Description:com.hc.kugou.bean   歌单对象
 * @Version:1.0
 */
@Data
public class MusicList implements Serializable {
    /**
     * hashcode  solr中的
     */
    private String hashCode;
    /**
     * hashcode   mysql中的
     */
    private String musicListHashCode;
    /**
     * id
     */
    private Integer musicListId;
    /**
     * 歌单名
     */
    private String musicListName;
    /**
     * 创建歌单的用户
     */
    private Integer musicListUserId;
    /**
     * 创建歌单的用户的用户名
     */
    private String musicListUserUsername;
    /**
     *  心情
     */
    private String musicListMood;
    /**
     * 更新时间
     */
    private Date musicListUpdateTime;
    /**
     * 描述
     */
    private String musicListDescribe;
    /**
     * 歌单封面
     */
    private String musicListHeadImage;
    /**
     * 歌单中歌曲的数量
     */
    private Integer musicListMusicCount;
    /**
     * 歌单访问量
     */
    private Long musicListListenerCount;
}
