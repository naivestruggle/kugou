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

    /**
     * 根据歌手ID查询歌手
     *
     * @param authorId 歌手ID
     * @return 歌手对象
     */
    List<Singer> findBySingerId(@Param("authorId")Long authorId);

    /**
     * 查询
     *
     * @param start 从第几条开始
     * @param end   查多少条
     * @return 结果集
     */
    List<Singer> selectCopy(@Param("start")int start, @Param("end")int end);
}
