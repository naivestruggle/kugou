package com.hc.kugou.service.impl;

import com.hc.kugou.bean.custombean.CustomMusic;
import com.hc.kugou.bean.custombean.CustomMv;
import com.hc.kugou.bean.custombean.CustomSinger;
import com.hc.kugou.bean.custombean.IndexViewBean;
import com.hc.kugou.solr.MvSolr;
import com.hc.kugou.solr.SingerSolr;
import com.hc.kugou.solr.SolrBean;
import com.hc.kugou.mapper.MusicMapper;
import com.hc.kugou.mapper.MvMapper;
import com.hc.kugou.mapper.SingerMapper;
import com.hc.kugou.service.IndexService;
import com.hc.kugou.solr.MusicSolr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:
 * @Date:2019/4/30
 * @Description:com.hc.kugou.service.impl
 * @Version:1.0
 */

@Service("indexService")
public class IndexServiceImpl implements IndexService {
    /**
     * 华语
     */
    private static final String CHINA = "china";
    /**
     * 欧美
     */
    private static final String EAA = "eaa";
    /**
     * 日本
     */
    private static final String JAPAN = "japan";
    /**
     * 韩国
     */
    private static final String KOREA = "korea";

    /**
     * 日韩
     */
    private static final String JAPAN_KOREA = "japanKorea";



    @Autowired
    private MusicMapper musicMapper;

    @Autowired
    private SingerMapper singerMapper;

    @Autowired
    private MvMapper mvMapper;

    @Autowired
    private MusicSolr musicSolr;

    @Autowired
    private MvSolr mvSolr;

    @Autowired
    private SingerSolr singerSolr;
    /**
     * 得到所有的主页要显示的信息
     *
     * @return
     */
    @Override
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
        Map<String, SolrBean<CustomSinger>> popSingerCollect = new HashMap<String,SolrBean<CustomSinger>>();
        SolrBean<CustomSinger> chinaSingerSolrBean = singerSolr.selectSingerByClassName("华语",5);
        SolrBean<CustomSinger> eaaSingerSolrBean = singerSolr.selectSingerByClassName("欧美",5);
        SolrBean<CustomSinger> japanKoreaSingerSolrBean = singerSolr.selectSingerByClassName("日韩",5);
        popSingerCollect.put(CHINA,chinaSingerSolrBean);
        popSingerCollect.put(EAA,eaaSingerSolrBean);
        popSingerCollect.put(JAPAN_KOREA,japanKoreaSingerSolrBean);
        indexViewBean.setPopSingerCollect(popSingerCollect);
    }

    /**
     * 添加新歌集合
     * @param indexViewBean 信息对象
     */
    private void addNewMusic(IndexViewBean indexViewBean) {
        Map<String, SolrBean<CustomMusic>> newMusicCollect = new HashMap<String,SolrBean<CustomMusic>>();
        SolrBean<CustomMusic> chinaMusicSolrBean = musicSolr.selectNewMusicByClassName("华语",24);
        SolrBean<CustomMusic> eaaMusicSolrBean = musicSolr.selectNewMusicByClassName("欧美",24);
        SolrBean<CustomMusic> japanMusicSolrBean = musicSolr.selectNewMusicByClassName("日本",24);
        SolrBean<CustomMusic> koreaMusicSolrBean = musicSolr.selectNewMusicByClassName("韩国",24);
        newMusicCollect.put(CHINA,chinaMusicSolrBean);
        newMusicCollect.put(EAA,eaaMusicSolrBean);
        newMusicCollect.put(JAPAN,japanMusicSolrBean);
        newMusicCollect.put(KOREA,koreaMusicSolrBean);
        indexViewBean.setNewMusicCollect(newMusicCollect);
    }

    /**
     * 添加MV
     * @param indexViewBean 信息对象
     */
    private void addRecommendMv(IndexViewBean indexViewBean) {
        SolrBean<CustomMv> recommendMvCollect = mvSolr.selectPopMv(3);
        indexViewBean.setRecommendMvCollect(recommendMvCollect);
    }
}
