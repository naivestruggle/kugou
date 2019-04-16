package com.hc.kugou.service.impl;

import com.hc.kugou.bean.Mv;
import com.hc.kugou.bean.custombean.CustomMv;
import com.hc.kugou.bean.custombean.MvViewBean;
import com.hc.kugou.mapper.MvMapper;
import com.hc.kugou.service.MvService;
import com.hc.kugou.solr.MvSolr;
import com.hc.kugou.solr.SolrBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author:
 * @Date:2019/5/1
 * @Description:com.hc.kugou.service.impl
 * @Version:1.0
 */
@Service("mvService")
public class MvServiceImpl implements MvService {
    /**
     * 新歌
     */
    private static final String MAP_NEWMV = "newMv";
    private static final int MAP_NEWMV_NUM = 1;
    /**
     * 华语
     */
    private static final String MAP_CHINA = "china";
    private static final int MAP_CHINA_NUM = 2;
    /**
     * 欧美
     */
    private static final String MAP_EAA = "eaa";
    private static final int MAP_EAA_NUM = 3;

    /**
     * 日韩
     */
    private static final String MAP_JAPAN_KOREA = "japanKorea";
    private static final int MAP_JAPAN_KOREA_NUM = 4;


    @Autowired
    private MvMapper mvMapper;

    @Autowired
    private MvSolr mvSolr;

    /**
     * 根据名字模糊查找mv
     * 先在数据库查询  如果没有就调用python脚本查询
     *
     * @param mvName mv名
     * @return Mv对象
     */
    @Override
    public Mv findByName(String mvName) {
//        List<Mv> mvList = mvMapper.findMvByName(mvName);
//        Mv mv = null;
//        if(mvList == null || mvList.size() == 0){
//            //数据库中没有 调用python
//            mv = MvUtils.getMv(mvName);
//            final Mv insertMv = mv;
//            //将这条mv存入数据库
//            mvMapper.insert(insertMv);
//        }else{
//            //数据库中有  返回第一条
//            mv = mvList.get(0);
//        }
//        return mv;
        return null;
    }

    /**
     * 根据id查询指定mv
     *
     * @param mvId mvid
     * @return
     */
    @Override
    public SolrBean<CustomMv> selectMvById(Integer mvId) {
        SolrBean<CustomMv> customMvSolrBean = mvSolr.selectMvById(mvId);
        return customMvSolrBean;
    }

    /**
     * 根据当前播放的mv推荐相应的mv
     *
     * @param customMvSolrBean 当前播放的mv信息
     * @return
     */
    @Override
    public SolrBean<CustomMv> recommendMv(SolrBean<CustomMv> customMvSolrBean) {
        SolrBean<CustomMv> solrBean = new SolrBean<CustomMv>();

        //1.得到mv名
        String mvName = customMvSolrBean.getSolrBeanList().get(0).getMvName();;
        mvName = mvName.split(" - ")[0];
        //得到该MV的所有歌手
        String[] singers = mvName.split(",");

        //将所有歌手的mv排序，取前10首，再存入solrBean中
        for (int i = 0; i < singers.length; i++) {
            SolrBean<CustomMv> customMvSolrBean1 = mvSolr.selectMvBySingerName(singers[i]);
            if (i == 0) {
                solrBean.setSolrBeanList(customMvSolrBean1.getSolrBeanList());
            } else {
                solrBean.getSolrBeanList().addAll(customMvSolrBean1.getSolrBeanList());
            }
        }

        //2.遍历所有歌手mv，再随机获取6首mv
        Random r = new Random();
        List randomNum = new ArrayList();
        while (randomNum.size() != 6) {
            int num = r.nextInt(solrBean.getSolrBeanList().size());
            if (!randomNum.contains(num)) {
                randomNum.add(num);
            }
        }

        //最终的结果集
        SolrBean<CustomMv> solrBeanMv = new SolrBean<CustomMv>();

        int count = 0;
        boolean flag = false;
        List bean = new ArrayList<CustomMv>();
        for(int i = 0; i < solrBean.getSolrBeanList().size(); i++){
            for(int j = 0; j < randomNum.size(); j++){
                if(i == Integer.parseInt(randomNum.get(j).toString())){
                    bean.add(solrBean.getSolrBeanList().get(i));
                    break;
                }
            }
        }

        solrBeanMv.setSolrBeanList(bean);
        return solrBeanMv;
    }


