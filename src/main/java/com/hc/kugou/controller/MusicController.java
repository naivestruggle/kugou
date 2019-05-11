package com.hc.kugou.controller;

import com.hc.commons.StringUtils;
import com.hc.kugou.bean.custombean.CustomUser;
import com.hc.kugou.bean.custombean.MusicPlayList;
import com.hc.kugou.bean.custombean.SimpleSongBean;
import com.hc.kugou.service.SimpleSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("simpleSong.html/{musicId}")
    public String simpleSongHtml(@PathVariable("musicId") Integer musicId, Model model, HttpSession session){
        //得到当前登录对象
        CustomUser loginedUser = (CustomUser)session.getAttribute(StringUtils.LOGINED_USER);
        System.out.println("获取到的歌曲id："+musicId);
        System.out.println("当前登录用户对象："+loginedUser);

        //取出当前session中的播放列表对象
        MusicPlayList sessionMusicPlayList = (MusicPlayList)session.getAttribute(StringUtils.PLAT_SONG_LIST_PRE);
        //得到单曲播放页面对象
        SimpleSongBean simpleSongBean = simpleSongService.play(musicId,loginedUser,sessionMusicPlayList);
        model.addAttribute("simpleSongBean",simpleSongBean);
        return "playsong";
    }
}
