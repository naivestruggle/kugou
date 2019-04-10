package com.hc.kugou.controller;

import com.hc.commons.StringUtils;
import com.hc.kugou.bean.custombean.CustomMusicPlayList;
import com.hc.kugou.bean.custombean.CustomUser;
import com.hc.kugou.bean.custombean.IndexViewBean;
import com.hc.kugou.bean.custombean.SimpleSongBean;
import com.hc.kugou.service.IndexService;
import com.hc.kugou.service.SimpleSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

/**
 * @Author:
 * @Date:2019/4/30
 * @Description:com.hc.kugou.controller     主页Controller
 * @Version:1.0
 */
@Controller
public class IndexController {
    /**
     * 主页业务类
     */
    @Autowired
    private IndexService indexService;

    @Autowired
    private SimpleSongService simpleSongService;


    @GetMapping("index.html")
    public String fun1(Model model){
        Long start = System.currentTimeMillis();
        IndexViewBean indexViewBean = indexService.showService();
        Long end = System.currentTimeMillis();

        model.addAttribute("indexViewBean",indexViewBean);


        Long times = end - start;
        System.out.println("查询所用时间："+times+"ms");
        return "index";
    }

    @GetMapping("simpleSong.html/{musicId}")
    public String fun2(@PathVariable("musicId") Integer musicId, Model model, HttpSession session){
        //得到当前登录对象
        CustomUser loginedUser = (CustomUser)session.getAttribute(StringUtils.LOGINED_USER);
        System.out.println("获取到的歌曲id："+musicId);
        System.out.println("当前登录用户对象："+loginedUser);

        //取出当前session中的播放列表对象
        CustomMusicPlayList sessionMusicPlayList = (CustomMusicPlayList)session.getAttribute(StringUtils.PLAT_SONG_LIST_PRE);
        //得到单曲播放页面对象
        SimpleSongBean simpleSongBean = simpleSongService.play(musicId,loginedUser,sessionMusicPlayList);
        model.addAttribute("simpleSongBean",simpleSongBean);
        return "playsong";
    }
}
