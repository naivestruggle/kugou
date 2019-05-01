package com.hc.kugou.mapper;

import com.hc.kugou.bean.Singer;import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:
 * @Date:2019/4/30
 * @Description:com.hc.kugou.mapper
 * @Version:1.0
 */
public interface SingerMapper {
    /**
     * 根据语种查询歌手集合
     *
     * @param className 语种
     * @param n         查询数量
     * @return 歌手集合
     */
    List<Singer> selectSingerByClassName(@Param("className")String className, @Param("n")int n);
}
