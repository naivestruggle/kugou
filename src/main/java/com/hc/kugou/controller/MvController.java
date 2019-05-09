package com.hc.kugou.controller;

import com.hc.kugou.bean.Mv;
import com.hc.kugou.bean.custombean.CustomMv;
import com.hc.kugou.bean.custombean.MvViewBean;
import com.hc.kugou.service.MvService;
import com.hc.kugou.solr.SolrBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

/**
 * @author ck
 * @create 2019-05-08 11:04
 */

@Controller
public class MvController {

    @Autowired
    MvService mvService;

    /**
     * 播放mv请求
     * @param mvId  mvId
     * @param model
     * @return
     */
    @GetMapping("mvPlay.html/{mvId}")
    public String mvplay(@PathVariable("mvId") Integer mvId, Model model){

        //查询当前mvid对应的MV
        SolrBean<CustomMv> customMvSolrBean = mvService.selectMvById(mvId);

        //猜你喜欢 根据当前mv的歌手推荐mv
        SolrBean<CustomMv> recommendMvSolrBean =  mvService.recommendMv(customMvSolrBean);

        model.addAttribute("customMvSolrBean",customMvSolrBean);
        model.addAttribute("recommendMvSolrBean",recommendMvSolrBean);

        return "mvPlay";
    }

    @GetMapping({"mv.html/{mvClassName}/{page}"})
    public String mvHtml(@PathVariable("mvClassName") int mvClassName,@PathVariable("page") int page, Model model){
        MvViewBean mvViewBean =  mvService.showService(mvClassName,page);

        model.addAttribute("mvViewBean",mvViewBean);

        return "mv";
    }

    @GetMapping({"mv.html"})
    public void toMvHtml(Model model){

        mvHtml(1,1,model);
    }


}
