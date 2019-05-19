package com.hc.kugou.service;

import com.hc.kugou.bean.custombean.CustomMusicPlayList;
import com.hc.kugou.bean.custombean.CustomUser;

import javax.servlet.http.HttpSession;

/**
 * @Author:
 * @Date:2019/5/19
 * @Description:com.hc.kugou.service    播放列表
 * @Version:1.0
 */
public interface MusicPlayListService {

    /**
     * 加载播放列表
     * @param loginedUser   登录用户
     * @param sessionMusicPlayList  session中存的播放列表
     * @return  播放列表对象
     */
    CustomMusicPlayList loadMusicPlayList(CustomUser loginedUser, CustomMusicPlayList sessionMusicPlayList)throws Exception;

    /**
     * 清空播放列表
     * @param loginedUser   登录用户对象
     * @param session   会话对象
     * @throws Exception    抛出异常
     */
    void clearAllMusicPlayList(CustomUser loginedUser, HttpSession session)throws Exception;

}
