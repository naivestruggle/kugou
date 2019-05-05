package com.hc.kugou.mapper;

import com.hc.kugou.bean.Mv;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
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

    /**
     * 修改更新日期
     *
     * @param id
     * @param updateTime
     */
    void updateTime(@Param("id")Long id, @Param("updateTime")Date updateTime);

    /**
     * 根据语种查询推荐MV
     * @param className 语种
     * @param n 查询条数
     * @return  Mv集合
     */
    List<Mv> selectPopMvByClassName(@Param("className")String className, @Param("n")int n);

    Mv selectById(@Param("id")long id);

    /**
     * 查询
     *
     * @param start 从第几条开始
     * @param end   查多少条
     * @return 结果集
     */
    List<Mv> selectCopy(@Param("start")int start, @Param("end")int end);
}
