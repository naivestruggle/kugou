package com.hc.kugou.controller;

import com.hc.commons.MvUtils;
import com.hc.kugou.bean.Music;
import com.hc.kugou.bean.Mv;
import com.hc.kugou.bean.Singer;
import com.hc.kugou.mapper.MusicMapper;
import com.hc.kugou.mapper.MvMapper;
import com.hc.kugou.mapper.SingerMapper;
import com.hc.kugou.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.util.List;

/**
 * @Author:
 * @Date:2019/5/2
 * @Description:com.hc.kugou.controller
 * @Version:1.0
 */
@Controller
public class HelloController {

    @Autowired
    private IndexService indexService;

    @Autowired
    private MvMapper mvMapper;

    @Autowired
    private MusicMapper musicMapper;
    @Autowired
    private SingerMapper singerMapper;

    @RequestMapping("updateMv")
    public String hello(){
        //78143
        Long id = 67131L;
        while(id < 78144) {
            Music music = musicMapper.selectMusicById(id);
            //获取作者ID
            Long authorId = music.getAuthorId();
            //查询作者是哪个语种
            String language = null;
            List<Singer> singerList = singerMapper.findBySingerId(authorId);
            if (singerList == null || singerList.size()==0) {
                language = "未知语种";
            } else {
                language = "";
                for(Singer singer:singerList){
                    language += singer.getClassName()+"、";
                }
            }
            String mvName = music.getAudioName();
            //用歌曲名去调用python查询是否有MV
            Mv mv = MvUtils.getMv(mvName);
            System.out.println("id:" + id +"线程1"+ "  歌曲名：" + mvName);
            //如果没有mv  就直接都设置为0
            if (mv == null) {
                musicMapper.updateHasMv(id, 0l, 0, language);
            } else {
                //如果有网上有Mv   先查询数据库中是否已经有了这个数据
                List<Mv> mvList = mvMapper.findMvByName(mv.getMvName());
                if (mvList == null || mvList.size() == 0) {
                    //如果没有  就将网上的数据存入数据库
                    mv.setClassName(language);
                    mvMapper.insert(mv);
                    musicMapper.updateHasMv(id, mv.getId(), 1, language);
                } else {
                    //如果已经有了   那么将Mv的id存入music  同时修改更新日期
                    Mv mv1 = mvList.get(0);
                    musicMapper.updateHasMv(id, mv1.getId(), 1, language);
                    mvMapper.updateTime(mv1.getId(), new Date(System.currentTimeMillis()));
                }
            }
            id++;
        }
        return "index";
    }
}
