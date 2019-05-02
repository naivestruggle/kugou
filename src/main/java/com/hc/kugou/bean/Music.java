package com.hc.kugou.bean;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * @Date:2019/4/30
 * @Description:com.hc.kugou.bean  音乐类
 * @Version:1.0
 */
@Data
public class Music implements Serializable {
    /**
     * ID
     */
    private Integer id;

    /**
     * 作者ID
     */
    private Long authorId;

    /**
     * 作者姓名
     */
    private String authorName;

    /**
     * 音频ID
     */
    private Long audioId;

    /**
     * 音频名
     */
    private String audioName;

    /**
     * 歌曲名
     */
    private String songName;

    /**
     * 哈希值
     */
    private String hashCode;

    /**
     * 歌曲文件大小
     */
    private Long filesize;

    /**
     * 歌曲时长
     */
    private Long timelength;


    /**
     * 是否有唱片集
     */
    private Integer haveAlbum;


    /**
     * 唱片集ID
     */
    private Long albumId;

    /**
     * 唱片集名
     */
    private String albumName;

    /**
     * 是否有mv
     */
    private Integer haveMv;


    /**
     * mvID
     */
    private Integer videoId;


    /**
     * 权限
     */
    private Integer privilege;
    /**
     * 权限2
     */
    private Integer privilege2;

    /**
     * 歌曲播放链接
     */
    private String playUrl;

    /**
     * 图片路径
     */
    private String img;

    /**
     * 歌词
     */
    private String lyrics;

    /**
     * 听这首歌的人数
     */
    private Long listenerCount;

    /**
     * 语种
     */
    private String className;
//    https://www.kugou.com/song/#hash=44C60678EF095B12845D2056279B8E7B&album_id=0
//    http://fs.w.kugou.com/201905010911/92c9775ff309fc2b39f3f581548aaa36/G159/M04/1F/14/P4cBAFzCxBeAKoBiAD4dE_mpuUE604.mp3
//    http://fs.w.kugou.com/201904271611/c583e0dfa2140775e39f741e89bc3d7a/G159/M04/1F/14/P4cBAFzCxBeAKoBiAD4dE_mpuUE604.mp3
//
//    http://fs.w.kugou.com/201905010905/bf3e32aae7706a1aeea11b9690b01025/G157/M05/1B/0D/PYcBAFzG90GAVTDLADHU-kEwu2E876.mp3
//    http://fs.w.kugou.com/201905011540/9b68b0e167809424f506dd849fe91b21/G113/M07/07/1F/UZQEAFluVU2AGS4OAD1d-2CAjGQ832.mp3
//    http://fs.w.kugou.com/201904271540/42cd4a6cb04a53a3ee259f0e033dcb32/G082/M03/13/18/MpQEAFhfN7KAP67vADb2_EgpN7g297.mp3
}
