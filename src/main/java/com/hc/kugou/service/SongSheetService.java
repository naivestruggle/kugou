package com.hc.kugou.service;

import com.hc.kugou.bean.custombean.CustomMusicList;

/**
 * @author ck
 * @create 2019-05-14 19:30
 */
public interface SongSheetService {

    CustomMusicList selectMusicListById(Integer musicListId);

}
