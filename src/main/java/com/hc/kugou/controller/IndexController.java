package com.hc.kugou.controller;

import com.hc.commons.StringUtils;
import com.hc.kugou.bean.custombean.MusicPlayList;
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

}
