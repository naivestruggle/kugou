package com.hc.kugou.service.impl;

import com.hc.kugou.bean.custombean.CustomMusicList;
import com.hc.kugou.mapper.MusiclistMapper;
import com.hc.kugou.service.SongSheetService;
import com.hc.kugou.solr.SolrBean;
import com.hc.kugou.solr.MusicListSolr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author ck
 * @create 2019-05-14 19:30
 */
@Service("songSheetService")
public class SongSheetServiceImpl implements SongSheetService {

    @Autowired
    private MusiclistMapper musiclistMapper;

    @Autowired
    private MusicListSolr musicListSolr;

    @Override
    public CustomMusicList selectMusicListById(Integer musicListId) {
        CustomMusicList customMusicList = musiclistMapper.selectMusicListById(musicListId);

        return customMusicList;
    }

    /**
     * 根据关键字查询歌单集合
     *
     * @param searchKey 关键字
     * @return 歌单集合
     */
    @Override
    public SolrBean<CustomMusicList> selectMusicListSearchBySearchKey(String searchKey) {
        return musicListSolr.selectMusicListSearchBySearchKey(searchKey,50);
    }
}
