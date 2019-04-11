package com.hc.kugou.controller;

import com.hc.kugou.bean.custombean.SingerViewBean;
import com.hc.kugou.service.SingerService;
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

        //如果页数超出范围
        if(page > 5){
            page = 5;
        }else if(page < 1){
            page = 1;
        }

        SingerViewBean singerViewBean = singerService.singer(singerClassName, singerSindex, page);


        model.addAttribute("singerViewBean",singerViewBean);

        return "singer";
    }


    @GetMapping({"singer.html"})
    public void toMvHtml(Model model){
        singer(1,"ALL",1,model);
    }

}
