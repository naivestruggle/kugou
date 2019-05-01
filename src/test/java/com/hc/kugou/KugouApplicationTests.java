package com.hc.kugou;

import com.alibaba.fastjson.JSONObject;
import com.hc.commons.MvUtils;
import com.hc.kugou.bean.IndexViewBean;
import com.hc.kugou.bean.Music;
import com.hc.kugou.bean.Mv;
import com.hc.kugou.mapper.MusicMapper;
import com.hc.kugou.service.IndexService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KugouApplicationTests {

    @Autowired
    private IndexService indexService;

    @Autowired
    private MusicMapper musicMapper;
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



}
