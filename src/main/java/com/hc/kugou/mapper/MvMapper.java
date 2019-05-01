package com.hc.kugou.mapper;

import com.hc.kugou.bean.Mv;import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:
 * @Date:2019/5/1
 * @Description:com.hc.kugou.mapper
 * @Version:1.0
 */
public interface MvMapper {
    /**
     * 在数据库中查找mv
     *
     * @param mvName mvname
     * @return mv集合
     */
    List<Mv> findMvByName(@Param("mvName")String mvName);

    /**
     * 新增mv
     *
     * @param mv mv对象
     */
    void insert(@Param("mv")Mv mv);
}
