package com.hc.kugou;

import com.hc.commons.Code;
import com.hc.commons.MailUtils;
import com.hc.commons.PythonUtils;
import com.hc.commons.StringUtils;
import com.hc.kugou.bean.Music;
import com.hc.kugou.bean.Mv;
import com.hc.kugou.bean.custombean.CustomMusic;
import com.hc.kugou.mapper.*;
import com.hc.kugou.service.IndexService;
import com.hc.kugou.solr.MusicSolr;
import org.apache.commons.io.input.ReaderInputStream;
import org.apache.solr.client.solrj.SolrClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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


//    @Test
//    public void fun1(){
//
////        List<Music> musicList = musicMapper.selectAudioName(1,3);
////        System.out.println(musicList);
//        //创建线程池
//        Long startTime = System.currentTimeMillis();
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        System.out.println("****************************newCachedThreadPool*******************************");
//        for(int i=0;i<5;i++){
//            final int num = i;
//            executorService.execute(new Thread(){
//                @Override
//                public void run() {
//                    int begin = 1000;
//                    List<Mv> list = new ArrayList<Mv>();
//                    List<Music> musicList = musicMapper.selectAudioName(begin*num,begin);
//                    PythonUtils pythonUtils = new PythonUtils();
//                    int j = 1;
//                    for(Music music:musicList){
//                        Mv mv = pythonUtils.getMv(music.getMusicAudioName());
//                        if(mv == null){
//                            continue;
//                        }
//                        mv.setMvClassName(music.getMusicClassName());
//                        mv.setMvListenerCount(0l);
//                        list.add(mv);
//                        System.out.println(num+"成功.."+j+++".."+mv.getMvName());
//                    }
//                    mvMapper.insertList(list);
//                }
//            });
//        }
//        executorService.shutdown();
//        boolean flag = true;
//        while (flag){
//            if(executorService.isTerminated()){
//                Long endTime = System.currentTimeMillis();
//                Long time = endTime - startTime;
//                System.out.println("所用时间："+time);
//                flag = false;
//
//            }
//        }
//    }

    @Test
    public void fun3(){
        Long startTime = System.currentTimeMillis();
        int j = 0;
        int k = 0;
        for(int i=0;i<1000;i++){
            int num = 100;
            List<Mv> list = new ArrayList<Mv>();
            List<Music> musicList = musicMapper.selectAudioName(num*i,num);
            PythonUtils pythonUtils = new PythonUtils();
            for(Music music:musicList){
                if(music.getFlag() == 1){
                    continue;
                }
                Mv mv = pythonUtils.getMv(music.getMusicAudioName());
                if(mv == null){
                    k++;
                    System.out.println("没有mv..."+k);
                    continue;
                }
                mv.setMvClassName(music.getMusicClassName());
                mv.setMvListenerCount(0l);
                list.add(mv);
                j++;
                System.out.println("成功.."+j+".."+mv.getMvName());
            }
            if(list.size() > 0){
                musicMapper.updateBatchFlag(musicList);
                mvMapper.insertList(list);
            }
        }
        Long endTime = System.currentTimeMillis();
        Long time = endTime - startTime;
        System.out.println("所用时间："+time);
        System.out.println(j+"个成功");
        System.out.println(k+"个没有mv");
    }

}
