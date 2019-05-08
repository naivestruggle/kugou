package com.hc.kugou;

import com.hc.kugou.bean.*;
import com.hc.kugou.solr.SolrBean;
import com.hc.kugou.solr.SolrManager;
import com.hc.kugou.mapper.MusicMapper;
import com.hc.kugou.mapper.MvMapper;
import com.hc.kugou.mapper.SingerInfoMapper;
import com.hc.kugou.mapper.SingerMapper;
import com.hc.kugou.service.IndexService;
import com.hc.kugou.solr.MusicSolr;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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



    @Autowired
    private SolrClient client;

    //导入music
    @Test
    public void testSolrAddMusic()throws Exception{
        //78142
//        List<Music> list = musicMapper.selectCopy(70000,8142);
//        for (Music music:list){
//            SolrInputDocument doc = new SolrInputDocument();
//            doc.setField("id",music.getId());
//            doc.setField("author_id",music.getAudioId());
//            doc.setField("author_name",music.getAudioName());
//            doc.setField("audio_id",music.getAudioId());
//            doc.setField("audio_name",music.getAudioName());
//            doc.setField("song_name",music.getSongName());
//            doc.setField("hash_code",music.getHashCode());
//            doc.setField("filesize",music.getFilesize());
//            doc.setField("timelength",music.getTimelength());
//            doc.setField("have_album",music.getHaveAlbum());
//            doc.setField("album_id",music.getAlbumId());
//            doc.setField("album_name",music.getAlbumName());
//            doc.setField("have_mv",music.getHaveMv());
//            doc.setField("video_id",music.getVideoId());
//            doc.setField("privilege",music.getPrivilege());
//            doc.setField("privilege2",music.getPrivilege2());
//            doc.setField("play_url",music.getPlayUrl());
//            doc.setField("img",music.getImg());
//            doc.setField("lyrics",music.getLyrics());
//            doc.setField("listener_count",music.getListenerCount());
//            doc.setField("class_name",music.getClassName());
//            client.add(doc);
//        }
//        client.commit();
    }

    @Test
    public void testSolrDeleteMusic()throws Exception{
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("7");
        client.deleteById(list);
        client.commit();
    }


    //查询
    @Test
    public void testSolrQuery()throws Exception{
        Long startTime = System.currentTimeMillis();

        //查询
        SolrQuery solrQuery = new SolrQuery();
        //关键词
        solrQuery.setQuery("出山");
        //排序
        solrQuery.addSort("music_listener_count", SolrQuery.ORDER.desc);
        //分页
        solrQuery.setStart(0);
        solrQuery.setRows(100);
        //默认域
        solrQuery.set("df","music_audio_name");
        //只查询指定域
        solrQuery.set("fl","id,audio_name");
        //高亮
        //打开开关
        solrQuery.setHighlight(true);
        //指定高亮域
        solrQuery.addHighlightField("audio_name");
        //前缀
        solrQuery.setHighlightSimplePre("<font style='color:red;'>");
        //后缀
        solrQuery.setHighlightSimplePost("</font>");

        //执行查询
        QueryResponse response = client.query(solrQuery);
        //文档结果集
        SolrDocumentList docs = response.getResults();

        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        //Map K id  :  V Map
        //Map K 域名 ：V List
        //List  List.get(0)
        Long numFound = docs.getNumFound();
        Long endTime = System.currentTimeMillis();
        Long times = endTime - startTime;
        System.out.println("查到了："+numFound+",用时："+times+" ms");
        for(SolrDocument doc:docs){
            Map<String, List<String>> map = highlighting.get(doc.get("id"));
            List<String> list = map.get("audio_name");
            for(String s : list){
                System.out.println(s);
            }
            System.out.println("===============================");

        }
    }

    //添加词汇
    @Test
    public void testAddFengce(){
        FileWriter fileWriter = null;
        for (int num=0;num<8;num++){
            long start = num*10000;
            long end = (num+1)*10000;
            if(num == 7){
                end = end - 1858;
            }
            try{
                fileWriter = new FileWriter(new File("F:/myFengci.properties"));
                Properties properties = new Properties();
                //78142
                for(long i=start;i<end;i++){
                    if(i == 0)
                        i = 1;
                    Music music = musicMapper.selectMusicById(i);
                    String audio_name = music.getMusicAudioName().trim();
                    String[] arr = audio_name.split("-");
                    if(arr.length == 2){
                        properties.put(arr[1].trim().replace("\\",""),"");  //歌名
                        String[] arr2 = arr[0].split("、");  //多个歌手
                        for (String s:arr2){
                            properties.put(s.trim(),"");
                        }
                    }
                    System.out.println("当前歌曲:"+music.getMusicId());
                }
                properties.store(fileWriter,"");
            }catch (Exception e){

            }finally {
                if(fileWriter != null){
                    try {
                        fileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


    }

    @Test
    public void testUpdate()throws Exception{
        SolrManager<Music> solrManager = SolrManager.getInstance(Music.class,client);

        //11297  毛不易消愁
        Music music = musicMapper.selectMusicById(11297L);
//        client.deleteById("11297");
//        client.commit();
        //solrManager.add(music);
        solrManager.update("11297",music);
//        solrManager.delete("11297");
//        SolrInputDocument doc = new SolrInputDocument();
//        doc.setField("id",music.getId());
//        doc.setField("author_id",music.getAuthorId());
//        doc.setField("author_name",music.getAudioName());
//        doc.setField("audio_id",music.getAudioId());
//        doc.setField("audio_name",music.getAudioName());
//        doc.setField("song_name",music.getSongName());
//        doc.setField("hash_code",music.getHashCode());
//        doc.setField("filesize",music.getFilesize());
//        doc.setField("timelength",music.getTimelength());
//        doc.setField("have_album",music.getHaveAlbum());
//        doc.setField("album_id",music.getAlbumId());
//        doc.setField("album_name",music.getAlbumName());
//        doc.setField("have_mv",music.getHaveMv());
//        doc.setField("video_id",music.getVideoId());
//        doc.setField("privilege",music.getPrivilege());
//        doc.setField("privilege2",music.getPrivilege2());
//        doc.setField("play_url",music.getPlayUrl());
//        doc.setField("img",music.getImg());
//        doc.setField("lyrics",music.getLyrics());
//        doc.setField("listener_count",music.getListenerCount());
//        doc.setField("class_name",music.getClassName());
//        client.add(doc);
//        client.commit();
    }


    @Test
    public void fun()throws Exception{
        SolrManager solrManager = SolrManager.getInstance(Music.class,client);

        String[] pointFields = {
                "id",
                "author_id",
                "author_name",
                "audio_id",
                "audio_name",
                "song_name",
                "hash_code"          ,
                "filesize"           ,
                "timelength"         ,
                "have_album"         ,
                "album_id"           ,
                "album_name"         ,
                "have_mv"            ,
                "video_id"           ,
                "privilege"          ,
                "privilege2"         ,
                "play_url"           ,
                "img"                ,
                "lyrics"             ,
                "listener_count"     ,
                "class_name"

        };
        SolrBean<Music> musicList = solrManager.find(
                 "消愁"
                ,new String[]{"audio_name:毛不易"}
                ,"listener_count"
                ,SolrManager.SORT_RULE_DESC
                ,0
                ,1
                ,new String[]{"audio_name"}
                ,pointFields
                ,"audio_name"
        );
        System.out.println(musicList);
    }


    @Test
    public void testAddMv(){
        SolrManager<Music> solrManager = SolrManager.getInstance(Music.class,client);
        for(int i=0;i<8;i++){
            int start = i*10000;
            int end = (i+1)*10000;
            if(i == 7){
                end = end - 1858;
            }
            if(start == 0){
                start = 1;
            }
            List<Music> musicList = musicMapper.selectCopy(start,end);
           // solrManager.add(musicList);
        }

//        solrManager.delete("");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void fun11(){
        SolrManager<Mv> solrManager = SolrManager.getInstance(Mv.class,client);
        for(int i=0;i<2;i++){
            int start = i*10000;
            int end = (i+1)*10000;
            if(i == 1){
                end = end - 1652;
            }
            if(start == 0){
                start = 1;
            }
            List<Mv> mvList = mvMapper.selectCopy(start,end);
         //   solrManager.add(mvList);
        }

        SolrManager<Singer> singerSolrManager = SolrManager.getInstance(Singer.class,client);
        for(int i=0;i<2;i++){
            int start = i*10000;
            int end = (i+1)*10000;
            if(i == 1){
                end = end - 4696;
            }
            if(start == 0){
                start = 1;
            }
            List<Singer> singerList = singerMapper.selectCopy(start,end);
        //    singerSolrManager.add(singerList);
        }



        SolrManager<SingerInfo> singerInfoSolrManager = SolrManager.getInstance(SingerInfo.class,client);
        for(int i=0;i<2;i++){
            int start = i*10000;
            int end = (i+1)*10000;
            if(i == 1){
                end = end - 4568;
            }
            if(start == 0){
                start = 1;
            }
            List<SingerInfo> singerInfoList = singerInfoMapper.selectCopy(start,end);
          //  singerInfoSolrManager.add(singerInfoList);
        }
    }

    @Autowired
    private MusicSolr musicSolr;


    @Test
    public void testMusicSolr1(){
        SolrBean<Music> solrBean = musicSolr.selectNewMusicByClassName("华语",24);
        System.out.println("查到结果："+solrBean.getFoundNum());
        System.out.println("高亮字段："+solrBean.getHighlight());
        System.out.println("-----------------------------------");;
        Map<String,Music> map = solrBean.getSolrBeanMap();
        for(Map.Entry<String,Music> me:map.entrySet()){
            System.out.println("id："+me.getKey());
            System.out.println(me.getValue());
        }
    }



}
