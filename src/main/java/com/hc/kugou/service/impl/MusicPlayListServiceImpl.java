package com.hc.kugou.service.impl;

import com.hc.commons.StringUtils;
import com.hc.kugou.bean.custombean.CustomMusicPlayList;
import com.hc.kugou.bean.custombean.CustomUser;
import com.hc.kugou.service.MusicPlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @Author:
 * @Date:2019/5/19
 * @Description:com.hc.kugou.service.impl
 * @Version:1.0
 */
@Service("musicPlayListService")
public class MusicPlayListServiceImpl implements MusicPlayListService {
    @Autowired
    private RedisTemplate objectRedisTemplate;

    /**
     * 加载播放列表
     *
     * @param loginedUser          登录用户
     * @param sessionMusicPlayList session中存的播放列表
     * @return 播放列表对象
     */
    @Override
    public CustomMusicPlayList loadMusicPlayList(CustomUser loginedUser, CustomMusicPlayList sessionMusicPlayList) throws Exception {
        if(loginedUser == null){
            //没有用户登录
            if(sessionMusicPlayList == null){
                return new CustomMusicPlayList();
            }else {
                return sessionMusicPlayList;
            }
        }else {
            //有用户登录
            CustomMusicPlayList redisMusicPlayList = getRedisMusicPlayList(loginedUser.getUserId());
            return redisMusicPlayList;
        }
    }

    /**
     * 清空播放列表
     *
     * @param loginedUser 登录用户对象
     * @param session     会话对象
     * @throws Exception 抛出异常
     */
    @Override
    public void clearAllMusicPlayList(CustomUser loginedUser, HttpSession session) throws Exception {
        session.removeAttribute(StringUtils.PLAT_SONG_LIST_PRE);
        if(loginedUser != null){
            String key = StringUtils.getRedisMusicPlayListKey(loginedUser.getUserId());
            objectRedisTemplate.delete(key);
        }
    }

    /**
     * 得到redis中的播放列表对象
     * @param userId    用户ID
     * @return 播放列表对象
     */
    private CustomMusicPlayList getRedisMusicPlayList(Integer userId) {
        String key = StringUtils.getRedisMusicPlayListKey(userId);
        CustomMusicPlayList musicPlayList = (CustomMusicPlayList) objectRedisTemplate.opsForValue().get(key);
        if(musicPlayList == null){
            return new CustomMusicPlayList();
        }else {
            return musicPlayList;
        }
    }
}
