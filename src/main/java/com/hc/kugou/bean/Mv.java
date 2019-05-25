package com.hc.kugou.bean;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * @Author:
 * @Date:2019/4/30
 * @Description:com.hc.kugou.bean   mv
 * @Version:1.0
 */
@Data
public class Mv implements Serializable {
    /**
     * hashcode     solr中的
     */
    private String hashCode;
    /**
     * hashcode  mysql中的
     */
    private String mvHashCode;
    /**
     * ID
     */
    private Long mvId;

    /**
     * mv名字
     */
    private String mvName;
    /**
     * 流畅
     */
    private String mvHcUrl;

    /**
     * 高清
     */
    private String mvHdUrl;

    /**
     * 超清
     */
    private String mvBdUrl;

    /**
     * 封面
     */
    private String mvHeadImage;

    /**
     * 数据库更新时间
     */
    private Date mvUpdateTime;

    /**
     * 语种
     */
    private String mvClassName;

    /**
     * 播放量
     */
    private Long mvListenerCount;

    /**
     * 发行时间
     */
    private Date mvCreateTime;

}
