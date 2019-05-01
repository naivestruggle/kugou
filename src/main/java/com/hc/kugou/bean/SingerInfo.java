package com.hc.kugou.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:
 * @Date:2019/4/30
 * @Description:com.hc.kugou.bean   歌手信息类
 * @Version:1.0
 */
@Data
public class SingerInfo implements Serializable {
    /**
     * ID
     */
    Long id;
    /**
     * 歌手姓名
     */
    String singername;

    /**
     * 歌手ID
     */
    Long singerid;

    /**
     * 唱片数
     */
    Integer albumcount;

    /**
     * 歌曲数
     */
    Integer musiccount;

    /**
     * MV数
     */
    Integer mvcount;

    /**
     * 歌手介绍
     */
    String introduction;

}
