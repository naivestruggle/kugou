package com.hc.kugou.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:
 * @Date:2019/4/30
 * @Description:com.hc.kugou.bean   歌手信息类
 * @Version:1.0
 */
@Data
public class SingerInfo implements Serializable {
    /**
     * hashcode
     */
    private String hashCode;
    /**
     * ID
     */
    private Long singerInfoId;
    /**
     * 歌手姓名
     */
    private String singerInfoSingername;

    /**
     * 歌手ID
     */
    private Long singerInfoSingerid;

    /**
     * 唱片数
     */
    private Integer singerInfoAlbumcount;

    /**
     * 歌曲数
     */
    private Integer singerInfoMusiccount;

    /**
     * MV数
     */
    private Integer singerInfoMvcount;

    /**
     * 歌手介绍
     */
    private String singerInfoIntroduction;

}
