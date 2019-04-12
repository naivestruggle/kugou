package com.hc.kugou.bean;

import com.hc.kugou.bean.custombean.CustomMusic;

import java.io.Serializable;
import java.util.List;

/**
 * @Author:
 * @Date:2019/4/11
 * @Description:com.hc.kugou.bean   临时播放列表
 * @Version:1.0
 */
public class ShortTimeMusicPlayList implements Serializable {
    /**
     * hashcode
     */
    private String hashCode;
    /**
     * 音乐集合
     */
    private List<CustomMusic> musicList;

    /**
     * 临时歌单中的音乐数量
     */
    private Integer shortTimeMusicListCount;
}