    /**
     * 查询展示mv页面的所有内容所需要的数据
     *
     * @param mvClassName
     * @param page
     * @return
     */
    @Override
    public MvViewBean showService(int mvClassName, int page) {
        MvViewBean mvViewBean = new MvViewBean();

        //轮播图模块
        addSlideGraph(mvViewBean);

        //top10模块
        addTopTenMv(mvViewBean);

        //热门mv
        addHotMv(mvViewBean, mvClassName, page);

        return mvViewBean;
    }

    /**
     * 添加轮播
     * @param mvViewBean
     */
    private void addSlideGraph(MvViewBean mvViewBean) {
        SolrBean<CustomMv> slideGraphCollect = mvSolr.selectHotFiveMv();
        mvViewBean.setSlideGraphCollect(slideGraphCollect);
    }

    /**
     * 根据mv的id更新访问量  mv访问量+1  mv对应的歌手访问量+1
     *
     * @param id mvID
     */
    @Override
    public void updateMvListenerCount(Integer id) {

    }

    /**
     * 根据查询关键字查询mv
     *
     * @param searchKey 查询关键字
     * @return solrbean
     */
    @Override
    public SolrBean<CustomMv> selectMvBySearchKey(String searchKey,Integer n) {
        SolrBean<CustomMv> customMvSolrBean = mvSolr.selectMvBySearchKey(searchKey,n);
        return customMvSolrBean;
    }


    /**
     * 添加top集合
     *
     * @param mvViewBean
     */
    private void addTopTenMv(MvViewBean mvViewBean) {
        SolrBean<CustomMv> customMvSolrBean = mvSolr.selectPopMv(10);
        mvViewBean.setTopTenMvCollect(customMvSolrBean);
    }

    /**
     * 添加mv
     *
     * @param mvViewBean
     */
    private void addHotMv(MvViewBean mvViewBean, int mvClassName, int page) {
        Map<String,SolrBean<CustomMv>> hotMv = new HashMap<String,SolrBean<CustomMv>>();
        //设置分类
        mvViewBean.setMvClassName(mvClassName);
        //设置当前页
        mvViewBean.setPage(page);
        //计算起始行
        page = (page - 1) * 20;

        if (mvClassName == MAP_NEWMV_NUM) {
            SolrBean<CustomMv> customMvSolrBean = mvSolr.selectNewMv(page);
            hotMv.put(MAP_NEWMV,customMvSolrBean);
            Long foundNum = customMvSolrBean.getFoundNum();
            //设置总条数
            mvViewBean.setTotalMv(foundNum);
            //设置总页数
            mvViewBean.setTotalPage(new Double(Math.ceil(foundNum/20.0)).intValue());

        } else {
            //得到查询语种
            String languages = getLanguages(mvClassName);

            SolrBean<CustomMv> customMvSolrBean = mvSolr.selectHotMvByClassName(languages, page);


            Long foundNum = customMvSolrBean.getFoundNum();
            //设置总条数
            mvViewBean.setTotalMv(foundNum);
            //设置总页数
            mvViewBean.setTotalPage(new Double(Math.ceil(foundNum/20.0)).intValue());

            if(mvClassName == MAP_CHINA_NUM){
                hotMv.put(MAP_CHINA,customMvSolrBean);
            }else if(mvClassName == MAP_EAA_NUM){
                hotMv.put(MAP_EAA,customMvSolrBean);
            }else if(mvClassName == MAP_JAPAN_KOREA_NUM){
                hotMv.put(MAP_JAPAN_KOREA,customMvSolrBean);
            }
        }
        mvViewBean.setHotMvCollect(hotMv);
    }


    /**
     * 判断语种
     *
     * @param mvClassName
     * @return
     */
    private String getLanguages(int mvClassName) {
        String languages = null;
        switch (mvClassName) {
            case MAP_CHINA_NUM:
                languages = "华语";
                break;
            case MAP_EAA_NUM:
                languages = "欧美";
                break;
            case MAP_JAPAN_KOREA_NUM:
                languages = "日韩";
                break;
            default:
                languages = "未知";
        }
        return languages;
    }



}
