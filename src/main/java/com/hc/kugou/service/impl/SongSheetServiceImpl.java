package com.hc.kugou.service.impl;

import com.hc.commons.StringUtils;
import com.hc.kugou.bean.custombean.CustomMusicList;
import com.hc.kugou.bean.custombean.CustomUser;
import com.hc.kugou.mapper.MusiclistMapper;
import com.hc.kugou.service.SongSheetService;
import com.hc.kugou.service.exception.music.MusicExistsException;
import com.hc.kugou.solr.SolrBean;
import com.hc.kugou.solr.MusicListSolr;
import org.springframework.beans.factory.annotation.Autowired;
import com.hc.kugou.service.exception.songsheet.SongSheetAddException;
import com.hc.kugou.service.exception.songsheet.SongSheetException;
import com.hc.kugou.service.exception.songsheet.SongSheetExistsException;
import com.hc.kugou.service.exception.songsheet.SongSheetNotExistsException;
import com.hc.kugou.service.exception.user.UserNotExistsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.Date;


/**
 * @author ck
 * @create 2019-05-14 19:30
 */
@Service("songSheetService")
@Transactional(rollbackFor = {SongSheetException.class})
public class SongSheetServiceImpl implements SongSheetService {

    @Autowired
    private MusiclistMapper musiclistMapper;

    @Autowired
    private MusicListSolr musicListSolr;


    @Override
    public CustomMusicList selectMusicListById(Integer musicListId) throws Exception{
        CustomMusicList customMusicList = musiclistMapper.selectMusicListById(musicListId);
        if(customMusicList == null){
            throw new SongSheetNotExistsException("歌单不存在");
        }
        return customMusicList;
    }

    @Override
    public void addSongSheet(CustomMusicList customMusicList,HttpSession session) throws Exception {
        if(session.getAttribute(StringUtils.LOGINED_USER) == null){
            throw new UserNotExistsException("用户未登录");
        }
        //得到用户id和用户名
        Integer userId = ((CustomUser)session.getAttribute(StringUtils.LOGINED_USER)).getUserId();
        String userName = ((CustomUser)session.getAttribute(StringUtils.LOGINED_USER)).getUserUsername();
        //设置用户id和用户名
        customMusicList.setMusicListUserId(userId);
        customMusicList.setMusicListUserUsername(userName);
        customMusicList.setMusicListUpdateTime(new Date(System.currentTimeMillis()));
        //查询当前用户是否已创建该歌单
        Integer count = musiclistMapper.selectMusiclistCountByIdName(customMusicList);
        if(count > 0){
            throw new SongSheetExistsException("歌单已存在");
        }
        //添加歌单
        musiclistMapper.addSongSheet(customMusicList);
        //查询歌单是否已创建
        count = musiclistMapper.countMusicListById(customMusicList.getMusicListId());
        if(count == 0){
            throw new SongSheetAddException("新建歌单失败，请稍后再试");
        }

    }

    @Override
    public void delSongSheet(Integer musicListId, HttpSession session) throws Exception{
        if(session.getAttribute(StringUtils.LOGINED_USER) == null){
            throw new UserNotExistsException("用户未登录");
        }
        //得到用户id
        Integer userId = ((CustomUser)session.getAttribute(StringUtils.LOGINED_USER)).getUserId();
        //删除歌单
        musiclistMapper.delSongSheet(musicListId,userId);
        //删除当前歌单下的所有歌曲
        musiclistMapper.delAllSongInList(musicListId);

    }

    @Override
    public CustomMusicList updateSongSheet(CustomMusicList customMusicList, HttpSession session) throws Exception {
        if(session.getAttribute(StringUtils.LOGINED_USER) == null){
            throw new UserNotExistsException("用户未登录");
        }
        if(customMusicList == null){
            throw new SongSheetNotExistsException("歌单信息不能为空");
        }

        musiclistMapper.updateSongSheet(customMusicList);
        //将修改后的歌单信息返回
        customMusicList = selectMusicListById(customMusicList.getMusicListId());

        return customMusicList;
    }

    @Override
    public void addMusicToSongSheet( Integer musicListId, Integer musicId, HttpSession session) throws Exception {
        if(session.getAttribute(StringUtils.LOGINED_USER) == null){
            throw new UserNotExistsException("用户未登录");
        }
        //查询歌单中是否存在该歌曲
        Integer count = musiclistMapper.queryMusicIsExists(musicListId,musicId);
        if(count > 0){
            throw new MusicExistsException("歌曲已存在");
        }
        musiclistMapper.addMusicToSongSheet(musicListId,musicId);
    }

    @Override
    public void delMusicFromSongSheet(Integer musicListId, Integer musicId,  HttpSession session) throws Exception {
        if(session.getAttribute(StringUtils.LOGINED_USER) == null){
            throw new UserNotExistsException("用户未登录");
        }
        musiclistMapper.delMusicFromSongSheet(musicListId,musicId);
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
