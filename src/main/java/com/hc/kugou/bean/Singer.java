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
     * ID
     */
    private Long id;
    /**
     * 语种类型
     */
    private String className;
    /**
     * A-Z类型
     */
    private String sindex;
    /**
     * 歌手姓名
     */
    private String singername;
    /**
     * 歌手ID
     */
    private Long singerid;
    /**
     * 歌手照片路径
     */
    private String imgurl;

    /**
     * 听他的歌的次数
     */
    private Long listenerCount;
}
