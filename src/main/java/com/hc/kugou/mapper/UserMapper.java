package com.hc.kugou.mapper;

import com.hc.kugou.bean.custombean.CustomUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
     * @throws Exception
     */
    CustomUser selectByNotNullFields(CustomUser keyUser)throws Exception;

    /**
     * 添加用户记录
     * @param user  用户信息
     */
    void insertUser(CustomUser user);

    /**
     * 根据id查询用户
     * @param userId 用户id
     * @return
     * @throws Exception
     */
    CustomUser selectById(Integer userId)throws Exception;

    /**
     * 根据电话查询用户
     * @param account 电话
     * @return
     */
    @Select("select count(1) from kugou_user where user_tel = #{account}")
    Integer selectByTelToCount(@Param("account") String account);

    /**
     * 根据邮箱查询用户
     * @param account 邮箱
     * @return
     */
    @Select("select count(1) from kugou_user where user_email = #{account}")
    Integer selectByEmailToCount(@Param("account") String account);

}
