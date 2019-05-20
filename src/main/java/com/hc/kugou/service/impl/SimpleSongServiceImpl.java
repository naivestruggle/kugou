package com.hc.kugou.service.impl;

import com.hc.commons.PythonUtils;
import com.hc.commons.StringUtils;
import com.hc.kugou.bean.Music;
import com.hc.kugou.bean.MusicPlayList;
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

import javax.servlet.http.HttpSession;
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
    public SimpleSongBean play(Integer musicId, CustomUser loginedUser, HttpSession session) {
        SimpleSongBean simpleSongBean = new SimpleSongBean();
        //【添加要播放的音乐对象】
        SolrBean<CustomMusic> solrBean =  musicSolr.selectMusicById(musicId);
        for(CustomMusic music : solrBean.getSolrBeanList()) {
            //获取到music的播放路径
            System.out.println("hashcode:"+music.getHashCode()+":::=="+PythonUtils.getMusicPlayUrl(music.getHashCode()));
            music.setMusicPlayUrl(PythonUtils.getMusicPlayUrl(music.getHashCode()));
            simpleSongBean.setOneMusic(music);
            //将单曲添加到播放列表中
            addMusicToMusicPlayList(loginedUser,music,session);
        }
        return simpleSongBean;
    }

    /**
     * 将当前播放歌曲添加到播放列表
     *
     * @param loginedUser 登录用户
     * @param session     会话对象
     * @param musicId     音乐id
     * @throws Exception 抛出异常
     */
    @Override
    public void addNowPlayMusicToMusicPlayList(CustomUser loginedUser, HttpSession session, Integer musicId) throws Exception {
        SolrBean<CustomMusic> solrBean = musicSolr.selectMusicById(musicId);
        CustomMusic music = solrBean.getSolrBeanList().get(0);
        addMusicToMusicPlayList(loginedUser,music,session);
    }

    /**
     * 将单曲添加到播放列表
     * @param loginedUser   登录用户
     * @param music 单曲对象
     */
    private void addMusicToMusicPlayList(CustomUser loginedUser, CustomMusic music, HttpSession session) {
        if(loginedUser == null){
            //未登录  将session中的播放列表对象取出
            CustomMusicPlayList sessionMusicPlayList = (CustomMusicPlayList)session.getAttribute(StringUtils.PLAT_SONG_LIST_PRE);
            sessionMusicPlayList = getNotNullMusicPlayList(music, sessionMusicPlayList);
            session.setAttribute(StringUtils.PLAT_SONG_LIST_PRE,sessionMusicPlayList);
        }else{
            //已登录
            String key = StringUtils.getRedisMusicPlayListKey(loginedUser.getUserId());
            //取出redis中的播放列表对象
            CustomMusicPlayList musicPlayList = (CustomMusicPlayList)objectRedisTemplate.opsForValue().get(key);
            musicPlayList = getNotNullMusicPlayList(music, musicPlayList);
            objectRedisTemplate.opsForValue().set(key,musicPlayList);
        }
    }

    /**
     * 得到播放列表对象  没有就创建一个
     * @param music 单曲对象
     * @param musicPlayList    播放列表对象
     * @return  播放列表对象
     */
    private CustomMusicPlayList getNotNullMusicPlayList(CustomMusic music, CustomMusicPlayList musicPlayList) {
        if (musicPlayList == null) {
            //如果session中的播放列表对象为null  就创建一个放入session中
            musicPlayList = new CustomMusicPlayList();
            List<CustomMusic> list = new ArrayList<CustomMusic>();
            list.add(music);
            musicPlayList.setMusicPlayLists(list);
        } else {
            //如果不为null  就追加
            boolean flag = true;
            List<CustomMusic> musics = musicPlayList.getMusicPlayLists();
            for (Music music1 : musics){
                if(music.getMusicId().equals(music1.getMusicId())){
                    flag = false;
                    break;
                }
            }
            if(flag){
                //原播放列表中没有  才追加
                musicPlayList.getMusicPlayLists().add(music);
            }
        }
        return musicPlayList;
    }

    /**
     * 更新music的访问量   music访问量+1  对应歌手访问量+1  对应专辑访问量+1
     *
     * @param id 要更新的musicid对象
     */
    @Override
    public void updateLentenerCount(Integer id) {

    }

    /**
     * 根据音乐id  获取音乐对象
     *
     * @param musicId 音乐id
     * @return 音乐对象
     * @throws Exception 抛出异常
     */
    @Override
    public CustomMusic getOneMusicInfo(Integer musicId) throws Exception {
        SolrBean<CustomMusic> solrBean = musicSolr.selectMusicById(musicId);
        CustomMusic music = solrBean.getSolrBeanList().get(0);
        music.setMusicPlayUrl(PythonUtils.getMusicPlayUrl(music.getHashCode()));
        return music;
    }
}
