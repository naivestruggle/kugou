package com.hc.kugou.controller;

import com.hc.kugou.bean.custombean.SingerViewBean;
import com.hc.kugou.service.impl.SingerService;
import com.hc.kugou.solr.SingerSolr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ck
 * @create 2019-05-09 18:15
 */

@Controller
public class SingerContorller {

    @Autowired
    SingerService singerService;

    @GetMapping("singer.html/{singerClassName}/{singerSindex}/{page}")
    public String singer(@PathVariable("singerClassName") int singerClassName, @PathVariable("singerSindex") String singerSindex,
                         @PathVariable("page") int page, Model model){

        SingerViewBean singerViewBean = singerService.singer(singerClassName, singerSindex, page);

        model.addAttribute("singerViewBean",singerViewBean);

        return "singer";
    }


    @GetMapping({"singer.html"})
    public void toMvHtml(Model model){
        singer(1,"all",1,model);
    }

}
