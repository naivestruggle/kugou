package com.hc.kugou.controller;

import com.alibaba.fastjson.JSONObject;
import com.hc.commons.ResponseUtils;
import com.hc.kugou.bean.custombean.CustomMusicList;
import com.hc.kugou.service.SongSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ck
 * @create 2019-05-14 19:28
 */

@Controller
public class SongSheetController {

    @Autowired
    private SongSheetService songSheetService;

    private JSONObject jsonObject;

    @GetMapping("songsheetlist/{musicListId}")
    private String querySongSheetList(@PathVariable("musicListId") Integer musicListId, Model model){

        try {
            CustomMusicList customMusicList = songSheetService.selectMusicListById(musicListId);

            model.addAttribute("customMusicList",customMusicList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "songsheetlist";
    }

}
