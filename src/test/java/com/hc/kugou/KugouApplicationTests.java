package com.hc.kugou;

import com.alibaba.fastjson.JSONObject;
import com.hc.commons.MvUtils;
import com.hc.kugou.bean.IndexViewBean;
import com.hc.kugou.bean.Music;
import com.hc.kugou.bean.Mv;
import com.hc.kugou.bean.Singer;
import com.hc.kugou.mapper.MusicMapper;
import com.hc.kugou.mapper.MvMapper;
import com.hc.kugou.mapper.SingerMapper;
import com.hc.kugou.service.IndexService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.net.URL;
import java.sql.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KugouApplicationTests {

    @Autowired
    private IndexService indexService;

    @Autowired
    private MvMapper mvMapper;

    @Autowired
    private MusicMapper musicMapper;
    @Autowired
    private SingerMapper singerMapper;
    @Test
    public void contextLoads() {
        Long time1 = System.currentTimeMillis();
        IndexViewBean indexViewBean = indexService.showService();
        Long time2 = System.currentTimeMillis();
        Long time = time2-time1;
        System.out.println(indexViewBean);
        System.out.println("执行时间："+time+"ms");
    }

    @Test
    public void copy() throws IOException {
//        StringBuffer sb = new StringBuffer();
//        sb.append("insert into KuGo_music values");
//        List<Music> list = musicMapper.selectCopy(0,1000);
//        for(Music music : list){
//            sb.append("(");
//            sb.append(music.getId()+",");
//            sb.append(music.getAuthorId()+",");
//            sb.append("'"+music.getAuthorName()+"'"+",");
//            sb.append(music.getAudioId()+",");
//            sb.append("'"+music.getAudioName()+"'"+",");
//            sb.append("'"+music.getSongName()+"'"+",");
//            sb.append("'"+music.getHashCode()+"'"+",");
//            sb.append(music.getFilesize()+",");
//            sb.append(music.getTimelength()+",");
//            sb.append(music.getHaveAlbum()+",");
//            sb.append(music.getAlbumId()+",");
//            sb.append("'"+music.getAlbumName()+"'"+",");
//            sb.append(music.getHaveMv()+",");
//            sb.append(music.getVideoId()+",");
//            sb.append(music.getPrivilege()+",");
//            sb.append(music.getPrivilege2()+",");
//            sb.append("'"+music.getPlayUrl()+"'"+",");
//            sb.append("'"+music.getImg()+"'"+",");
//            sb.append("'"+music.getLyrics()+"'"+",");
//            sb.append(music.getListenerCount()+",");
//            sb.append("'"+music.getClassName()+"'");
//            sb.append(")");
//            sb.append(",");
//        }
//
//        String sql = sb.toString();
//        FileWriter fileWriter = new FileWriter(new File("g:/sql.txt"));
//        fileWriter.write(sql);
//        fileWriter.close();
    }

    /*119.29.229.221
    @Test
    public void fun(){
        //78144
        //mv 16969
        //select count(*) from KuGo_mv
        Long id = 77687L;
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
    }

     */

    @Test
    public void fun1(){
//        String str = "周杰伦、陶晶莹 - 我愿意 (2003陶子娱乐秀现场)";
//        String str1 = str.split("-")[0].trim().split("、")[0];
//        System.out.println();
    }



}
