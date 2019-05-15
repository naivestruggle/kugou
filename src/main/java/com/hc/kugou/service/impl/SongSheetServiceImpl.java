package com.hc.kugou.service.impl;

import com.hc.kugou.bean.custombean.CustomMusicList;
import com.hc.kugou.mapper.MusiclistMapper;
import com.hc.kugou.service.SongSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author ck
 * @create 2019-05-14 19:30
 */
@Service("songSheetService")
public class SongSheetServiceImpl implements SongSheetService {

    @Autowired
    MusiclistMapper musiclistMapper;

    @Override
    public CustomMusicList selectMusicListById(Integer musicListId) {
        CustomMusicList customMusicList = musiclistMapper.selectMusicListById(musicListId);

        return customMusicList;
    }
}
