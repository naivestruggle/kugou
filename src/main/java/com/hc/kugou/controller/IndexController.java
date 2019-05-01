package com.hc.kugou.controller;

import com.hc.kugou.bean.IndexViewBean;
import com.hc.kugou.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author:
 * @Date:2019/4/30
 * @Description:com.hc.kugou.controller     主页Controller
 * @Version:1.0
 */
@Controller
public class IndexController {
    @Autowired
    private IndexService indexService;

    @GetMapping("index.show")
    public String fun1(Model model){
        IndexViewBean indexViewBean = indexService.showService();
        return "index";
    }
}
