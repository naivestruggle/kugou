package com.hc.kugou.mapper;

import com.hc.kugou.bean.SingerInfo;import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:
 * @Date:2019/4/4
 * @Description:com.hc.kugou.mapper
 * @Version:1.0
 */
public interface SingerInfoMapper {
    /**
     * 查询
     *
     * @param start 从第几条开始
     * @param end   查多少条
     * @return 结果集
     */
    List<SingerInfo> selectCopy(@Param("start")int start, @Param("end")int end);
}
