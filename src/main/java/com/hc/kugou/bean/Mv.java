package com.hc.kugou.bean;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * @Author:
 * @Date:2019/4/30
 * @Description:com.hc.kugou.bean
 * @Version:1.0
 */
@Data
public class Mv implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 哈希
     */
    private String mvHash;

    /**
     * mv名字
     */
    private String mvName;
    /**
     * 流畅
     */
    private String hcUrl;

    /**
     * 高清
     */
    private String hdUrl;

    /**
     * 超清
     */
    private String bdUrl;

    /**
     * 封面
     */
    private String headImage;

    /**
     * 数据库更新时间
     */
    private Date updateTime;

    /**
     * 语种
     */
    private String className;

    /**
     * 播放量
     */
    private Long listenerCount;
}
