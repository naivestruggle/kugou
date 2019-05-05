package com.hc.kugou;

import com.hc.commons.MvUtils;
import com.hc.kugou.bean.Music;
import com.hc.kugou.bean.Mv;
import com.hc.kugou.bean.Singer;
import com.hc.kugou.controller.HeadController;
import com.hc.kugou.mapper.MusicMapper;
import com.hc.kugou.mapper.MvMapper;
import com.hc.kugou.mapper.SingerMapper;
import com.hc.kugou.service.IndexService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;
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
//        Long time1 = System.currentTimeMillis();
//        IndexViewBean indexViewBean = indexService.showService();
//        Long time2 = System.currentTimeMillis();
//        Long time = time2-time1;
//        System.out.println(indexViewBean);
//        System.out.println("执行时间："+time+"ms");
        System.out.println(singerMapper);
    }

    @Test
    public void fun(){
        //78143
        //mv 16969
        //select count(*) from KuGo_mv
        Long id = 77230L;
        while(id < 77687) {
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

    @Test
    public void fun1(){
//        String str = "周杰伦、陶晶莹 - 我愿意 (2003陶子娱乐秀现场)";
//        String str1 = str.split("-")[0].trim().split("、")[0];
//        System.out.println();
    }

    @Autowired
    HeadController headController;

    @Test
    public void test03(){
//        try {
//            //List<Mv> solr = headController.solr("周杰伦", "mv_name", Mv.class, 0, 4, "mv_name");
//            List<Music> solr = headController.solr("周杰伦", "music_keywords", Music.class, 0, 4);
//            System.out.println(solr);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (SolrServerException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
    }

}
