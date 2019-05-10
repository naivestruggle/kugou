package com.hc.kugou.service.impl;

import com.hc.kugou.bean.custombean.CustomSinger;
import com.hc.kugou.bean.custombean.SingerViewBean;
import com.hc.kugou.solr.SingerSolr;
import com.hc.kugou.solr.SolrBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ck
 * @create 2019-05-09 18:30
 */
@Service("singerService")
public class SingerServiceImpl implements SingerService {

    /**
     * 全部歌手
     */
    private static final String MAP_ALL = "all";
    private static final int MAP_ALL_NUM = 1;

    /**
     * 华语男歌手
     */
    private static final String MAP_CHINA_MALE_SINGER = "chinaMaleSinger";
    private static final int MAP_CHINA_MALE_SINGER_NUM = 2;

    /**
     * 华语女歌手
     */
    public static final String MAP_CHINA_FEMALE_SINGER = "chinaFemaleSinger";
    public static final int MAP_CHINA_FEMALE_SINGER_NUM = 3;

    /**
     * 华语组合
     */
    public static final String MAP_CHINA_GROUP_SINGER = "chinaGroupSinger";
    public static final int MAP_CHINA_GROUP_SINGER_NUM = 4;

    /**
     * 日韩男歌手
     */
    private static final String MAP_JAPANKOREA_MALE_SINGER = "japanKoreaMaleSinger";
    private static final int MAP_JAPANKOREA_MALE_SINGER_NUM = 5;

    /**
     * 日韩女歌手
     */
    private static final String MAP_JAPANKOREA_FEMALE_SINGER = "japanKoreaFemaleSinger";
    private static final int MAP_JAPANKOREA_FEMALE_SINGER_NUM = 6;

    /**
     * 日韩组合
     */
    private static final String MAP_JAPANKOREA_GROUP_SINGER = "japanKoreaGroupSinger";
    private static final int MAP_JAPANKOREA_GROUP_SINGER_NUM = 7;

    /**
     * 欧美男歌手 EuropeAmerica
     */
    private static final String MAP_EUROPEAMERICA_MALE_SINGER = "europeAmericaMaleSinger";
    private static final int MAP_EUROPEAMERICA_MALE_SINGER_NUM = 8;

    /**
     * 欧美女歌手
     */
    private static final String MAP_EUROPEAMERICA_FEMALE_SINGER = "europeAmericaFemaleSinger";
    private static final int MAP_EUROPEAMERICA_FEMALE_SINGER_NUM = 9;

    /**
     * 欧美组合
     */
    private static final String MAP_EUROPEAMERICA_GROUP_SINGER = "europeAmericaGroupSinger";
    private static final int MAP_EUROPEAMERICA_GROUP_SINGER_NUM = 10;

    @Autowired
    SingerSolr singerSolr;

    /**
     * 查询展示Singer页面所需要的数据
     *
     * @param singerClassName 语种
     * @param singerSindex    A-Z类型
     * @param page            页数
     * @return
     */
    @Override
    public SingerViewBean singer(int singerClassName, String singerSindex, int page) {
        SingerViewBean singerViewBean = new SingerViewBean();


        //添加歌手
        addSingers(singerViewBean,singerClassName,singerSindex,page);


        return singerViewBean;
    }

    /**
     * 添加歌手
     * @param singerViewBean  歌手视图集合
     * @param singerClassName 语种
     * @param singerSindex    A-Z
     * @param page            当前页
     */
    private void addSingers(SingerViewBean singerViewBean, int singerClassName, String singerSindex, int page) {
        Map<String, SolrBean<CustomSinger>> singerCollect = new HashMap<String, SolrBean<CustomSinger>>();

        //计算起始行
        if(page == 1){
            page = 0;
        }else{
            page = 51 + (page - 2) * 63;
        }


        if(singerClassName == MAP_ALL_NUM){
            //查全部歌手
        }else {
            //得到查询语种
            String languages = getLanguages(singerClassName);

            //solr查询
            singerSolr.singer(singerClassName,singerSindex,page);
            //放入map
//            singerSolr.singer();
//            addMap(singerClassName,singerCollect,)
//            if(singerClassName == MAP_CHINA_MALE_SINGER_NUM){
//                singerCollect.put(MAP_CHINA_MALE_SINGER,);
//            }else if(singerClassName == )
        }
        singerViewBean.setSingerCollect(singerCollect);
    }


    /**
     * 判断语种
     * @param singerClassName
     * @return
     */
    private String getLanguages(int singerClassName) {
        String language = null;
        switch (singerClassName){
            case MAP_CHINA_MALE_SINGER_NUM:
                language = "华语男歌手";
                break;
            case MAP_CHINA_FEMALE_SINGER_NUM:
                language = "华语女歌手";
                break;
            case MAP_CHINA_GROUP_SINGER_NUM:
                language = "华语组合";
                break;
            case MAP_JAPANKOREA_MALE_SINGER_NUM:
                language = "日韩男歌手";
                break;
            case MAP_JAPANKOREA_FEMALE_SINGER_NUM:
                language = "日韩女歌手";
                break;
            case MAP_JAPANKOREA_GROUP_SINGER_NUM:
                language = "日韩组合";
                break;
            case MAP_EUROPEAMERICA_MALE_SINGER_NUM:
                language = "欧美男歌手";
                break;
            case MAP_EUROPEAMERICA_FEMALE_SINGER_NUM:
                language = "欧美女歌手";
                break;
            case MAP_EUROPEAMERICA_GROUP_SINGER_NUM:
                language = "欧美组合";
                break;
            default:
                language = "未知";
        }
        return  language;

    }


}
