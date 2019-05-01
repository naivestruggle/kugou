package com.hc.kugou.service;

import com.hc.kugou.bean.Mv;

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
}
