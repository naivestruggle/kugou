package com.hc.kugou.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:
 * @Date:2019/4/30
 * @Description:com.hc.kugou.bean   歌手类
 * @Version:1.0
 */
@Data
public class Singer implements Serializable {
    /**
     * hashcode
     */
    private String hashCode;
    /**
     * ID
     */
    private Long singerId;
    /**
     * 语种类型
     */
    private String singerClassName;
    /**
     * A-Z类型
     */
    private String singerSindex;
    /**
     * 歌手姓名
     */
    private String singerSingername;
    /**
     * 歌手ID
     */
    private Long singerSingerid;
    /**
     * 歌手照片路径
     */
    private String singerImgurl;

    /**
     * 标记
     */
    private Integer singerFlag;
    /**
     * 听他的歌的次数
     */
    private Long singerListenerCount;
}
