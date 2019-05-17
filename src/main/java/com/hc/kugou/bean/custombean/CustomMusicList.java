package com.hc.kugou.bean.custombean;

import com.hc.kugou.bean.Music;
import com.hc.kugou.bean.MusicList;
import lombok.Data;

import java.util.List;

/**
 * @Author:
 * @Date:2019/4/11
 * @Description:com.hc.kugou.bean.custombean    歌单
 * @Version:1.0
 */
@Data
public class CustomMusicList extends MusicList {
    /**
     * 高亮
     */
    private String highlight;
    /**
     * 歌曲集合
     */
    private List<CustomMusic> musicsList;
}
