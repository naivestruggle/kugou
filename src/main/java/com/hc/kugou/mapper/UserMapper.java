package com.hc.kugou.mapper;

import com.hc.kugou.bean.custombean.CustomUser;

/**
 * @Author:
 * @Date:2019/4/11
 * @Description:com.hc.kugou.mapper
 * @Version:1.0
 */
public interface UserMapper {
    /**
     * 根据对象中不为null的属性进行多条件组合查询
     * @param keyUser   user对象
     * @return  一个user对象
     */
    CustomUser selectByNotNullFields(CustomUser keyUser)throws Exception;

    /**
     * 添加用户记录
     * @param user  用户信息
     */
    void insertUser(CustomUser user);
}
