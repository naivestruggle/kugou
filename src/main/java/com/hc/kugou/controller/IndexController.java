package com.hc.kugou.controller;

import com.alibaba.fastjson.JSONObject;
import com.hc.commons.CookieUtils;
import com.hc.kugou.bean.custombean.CustomMusic;
import com.hc.kugou.bean.custombean.CustomUser;
import com.hc.kugou.bean.custombean.IndexViewBean;
import com.hc.kugou.service.IndexService;
import com.hc.kugou.service.MusicService;
import com.hc.kugou.service.UserService;
import com.hc.kugou.service.exception.CustomException;
import com.hc.kugou.service.exception.music.NotFoundMusicException;
import com.hc.kugou.solr.SolrBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;

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
    private MusicService musicService;

    @Autowired
    private UserService userService;

    private static final String COOKIE_HISTORY_SEARCH = "yinmeng_history_search_info";

    /**
     * 显示主页信息
     * @param model
     * @return
     */
    @GetMapping(path = {"index.html","/","index"})
    public String fun1(Model model,HttpSession session,HttpServletRequest request){
        String loginedUserInfo = CookieUtils.getCookieValueByName("indream_autoLogin",request);
        if(!StringUtils.isEmpty(loginedUserInfo) && !"null".equals(loginedUserInfo)){
            String[] arr = loginedUserInfo.split("-");
            String account = arr[0];
            String password = arr[1];
            //自动登录
            CustomUser loginedUser = null;
            try {
                loginedUser = userService.loginService(account, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //将用户信息存入session
            session.setAttribute(com.hc.commons.StringUtils.LOGINED_USER,loginedUser);
        }
        IndexViewBean indexViewBean = indexService.showService();
        model.addAttribute("indexViewBean",indexViewBean);
        return "index";
    }

    /**
     * 获取头部搜索框默认显示的内容
     * @return
     */
    @ResponseBody
    @RequestMapping("index.searchBox.ajax")
    public JSONObject fun2(){
        JSONObject jsonObject = new JSONObject();
        String searchKey = indexService.getSearchBoxKey();
        jsonObject.put("code",1);
        jsonObject.put("searchString",searchKey);
        return jsonObject;
    }

    /**
     * 用户点击head的搜索按钮  先查询出单曲
     * @param model
     * @param searchKey
     * @return
     */
    @PostMapping("/search/{searchKey}")
    public String fun3(Model model, @PathVariable String searchKey, HttpServletRequest request, HttpServletResponse response){
        //判断用户输入的字符
        if(StringUtils.isEmpty(searchKey)){
            warning();
            return "";
        }

        //需要单曲  歌单  mv 集合
        //先查找单曲  进入搜索结果页面后再异步获取歌单和mv
        //将搜索关键词放入request域
        model.addAttribute("searchKey",searchKey);

        //查询单曲
        SolrBean<CustomMusic> solrBean = musicService.selectMusicBySearchKey(searchKey,50);
        model.addAttribute("solrMusicList",solrBean);
        return "search";
    }



    /**
     * 警告   是非正常操作   获取他的ip进行记录
     * @return
     */
    private String warning(){
        return null;
    }
}
