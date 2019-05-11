package com.hc.kugou.bean;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * @Author:
 * @Date:2019/4/10
 * @Description:com.hc.kugou.bean       专辑
 * @Version:1.0
 */
@Data
public class Album implements Serializable {
    /**
     * hashcode
     */
    private String hashCode;
    /**
     * id
     */
    private Integer albumId;
    /**
     * 专辑名
     */
    private String albumName;
    /**
     * 创建专辑的歌手id
     */
    private Integer albumSingerId;
    /**
     * 创建专辑的歌手名
     */
    private String albumSingerName;
    /**
     * 专辑所在公司
     */
    private String albumCompany;
    /**
     * 专辑创建时间
     */
    private Date albumCreatetime;
    /**
     * 专辑描述
     */
    private String albumDescribe;
    /**
     * 专辑封面
     */
    private String albumImgpath;
    /**
     * 专辑收听数
     */
    private Long albumListenerCount;
    /**
     * 专辑状态
     */
    private Integer albumStatus;
}
