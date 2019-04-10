package com.hc.kugou.bean;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * @Author:
 * @Date:2019/4/7
 * @Description:com.hc.kugou.bean
 * @Version:1.0
 */
@Data
public class User implements Serializable {
    /**
     * hashcode
     */
    private String hashCode;
    /**
     * id
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String userUsername;
    /**
     * 账号
     */
    private String userAccount;
    /**
     * 密码
     */
    private String userPassword;
    /**
     * 手机号
     */
    private String userTel;
    /**
     * 邮箱
     */
    private String userEmail;
    /**
     * QQ
     */
    private String userQq;
    /**
     * 微信
     */
    private String userWechat;
    /**
     * 性别
     */
    private Integer userSex;
    /**
     * 生日
     */
    private Date userBirthday;
    /**
     * 地址
     */
    private String userAddr;
    /**
     * 个性签名
     */
    private String userSignature;
    /**
     * 头像
     */
    private String userImgpath;

    /**
     * 是否会员
     */
    private Integer userIsVip;

    /**
     * 账号状态
     */
    private Integer userStatus;
}
