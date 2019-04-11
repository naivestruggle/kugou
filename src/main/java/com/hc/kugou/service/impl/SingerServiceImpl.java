package com.hc.kugou.service.impl;

import com.hc.kugou.bean.custombean.CustomSinger;
import com.hc.kugou.bean.custombean.SingerViewBean;
import com.hc.kugou.service.SingerService;
import com.hc.kugou.solr.SingerSolr;
import com.hc.kugou.solr.SolrBean;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static final String MAP_ALL = "ALL";
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
    private SingerSolr singerSolr;

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

        //设置当前页
        singerViewBean.setPage(page);
        //设置语种
        singerViewBean.setSingerClassName(singerClassName);
        //计算起始行
        page = (page - 1) * 36;
        //将a-z 或 A-Z全转换成大写
        singerSindex = singerSindex.toUpperCase();
        //设置A-Z
        singerViewBean.setSingerSindex(singerSindex);
        if(singerClassName == MAP_ALL_NUM){
            //查全部歌手
            SolrBean<CustomSinger> singerSolrBean = singerSolr.queryAllSinger(singerSindex, page);
            singerCollect.put(MAP_ALL,singerSolrBean);
            //计算总页数
            int i = new Double(Math.ceil(singerSolrBean.getFoundNum() / 36.0)).intValue();
            if(i > 5){
                i = 5;
            }
            singerViewBean.setTotalPage(i);
        }else {
            //得到查询语种
            String languages = getLanguages(singerClassName);

            //solr查询,并放入
            SolrBean<CustomSinger> singerSolrBean = singerSolr.singer(singerClassName, singerSindex, page);

            //计算总页数
            int i = new Double(Math.ceil(singerSolrBean.getFoundNum() / 36.0)).intValue();
            if(i > 5){
                i = 5;
            }
            singerViewBean.setTotalPage(i);

            addMap(singerCollect,singerSolrBean,singerClassName);
        }

        singerViewBean.setSingerCollect(singerCollect);
    }

    /**
     * 将查询出的singer集合添加到singerCollect
     * @param singerCollect
     * @param singerSolrBean
     * @param singerClassName
     */
    private void addMap(Map<String, SolrBean<CustomSinger>> singerCollect, SolrBean<CustomSinger> singerSolrBean, int singerClassName) {
        switch (singerClassName){
            case MAP_CHINA_MALE_SINGER_NUM:
                singerCollect.put(MAP_CHINA_MALE_SINGER,singerSolrBean);
                break;
            case MAP_CHINA_FEMALE_SINGER_NUM:
                singerCollect.put(MAP_CHINA_FEMALE_SINGER,singerSolrBean);
                break;
            case MAP_CHINA_GROUP_SINGER_NUM:
                singerCollect.put(MAP_CHINA_GROUP_SINGER,singerSolrBean);
                break;
            case MAP_JAPANKOREA_MALE_SINGER_NUM:
                singerCollect.put(MAP_JAPANKOREA_MALE_SINGER,singerSolrBean);
                break;
            case MAP_JAPANKOREA_FEMALE_SINGER_NUM:
                singerCollect.put(MAP_JAPANKOREA_FEMALE_SINGER,singerSolrBean);
                break;
            case MAP_JAPANKOREA_GROUP_SINGER_NUM:
                singerCollect.put(MAP_JAPANKOREA_GROUP_SINGER,singerSolrBean);
                break;
            case MAP_EUROPEAMERICA_MALE_SINGER_NUM:
                singerCollect.put(MAP_EUROPEAMERICA_MALE_SINGER,singerSolrBean);
                break;
            case MAP_EUROPEAMERICA_FEMALE_SINGER_NUM:
                singerCollect.put(MAP_EUROPEAMERICA_FEMALE_SINGER,singerSolrBean);
                break;
            case MAP_EUROPEAMERICA_GROUP_SINGER_NUM:
                singerCollect.put(MAP_EUROPEAMERICA_GROUP_SINGER,singerSolrBean);
                break;
        }


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
