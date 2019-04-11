package com.hc.kugou.service;

import com.hc.kugou.bean.custombean.SingerViewBean;

/**
 * @author ck
 * @create 2019-05-09 18:29
 */
public interface SingerService {
    /**
     * 查询展示Singer页面所需要的数据
     * @param singerClassName   语种
     * @param singerSindex      A-Z类型
     * @param page              页数
     * @return
     */
    SingerViewBean singer(int singerClassName, String singerSindex, int page);
}
