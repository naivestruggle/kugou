package com.hc.kugou.controller;

import com.hc.kugou.bean.custombean.IndexViewBean;
import com.hc.kugou.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
