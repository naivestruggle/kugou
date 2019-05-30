package com.hc.kugou.service;

import com.hc.kugou.bean.custombean.CustomMusic;

import java.util.List;

/**
 * @author ck
 * @create 2019-05-27 16:16
 */
public interface ListService {

    /**
     * 查询飙升榜单
     * @return
     */
    List<CustomMusic> querySoarList();

    /**
     * 查询top500歌曲
     * @return
     */
    List<CustomMusic> queryTop500();

    /**
     * 查询新歌榜单
     * @param className  1：华语 2：欧美 3：日韩
     * @return
     */
    List<CustomMusic> queryNewCNList(Integer className);
}
