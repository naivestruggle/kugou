package com.hc.kugou.service.impl;

import com.hc.commons.PythonUtils;
import com.hc.commons.StringUtils;
import com.hc.kugou.bean.custombean.CustomMusic;
import com.hc.kugou.bean.custombean.CustomMusicPlayList;
import com.hc.kugou.bean.custombean.CustomUser;
import com.hc.kugou.bean.custombean.SimpleSongBean;
import com.hc.kugou.service.SimpleSongService;
import com.hc.kugou.solr.MusicSolr;
import com.hc.kugou.solr.SolrBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:
 * @Date:2019/4/7
 * @Description:com.hc.kugou.service.impl
 * @Version:1.0
 */
@Service("simpleSongService")

public class SimpleSongServiceImpl implements SimpleSongService {

    /**
     * 注入自定义的序列化redis模版对象
     */
    @Autowired
    private RedisTemplate objectRedisTemplate;

    @Autowired
    private MusicSolr musicSolr;

    /**
     * 单曲播放
     *
     * @param musicId     音乐id
     * @param loginedUser 登录对象
     * @return 播放对象
     */
    @Override
    public SimpleSongBean play(Integer musicId, CustomUser loginedUser,CustomMusicPlayList sessionMusicPlayList) {
        SimpleSongBean simpleSongBean = new SimpleSongBean();
        //【添加播放列表对象】
        addMusicPlayList(loginedUser, sessionMusicPlayList, simpleSongBean);

        //【添加要播放的音乐对象】
        SolrBean<CustomMusic> solrBean =  musicSolr.selectMusicById(musicId);
        for(java.util.Map.Entry<String,CustomMusic> me : solrBean.getSolrBeanMap().entrySet()){
            CustomMusic music = me.getValue();
            //获取到music的播放路径
            music.setMusicPlayUrl(PythonUtils.getMusicPlayUrl(music.getMusicHashCode()));
            simpleSongBean.setOneMusic(music);
        }
        return simpleSongBean;
    }


    /**
     * 添加播放列表对象
     * @param loginedUser   登录用户
     * @param sessionMusicPlayList  session中的播放列表对象
     * @param simpleSongBean    单曲页面对象
     */
    private void addMusicPlayList(CustomUser loginedUser, CustomMusicPlayList sessionMusicPlayList, SimpleSongBean simpleSongBean) {
        if(loginedUser == null){
            //如果没有登录用户  播放列表对象就从session中获取
            if(sessionMusicPlayList == null){
                //如果播放列表对象为null
                sessionMusicPlayList = new CustomMusicPlayList();
                //没有歌曲
                sessionMusicPlayList.setHasMusicList(0);
                //没有用户
                sessionMusicPlayList.setHasUser(0);
            }
            simpleSongBean.setMusicPlayList(sessionMusicPlayList);
        }else {
            //如果有登录用户  就将播放列表从redis中取出
            //取出用户id
            Integer userId = loginedUser.getUserId();
            //取出redis中的播放列表对象
            CustomMusicPlayList musicPlayList = (CustomMusicPlayList) objectRedisTemplate.opsForValue().get(StringUtils.PLAT_SONG_LIST_PRE+userId);
            if(musicPlayList == null){
                //如果播放列表对象为null
                musicPlayList = new CustomMusicPlayList();
                //没有歌曲
                musicPlayList.setHasMusicList(0);
                //有用户
                musicPlayList.setHasUser(1);
                //设置用户id
                musicPlayList.setUserId(userId);
            }
            simpleSongBean.setMusicPlayList(musicPlayList);
        }
    }
}
