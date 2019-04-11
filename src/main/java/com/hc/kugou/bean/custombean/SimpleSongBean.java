package com.hc.kugou.bean.custombean;

import com.hc.kugou.bean.custombean.CustomMusic;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author:
 * @Date:2019/4/6
 * @Description:com.hc.kugou.bean   单曲页面对象
 * @Version:1.0
 */
@Data
public class SimpleSongBean implements Serializable {
    /**
     * 单曲对象
     */
    private CustomMusic oneMusic;

    /**
     * 播放列表对象
     */
    private MusicPlayList musicPlayList;


}
