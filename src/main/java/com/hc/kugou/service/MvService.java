package com.hc.kugou.service;

import com.hc.kugou.bean.Mv;
import com.hc.kugou.bean.custombean.CustomMv;
import com.hc.kugou.bean.custombean.MvViewBean;
import com.hc.kugou.solr.SolrBean;

/**
 * @Author:
 * @Date:2019/5/1
 * @Description:com.hc.kugou.service
 * @Version:1.0
 */
public interface MvService {
    /**
     * 根据名字模糊查找mv
     * 先在数据库查询  如果没有就调用python脚本查询
     * @param mvName    mv名
     * @return  Mv对象
     */
    Mv findByName(String mvName);

    /**
     * 通过mvid查询mv信息
     * @param mvId  mvid
     * @return 返回solrBean的MV对象
     */
    SolrBean<CustomMv> selectMvById(Integer mvId);

    /**
     * 根据当前播放的mv推荐相应的mv
     * @param customMvSolrBean  当前播放的mv信息
     * @return 返回solrBean的MV集合
     */
    SolrBean<CustomMv> recommendMv(SolrBean<CustomMv> customMvSolrBean);


    /**
     * 查询展示mv页面的所有内容所需要的数据
     * @param mvClassName 语种
     * @param page   页数
     * @return
     */
    MvViewBean showService(int mvClassName, int page);

    /**
     * 根据mv的id更新访问量  mv访问量+1  mv对应的歌手访问量+1
     * @param id    mvID
     */
    void updateMvListenerCount(Integer id);

    /**
     * 根据查询关键字查询mv
     * @param searchKey 查询关键字
     * @return  solrbean
     */
    SolrBean<CustomMv> selectMvBySearchKey(String searchKey,Integer n);
}
