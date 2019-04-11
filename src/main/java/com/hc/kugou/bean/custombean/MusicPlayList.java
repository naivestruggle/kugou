package com.hc.kugou.bean.custombean;

import com.hc.kugou.bean.Music;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author:
 * @Date:2019/4/7
 * @Description:com.hc.kugou.bean.custombean    播放列表        存放在redis中
 * @Version:1.0
 */
@Data
public class MusicPlayList implements Serializable {
    /**
     * 歌曲集合
     */
    private List<CustomMusic> musicList;

    /**
     * 是否有歌曲
     */
    private Integer hasMusicList;

    /**
     * 歌曲数
     */
    private Integer musicListSize;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 是否有用户   用来做判断   没有用户的话 就存session中，随浏览器的关闭而消失
     */
    private Integer hasUser;
}
