package com.hc.kugou.controller;

import com.alibaba.fastjson.JSONObject;
import com.hc.commons.ResponseUtils;
import com.hc.commons.StringUtils;
import com.hc.kugou.bean.custombean.CustomMusicList;
import com.hc.kugou.service.SongSheetService;
import com.hc.kugou.solr.SolrBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * @author ck
 * @create 2019-05-14 19:28
 */

@Controller
public class SongSheetController {

    @Autowired
    private SongSheetService songSheetService;

    private JSONObject jsonObject;

    /**
     * 根据歌单id查询歌单
     * @param musicListId  歌单id
     * @param model
     * @return
     */
    @GetMapping("songsheetlist/{musicListId}")
    public String querySongSheetList(@PathVariable("musicListId") Integer musicListId, Model model){

        try {
            CustomMusicList customMusicList = songSheetService.selectMusicListById(musicListId);

            model.addAttribute("customMusicList",customMusicList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "songsheetlist";
    }

    /**
     * 搜索页面  异步获取查询到的歌单对象
     * @param searchKey 查询关键字
     * @return    查询结果
     */
    @ResponseBody
    @PostMapping("musiclist.search.ajax")
    public JSONObject fun1(String searchKey){
        JSONObject jsonObject = new JSONObject();
        SolrBean<CustomMusicList> lists = songSheetService.selectMusicListSearchBySearchKey(searchKey);
        jsonObject.put("lists",lists);
        return jsonObject;
    }

    /**
     * 添加歌单
     * @param customMusicList  歌单对象
     * @param session
     * @return
     */
    @ResponseBody
    @PostMapping("song.addSongSheet.ajax")
    public JSONObject addSongSheet(CustomMusicList customMusicList, HttpSession session){
        jsonObject = new JSONObject();
        try {
            //添加歌单
            songSheetService.addSongSheet(customMusicList, session);

            //返回信息
            ResponseUtils.responseNoException(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseUtils.responseException(jsonObject,e);
        }
        return jsonObject;
    }

    /**
     * 删除歌单
     * @param musicListId 歌单id
     * @param session
     * @return
     */
    @ResponseBody
    @PostMapping("song.delSongSheet.ajax")
    public JSONObject delSongSheet(Integer musicListId,HttpSession session){
        jsonObject = new JSONObject();

        try {
            songSheetService.delSongSheet(musicListId,session);
            ResponseUtils.responseNoException(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseUtils.responseException(jsonObject,e);
        }
        return jsonObject;
    }

    /**
     * 编辑歌单信息
     * @param customMusicList  歌单
     * @param session
     * @return
     */
    @ResponseBody
    @PostMapping("song.updateSongSheet.ajax")
    public JSONObject updateSongSheet(CustomMusicList customMusicList,HttpSession session){
        jsonObject = new JSONObject();

        try {
            customMusicList = songSheetService.updateSongSheet(customMusicList,session);
            //将修改后的歌单信息返回
            jsonObject.put("code",1);
            jsonObject.put("customMusicList",customMusicList);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseUtils.responseException(jsonObject,e);
        }
        return jsonObject;
    }

    /**
     * 将歌曲添加到歌单
     * @param musicId  歌曲id
     * @param musicListId  歌单id
     * @param session
     * @return
     */
    @ResponseBody
    @PostMapping("song.addMusicToSongSheet.ajax")
    public JSONObject addMusicToSongSheet(Integer musicListId,Integer musicId,HttpSession session){
        jsonObject = new JSONObject();
        try {
            songSheetService.addMusicToSongSheet(musicListId,musicId,session);
            ResponseUtils.responseNoException(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseUtils.responseException(jsonObject,e);
        }
        return jsonObject;
    }

    /**
     * 将歌曲从歌单中删除
     * @param musicId  歌曲id
     * @param musicListId 歌单id
     * @param session
     * @return
     */
    @ResponseBody
    @PostMapping("song.delMusicFromSongSheet.ajax")
    public JSONObject delMusicFromSongSheet(Integer musicListId,Integer musicId,HttpSession session){
        jsonObject = new JSONObject();
        try {
            songSheetService.delMusicFromSongSheet(musicListId,musicId,session);
            ResponseUtils.responseNoException(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseUtils.responseException(jsonObject,e);
        }
        return jsonObject;
    }

}
