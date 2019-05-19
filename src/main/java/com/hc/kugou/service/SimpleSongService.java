package com.hc.kugou.service;

import com.hc.kugou.bean.MusicPlayList;
import com.hc.kugou.bean.custombean.CustomMusic;
import com.hc.kugou.bean.custombean.CustomMusicPlayList;
import com.hc.kugou.bean.custombean.CustomUser;
import com.hc.kugou.bean.custombean.SimpleSongBean;

import javax.servlet.http.HttpSession;

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
    SimpleSongBean play(Integer musicId, CustomUser loginedUser, HttpSession session);

    /**
     * 将当前播放歌曲添加到播放列表
     * @param loginedUser   登录用户
     * @param session   会话对象
     * @param musicId   音乐id
     * @throws Exception    抛出异常
     */
    void addNowPlayMusicToMusicPlayList(CustomUser loginedUser, HttpSession session, Integer musicId)throws Exception;

    /**
     * 更新music的访问量   music访问量+1  对应歌手访问量+1  对应专辑访问量+1
     * @param id 要更新的musicid对象
     */
    void updateLentenerCount(Integer id);
}
