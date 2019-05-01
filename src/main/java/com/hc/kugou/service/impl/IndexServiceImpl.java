package com.hc.kugou.service.impl;

import com.hc.kugou.bean.IndexViewBean;
import com.hc.kugou.bean.Music;
import com.hc.kugou.bean.Mv;
import com.hc.kugou.bean.Singer;
import com.hc.kugou.mapper.MusicMapper;
import com.hc.kugou.mapper.MvMapper;
import com.hc.kugou.mapper.SingerMapper;
import com.hc.kugou.service.IndexService;
import com.hc.kugou.service.MvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:
 * @Date:2019/4/30
 * @Description:com.hc.kugou.service.impl
 * @Version:1.0
 */
@Service("indexService")
@Transactional
public class IndexServiceImpl implements IndexService {
    /**
     * 华语
     */
    private static final String MAP_CHINA = "china";
    /**
     * 欧美
     */
    private static final String MAA_EAA = "eaa";
    /**
     * 日本
     */
    private static final String MAP_JAPAN = "japan";
    /**
     * 韩国
     */
    private static final String MAP_KOREA = "korea";

    /**
     * 日韩
     */
    private static final String MAP_JAPAN_KOREA = "japanKorea";

    @Autowired
    private MusicMapper musicMapper;

    @Autowired
    private SingerMapper singerMapper;

    @Autowired
    private MvService mvService;
    /**
     * 得到所有的主页要显示的信息
     *
     * @return
     */
    @Override
    @Cacheable(cacheNames = "indexViewBean")
    public IndexViewBean showService() {
        IndexViewBean indexViewBean = new IndexViewBean();

        //添加【轮播图模块】

        //添加【精选歌单模块】

        //添加【热门榜单模块】

        //添加【新歌首发模块】
        addNewMusic(indexViewBean);

        //添加【推荐MV模块】
        addRecommendMv(indexViewBean);


        //添加【热门电台模块】

        //添加【热门歌手模块】
        addPopSinger(indexViewBean);

        return indexViewBean;
    }

    /**
     * 添加热门歌手
     * @param indexViewBean 信息对象
     */
    private void addPopSinger(IndexViewBean indexViewBean) {
        Map<String, List<Singer>> popSingerCollect = new HashMap<String,List<Singer>>();
        List<Singer> chinaSingerList = singerMapper.selectSingerByClassName("华",15);
        List<Singer> eaaSingerList = singerMapper.selectSingerByClassName("欧美",15);
        List<Singer> japanSingerList = singerMapper.selectSingerByClassName("日",15);
        List<Singer> koreaSingerList = singerMapper.selectSingerByClassName("韩",15);
        popSingerCollect.put(MAP_CHINA,chinaSingerList);
        popSingerCollect.put(MAA_EAA,eaaSingerList);
        popSingerCollect.put(MAP_JAPAN,japanSingerList);
        popSingerCollect.put(MAP_KOREA,koreaSingerList);
        indexViewBean.setPopSingerCollect(popSingerCollect);
    }

    /**
     * 添加新歌集合
     * @param indexViewBean 信息对象
     */
    private void addNewMusic(IndexViewBean indexViewBean) {
        Map<String, List<Music>> newMusicCollect = new HashMap<String,List<Music>>();
        List<Music> chinaMusicList = musicMapper.selectNewMusicByClassName("华",24);
        List<Music> eaaMusicList = musicMapper.selectNewMusicByClassName("欧美",24);
        List<Music> japanMusicList = musicMapper.selectNewMusicByClassName("日",24);
        List<Music> koreaMusicList = musicMapper.selectNewMusicByClassName("韩",24);
        newMusicCollect.put(MAP_CHINA,chinaMusicList);
        newMusicCollect.put(MAA_EAA,eaaMusicList);
        newMusicCollect.put(MAP_JAPAN,japanMusicList);
        newMusicCollect.put(MAP_KOREA,koreaMusicList);
        indexViewBean.setNewMusicCollect(newMusicCollect);
    }

    /**
     * 添加MV
     * @param indexViewBean 信息对象
     */
    private void addRecommendMv(IndexViewBean indexViewBean) {
        //先取出推荐的歌曲  华语15  欧美15  日韩15   推荐根据歌曲的访问量来排序
        List<Mv> mvList = null;
        Map<String,List<Mv>> recommendMvCollect = new HashMap<String,List<Mv>>();
        //1、添加华语MV
        List<Music> chinaRecommendMusicList = musicMapper.selectRecommendMusicByClassName("华",15);
        mvList = new ArrayList<Mv>();
        for(Music music:chinaRecommendMusicList){
            Mv mv = mvService.findByName(music.getAudioName());
            mvList.add(mv);
        }
        recommendMvCollect.put(MAP_CHINA,mvList);
        //2、添加欧美Mv
        List<Music> eaaRecommendMusicList = musicMapper.selectRecommendMusicByClassName("欧美",15);
        mvList = new ArrayList<Mv>();
        for(Music music:eaaRecommendMusicList){
            Mv mv = mvService.findByName(music.getAudioName());
            mvList.add(mv);
        }
        recommendMvCollect.put(MAA_EAA,mvList);
        //3、添加日韩Mv
        List<Music> japanKoreaRecommendMusicList = musicMapper.selectRecommendMusicByClassName("日韩",15);
        mvList = new ArrayList<Mv>();
        for(Music music:japanKoreaRecommendMusicList){
            Mv mv = mvService.findByName(music.getAudioName());
            mvList.add(mv);
        }
        recommendMvCollect.put(MAP_JAPAN_KOREA,mvList);
        indexViewBean.setRecommendMvCollect(recommendMvCollect);
    }
}
