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
     * ID
     */
    Long singerInfoId;
    /**
     * 歌手姓名
     */
    String singerInfoSingername;

    /**
     * 歌手ID
     */
    Long singerInfoSingerid;

    /**
     * 唱片数
     */
    Integer singerInfoAlbumcount;

    /**
     * 歌曲数
     */
    Integer singerInfoMusiccount;

    /**
     * MV数
     */
    Integer singerInfoMvcount;

    /**
     * 歌手介绍
     */
    String singerInfoIntroduction;

}
