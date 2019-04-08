package com.hc.kugou;

import com.hc.kugou.bean.Music;
import com.hc.kugou.bean.custombean.CustomMusic;
import com.hc.kugou.bean.custombean.CustomUser;
import com.hc.kugou.bean.custombean.IndexViewBean;
import com.hc.kugou.mapper.*;
import com.hc.kugou.solr.SolrBean;
import com.hc.kugou.service.IndexService;
import com.hc.kugou.solr.MusicSolr;
import org.apache.solr.client.solrj.SolrClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.util.StringUtils;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KugouApplicationTests {

    @Autowired
    private IndexService indexService;

    @Autowired
    private MvMapper mvMapper;

    @Autowired
    private SingerInfoMapper singerInfoMapper;

    @Autowired
    private MusicMapper musicMapper;
    @Autowired
    private SingerMapper singerMapper;
    @Test
    public void contextLoads() {
        Long time1 = System.currentTimeMillis();
        IndexViewBean indexViewBean = indexService.showService();
        Long time2 = System.currentTimeMillis();
        Long time = time2-time1;
        System.out.println(indexViewBean);
        System.out.println("执行时间："+time+"ms");
    }

    @Autowired
    private SolrClient client;

    @Autowired
    private MusicSolr musicSolr;


    @Test
    public void testMusicSolr1(){
        SolrBean<CustomMusic> solrBean = musicSolr.selectNewMusicByClassName("华语",1);
        System.out.println("查到结果："+solrBean.getFoundNum());
        System.out.println("高亮字段："+solrBean.getHighlight());
        System.out.println("-----------------------------------");;
        Map<String,CustomMusic> map = solrBean.getSolrBeanMap();
        for(Map.Entry<String,CustomMusic> me:map.entrySet()){
            System.out.println("id："+me.getKey());
            System.out.println(me.getValue());
            List<String[]> list = me.getValue().getMusicLyricsList();
            for(String[] arr : list){
                for(String str:arr){
                    System.out.println("歌词："+str);
                }
            }
        }
    }

    /**
     * 得到UUID
     * @return
     */
    private static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    @Test
    public void testfun1(){
        SolrBean<CustomMusic> solrBean = musicSolr.selectMusicById(35);
        Map<String,CustomMusic> map = solrBean.getSolrBeanMap();
        for(Map.Entry<String,CustomMusic> me:map.entrySet()) {
            System.out.println("id：" + me.getKey());
            System.out.println(me.getValue());
            List<String[]> list = me.getValue().getMusicLyricsList();
            for(String[] arr : list){
                for(String str:arr){
                    System.out.println("歌词："+str);
                }
            }
        }
    }

    @Test
    public void updateMusicSongName(){
        //78142
        for(long i=1;i<=78142;i++){
            Music music = musicMapper.selectMusicById(i);
            if(StringUtils.isEmpty(music.getMusicSongName())){
                String name = music.getMusicAudioName();
                name = name.split("-")[1].trim();
                musicMapper.updateMusicSongName(i,name);
            }
            System.out.println(i);
        }
    }
}
