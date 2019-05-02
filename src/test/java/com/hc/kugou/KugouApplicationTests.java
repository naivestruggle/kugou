package com.hc.kugou;

import com.alibaba.fastjson.JSONObject;
import com.hc.commons.MvUtils;
import com.hc.kugou.bean.IndexViewBean;
import com.hc.kugou.bean.Music;
import com.hc.kugou.bean.Mv;
import com.hc.kugou.controller.HeadController;
import com.hc.kugou.mapper.MusicMapper;
import com.hc.kugou.service.IndexService;
import org.apache.solr.client.solrj.SolrServerException;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class KugouApplicationTests {

    @Autowired
    private IndexService indexService;

    @Test
    public void contextLoads() {
        IndexViewBean indexViewBean = indexService.showService();
        System.out.println(indexViewBean);
    }

    @Test
    public void fun(){
    }

    @Test
    public void fun1(){
        String str = "{'毛不易':'wqeqweqw'}";
    }

    @Autowired
    HeadController headController;

    @Test
    public void test03(){
        try {
            //List<Mv> solr = headController.solr("周杰伦", "mv_name", Mv.class, 0, 4, "mv_name");
            List<Music> solr = headController.solr("周杰伦", "music_keywords", Music.class, 0, 4);
            System.out.println(solr);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
