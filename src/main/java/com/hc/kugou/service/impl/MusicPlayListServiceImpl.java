package com.hc.kugou.service.impl;

import com.hc.commons.StringUtils;
import com.hc.kugou.bean.custombean.CustomMusicPlayList;
import com.hc.kugou.bean.custombean.CustomUser;
import com.hc.kugou.service.MusicPlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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
