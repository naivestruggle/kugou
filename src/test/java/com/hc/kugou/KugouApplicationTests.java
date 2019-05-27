package com.hc.kugou;

import com.hc.commons.Code;
import com.hc.commons.MailUtils;
import com.hc.commons.MyRedis;
import com.hc.commons.StringUtils;
import com.hc.kugou.mapper.*;
import com.hc.kugou.service.IndexService;
import com.hc.kugou.solr.MusicSolr;
import org.apache.solr.client.solrj.SolrClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KugouApplicationTests {

    @Autowired
    private IndexService indexService;

    @Autowired
    private MvMapper mvMapper;

    @Autowired
    private SingerInfoMapper singerInfoMapper;

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
    }

    @Autowired
    private SolrClient client;

    @Autowired
    private MusicSolr musicSolr;

    /**
     * 得到UUID
     * @return
     */
    private static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }


    @Test
    public void updateMusicSongName(){
    }

    @Test
    public void fun2(){
    }


    @Test
    public void test02(){
//        List arr = new ArrayList();
//        Map<String,String> map = new HashMap<String, String>();
//        int count = 0;
//        for(int i = 0; i < 1000; i++){
//            String userAccount = Code.createUserAccount();
//            map.put(userAccount,"");
////            if(arr.contains(userAccount)){
////                count++;
////            }
////            arr.add(userAccount);
//        }
//
//        System.out.println(map.size());
    }

//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Test
//    public void test06(){
//        //切换到1库
//        MyRedis.select(stringRedisTemplate,1);
//        stringRedisTemplate.opsForValue().set("k1","v1");
//
//        //切换到0库
//        MyRedis.select(stringRedisTemplate,0);
//        stringRedisTemplate.opsForValue().set("k0","v0");
//
//    }

}
