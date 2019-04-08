package com.hc.kugou.service;

import com.hc.kugou.bean.custombean.CustomMusicPlayList;
import com.hc.kugou.bean.custombean.CustomUser;
import com.hc.kugou.bean.custombean.SimpleSongBean;

/**
 * @Author:
 * @Date:2019/4/7
 * @Description:com.hc.kugou.service
 * @Version:1.0
 */
public interface SimpleSongService {

    /**
     * 单曲播放
     * @param musicId   音乐id
     * @param loginedUser   登录对象
     * @return  播放对象
     */
    SimpleSongBean play(Integer musicId, CustomUser loginedUser, CustomMusicPlayList sessionMusicPlayList);
}
