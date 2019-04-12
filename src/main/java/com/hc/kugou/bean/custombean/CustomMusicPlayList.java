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
     * 是否有登录用户 1:是  0:否
     */
    private Integer musicPlayListHasLoginedUser;
    /**
     * 播放列表中是否有歌曲
     */
    private Integer musicPlayListHasMusic;
    /**
     * 播放列表中的音乐集合
     */
    private List<CustomMusic> musicPlayList;
}
