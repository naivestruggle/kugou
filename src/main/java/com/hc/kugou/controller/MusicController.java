package com.hc.kugou.controller;

import com.alibaba.fastjson.JSONObject;
import com.hc.commons.ResponseUtils;
import com.hc.commons.StringUtils;
import com.hc.kugou.bean.MusicPlayList;
import com.hc.kugou.bean.custombean.CustomMusic;
import com.hc.kugou.bean.custombean.CustomMusicPlayList;
import com.hc.kugou.bean.custombean.CustomUser;
import com.hc.kugou.bean.custombean.SimpleSongBean;
import com.hc.kugou.service.MusicPlayListService;
import com.hc.kugou.service.SimpleSongService;
import com.hc.kugou.solr.SolrBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Author:
 * @Date:2019/4/11
 * @Description:com.hc.kugou.controller
 * @Version:1.0
 */
@Controller
public class MusicController {
    @Autowired
    private SimpleSongService simpleSongService;

    @Autowired
    private MusicPlayListService musicPlayListService;

    @GetMapping("simpleSong.html/{musicId}")
    public String simpleSongHtml(@PathVariable("musicId") Integer musicId, Model model, HttpSession session){
        //得到当前登录对象
        CustomUser loginedUser = (CustomUser)session.getAttribute(StringUtils.LOGINED_USER);
        //得到单曲播放页面对象
        SimpleSongBean simpleSongBean = simpleSongService.play(musicId,loginedUser,session);

        model.addAttribute("simpleSongBean",simpleSongBean);
        return "playsong";
    }

    /**
     * 加载播放列表
     * @param session   会话对象
     * @return  jsonobject对象
     */
    @ResponseBody
    @PostMapping("music.loadMusicPlayList.ajax")
    public JSONObject fun1(HttpSession session){
        JSONObject jsonObject = new JSONObject();
        //取出登录对象
        CustomUser loginedUser = (CustomUser)session.getAttribute(StringUtils.LOGINED_USER);
        //取出当前session中的播放列表对象
        CustomMusicPlayList sessionMusicPlayList = (CustomMusicPlayList)session.getAttribute(StringUtils.PLAT_SONG_LIST_PRE);

        try {
            //得到播放列表对象
            CustomMusicPlayList musicPlayList = musicPlayListService.loadMusicPlayList(loginedUser,sessionMusicPlayList);
            jsonObject.put("code",1);
            jsonObject.put("musicPlayList",musicPlayList);
        } catch (Exception e) {
            ResponseUtils.responseException(jsonObject,e);
        }
        return jsonObject;
    }

    /**
     * 清空播放列表
     * @param session 会话对象
     * @return  jsonobject对象
     */
    @ResponseBody
    @PostMapping("music.clearAllMusicPlayList.ajax")
    public JSONObject fun2(HttpSession session){
        JSONObject jsonObject = new JSONObject();
        //取出当前登录对象
        CustomUser loginedUser = (CustomUser)session.getAttribute(StringUtils.LOGINED_USER);
        //清空
        try {
            musicPlayListService.clearAllMusicPlayList(loginedUser,session);
            ResponseUtils.responseNoException(jsonObject);
        } catch (Exception e) {
            ResponseUtils.responseException(jsonObject,e);
        }
        return jsonObject;
    }

    /**
     * 将当前播放歌曲添加至播放列表
     * @param session   会话对象
     * @param musicId   音乐ID
     * @return  jsonobject对象
     */
    @ResponseBody
    @PostMapping("music.addNowPlayMusicToMusicPlayList.ajax")
    public JSONObject fun3(HttpSession session,Integer musicId){
        JSONObject jsonObject = new JSONObject();
        //取出当前登录对象
        CustomUser loginedUser = (CustomUser)session.getAttribute(StringUtils.LOGINED_USER);
        try {
            //添加
            simpleSongService.addNowPlayMusicToMusicPlayList(loginedUser,session,musicId);
            //得到播放列表对象
            CustomMusicPlayList sesionMusicPlayList = (CustomMusicPlayList)session.getAttribute(StringUtils.PLAT_SONG_LIST_PRE);
            CustomMusicPlayList musicPlayList = musicPlayListService.loadMusicPlayList(loginedUser,sesionMusicPlayList);

            jsonObject.put("musicPlayList",musicPlayList);
            ResponseUtils.responseNoException(jsonObject);
        } catch (Exception e) {
            ResponseUtils.responseException(jsonObject,e);
        }
        return jsonObject;
    }

    /**
     * 根据歌曲id得到歌曲对象
     * @param musicId  歌曲id
     * @return  jsonobject对象
     */
    @ResponseBody
    @PostMapping("music.getOneMusicInfo.ajax")
    public JSONObject fun4(Integer musicId){
        JSONObject jsonObject = new JSONObject();
        try {
            CustomMusic music = simpleSongService.getOneMusicInfo(musicId);
            jsonObject.put("music",music);
            ResponseUtils.responseNoException(jsonObject);
        } catch (Exception e) {
            ResponseUtils.responseException(jsonObject,e);
        }
        return jsonObject;
    }

}
