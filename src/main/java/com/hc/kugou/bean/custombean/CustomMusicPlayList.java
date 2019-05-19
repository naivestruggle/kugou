package com.hc.kugou.bean.custombean;

import com.hc.kugou.bean.MusicPlayList;
import lombok.Data;

import java.util.List;

/**
 * @Author:
 * @Date:2019/4/11
 * @Description:com.hc.kugou.bean.custombean
 * @Version:1.0
 */
@Data
public class CustomMusicPlayList extends MusicPlayList {
    /**
     * 播放列表中的音乐集合
     */
    private List<CustomMusic> musicPlayLists;
}
