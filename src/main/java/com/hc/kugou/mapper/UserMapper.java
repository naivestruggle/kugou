package com.hc.kugou.mapper;

import com.hc.kugou.bean.custombean.CustomUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    /**
     * 修改头像
     *
     * @param userId  用户id
     * @param sqlPath 头像路径
     */
    @Update("update kugou_user set user_imgpath = #{sqlPath} where user_id = #{userId}")
    void updateHeadImage(@Param("userId")Integer userId, @Param("sqlPath")String sqlPath);

    /**
     * 修改用户信息
     *
     * @param info 信息
     */
    @Update("update kugou_user set user_username = #{info.userUsername} ,user_sex = #{info.userSex} ,user_birthday = #{info.userBirthday} " +
            ",user_addr = #{info.userAddr} ,user_signature = #{info.userSignature} where user_id = #{info.userId} ")
    void updateUserInfo(@Param("info")CustomUser info) throws Exception;

    /**
     * 根据用户id和密码查询用户的个数
     *
     * @param userId 用户id
     * @param pwd    用户密码
     * @return
     */
    @Select("select count(1) from kugou_user where user_id = #{userId} and user_password = #{pwd} ")
    Integer selectUserCountByIdAndPwd(@Param("userId")Integer userId, @Param("pwd")String pwd)throws Exception;

    /**
     * 通过id修改密码
     *
     * @param userId 用户id
     * @param newPwd 新密码
     */
    @Update("update kugou_user set user_password = #{newPwd} where user_id = #{userId}")
    void updatePwdById(@Param("userId")Integer userId, @Param("newPwd")String newPwd)throws Exception;

    /**
     * 根据用户ID修改邮箱
     *
     * @param userId 用户ID
     * @param email  邮箱
     * @throws Exception 抛出异常
     */
    @Update("update kugou_user set user_email = #{email} where user_id = #{userId} ")
    void updateEmailById(@Param("userId")Integer userId, @Param("email")String email) throws Exception;

    /**
     * 换绑手机号
     *
     * @param userId  用户ID
     * @param account 手机号
     * @throws Exception 抛出异常
     */
    @Update("update kugou_user set user_tel = #{account} where user_id = #{userId} ")
    void updateUserTelById(@Param("userId")Integer userId, @Param("account")String account) throws Exception;

    /**
     * 设置密保
     *
     * @param userId           用户id
     * @param userSafeQuestion 密保
     */
    @Update("update kugou_user set user_safe_question = #{userSafeQuestion} where user_id = #{userId} ")
    void updateEnsureQuestion(@Param("userId")Integer userId, @Param("userSafeQuestion")String userSafeQuestion)throws Exception;

    /**
     * 根据openid查询用户
     * @param openid  qq用户的唯一标识
     * @return
     */
    CustomUser queryByOpenId(@Param("openid") String openid);

    /**
     * 根据openid查询用户是否存在
     * @param openid
     * @return
     */
    @Select("select count(1) from kugou_user where user_qq = #{openid}")
    Integer queryByOpenIdCount(@Param("openid") String openid);

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    CustomUser queryById(@Param("userId") Integer userId);
}
