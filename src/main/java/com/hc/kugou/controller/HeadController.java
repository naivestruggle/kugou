package com.hc.kugou.controller;

import com.alibaba.fastjson.JSONObject;
import com.hc.kugou.bean.Music;
import com.hc.kugou.bean.Mv;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author:杨鑫虎
 * @Date:2019/4/30
 * @Description:com.hc.kugou.controller
 * @Version:1.0
 */
@Controller
public class HeadController {

    @Autowired
    SolrClient solrClient;

    JSONObject jsonObject = new JSONObject();

    @ResponseBody
    @PostMapping("/queryMusic")
    public String queryMusic(String word) throws IOException, SolrServerException {
        List<Music> musicList = new ArrayList<Music>();

        SolrQuery solrQuery = new SolrQuery();
        //关键词
        solrQuery.setQuery(word);
        //默认查询域
        solrQuery.set("df", "music_keywords");
        //过滤条件
        //solrQuery.set("fq", "item_bus_no:100");
        //分页
        solrQuery.setStart(0);
        solrQuery.setRows(4);
        //查询指定域
        //solrQuery.set("fl", "item_bus_busline,item_bus_runtime,id");
        //高亮
        //开启高亮开关
        solrQuery.setHighlight(true);
        //指定高亮域
        solrQuery.addHighlightField("music_author_name,music_song_name");
        //前缀
        solrQuery.setHighlightSimplePre("<span style=\"color:red\">");
        //后缀
        solrQuery.setHighlightSimplePost("</span>");

        //执行查询
        QueryResponse queryResponse = solrClient.query(solrQuery);

        SolrDocumentList docs = queryResponse.getResults();
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
        //总条数
        //System.out.println("总条数：" + docs.getNumFound());

        for (SolrDocument solrDocument : docs) {
            Music music = new Music();

            Map<String, List<String>> map = highlighting.get(solrDocument.get("id"));
            List<String> list = map.get("music_author_name");
            if(list == null){
                list = map.get("music_song_name");
                //将作品名高亮
                music.setMusicSongName(list.get(0));
                music.setMusicAuthorName((String) solrDocument.get("music_author_name"));
            }else{
                //将作者名高亮
                music.setMusicAuthorName(list.get(0));
                music.setMusicSongName((String) solrDocument.get("music_song_name"));
            }
            music.setMusicId(Integer.parseInt(solrDocument.get("id").toString()));
            music.setMusicAuthorId(Long.parseLong(solrDocument.get("music_author_id").toString()));
            music.setMusicAudioId(Long.parseLong(solrDocument.get("music_audio_id").toString()));
            music.setMusicAudioName((String) solrDocument.get("music_audio_name"));

            music.setMusicHashCode((String) solrDocument.get("music_hash_code"));
            music.setMusicFilesize(Long.parseLong(solrDocument.get("music_filesize").toString()));
            music.setMusicTimelength(Long.parseLong(solrDocument.get("music_timelength").toString()));
            //Integer.parseInt(solrDocument.get("music_privilege2").toString())
            //Long.parseLong(solrDocument.get("music_listener_count").toString())
            music.setMusicHaveAlbum(Integer.parseInt(solrDocument.get("music_have_album").toString()));
            music.setMusicAlbumId(Long.parseLong(solrDocument.get("music_album_id").toString()));
            music.setMusicAlbumName((String) solrDocument.get("music_album_name"));
            music.setMusicHaveMv(Integer.parseInt(solrDocument.get("music_have_mv").toString()));
            music.setMusicVideoId(Integer.parseInt(solrDocument.get("music_video_id").toString()));
            music.setMusicPrivilege(Integer.parseInt(solrDocument.get("music_privilege").toString()));
            music.setMusicPrivilege2(Integer.parseInt(solrDocument.get("music_privilege2").toString()));
            music.setMusicPlayUrl((String) solrDocument.get("music_play_url"));
            music.setMusicImg((String) solrDocument.get("music_img"));
            music.setMusicLyrics((String) solrDocument.get("music_lyrics"));
            music.setMusicListenerCount(Long.parseLong(solrDocument.get("music_listener_count").toString()));

            musicList.add(music);
        }

        jsonObject.put("musicList", musicList);
        return jsonObject.toString();
    }

    @ResponseBody
    @PostMapping("/queryMv")
    public String queryMv(String word) throws IOException, SolrServerException {
        List<Mv> mvList = new ArrayList<Mv>();

        SolrQuery solrQuery = new SolrQuery();
        //关键词
        solrQuery.setQuery(word);
        //默认查询域
        solrQuery.set("df", "mv_name");
        //分页
        solrQuery.setStart(0);
        solrQuery.setRows(4);
        //高亮
        //开启高亮开关
        solrQuery.setHighlight(true);
        //指定高亮域
        solrQuery.addHighlightField("mv_name");
        //前缀
        solrQuery.setHighlightSimplePre("<span style=\"color:red\">");
        //后缀
        solrQuery.setHighlightSimplePost("</span>");

        //执行查询
        QueryResponse queryResponse = solrClient.query(solrQuery);

        SolrDocumentList docs = queryResponse.getResults();
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
        //总条数
        //System.out.println("总条数：" + docs.getNumFound());

        for (SolrDocument solrDocument : docs) {
            Mv mv = new Mv();

            Map<String, List<String>> map = highlighting.get(solrDocument.get("id"));
            List<String> list = map.get("mv_name");
            if(list != null){
                //将MV名高亮
                mv.setMvName(list.get(0));
            }else{
                mv.setMvName((String) solrDocument.get("mv_name"));
            }

            mv.setMvId(Long.parseLong(solrDocument.get("id").toString()));
            mv.setMvHash((String) solrDocument.get("mv_hash"));
            mv.setMvHcUrl((String) solrDocument.get("hc_url"));
            mv.setMvHdUrl((String) solrDocument.get("hd_url"));
            mv.setMvBdUrl((String) solrDocument.get("bd_url"));
            mv.setMvHeadImage((String) solrDocument.get("head_image"));
            //mv.setUpdateTime((Date) solrDocument.get("update_time"));
            mv.setMvClassName((String) solrDocument.get("class_name"));

            mvList.add(mv);
        }

        jsonObject.put("mvList", mvList);
        return jsonObject.toString();
    }

    /**
     * solr查询
     * @param keyWord           查询关键字
     * @param defaultFiled      默认域
     * @param clazz             封装的对象
     * @param start             分页起始
     * @param end
     * @param <T>
     * @return
     * @throws IOException
     * @throws SolrServerException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     */
    public <T> List<T> solr(String keyWord,String defaultFiled,Class<T> clazz,int start,int end) throws IOException, SolrServerException, IllegalAccessException, InstantiationException, InvocationTargetException {
        List<T> list = new ArrayList<T>();

        SolrQuery solrQuery = new SolrQuery();
        //关键词
        solrQuery.setQuery(keyWord);
        //默认查询域
        solrQuery.set("df", defaultFiled);
        //分页
        solrQuery.setStart(start);
        solrQuery.setRows(end);

        //执行查询
        QueryResponse queryResponse = solrClient.query(solrQuery);
        SolrDocumentList docs = queryResponse.getResults();

        //总条数
        //System.out.println("总条数：" + docs.getNumFound());

        for (SolrDocument solrDocument : docs) {
            T t = clazz.newInstance();
            //得到方法名
            Method[] methods = clazz.getMethods();
            for(Method m : methods){
                if(m.getName().startsWith("set")){
                    //have_mv
                    String substring = m.getName().substring(3);
                    //HcUrl --> hc_url
                    for(int i = 1; i < substring.length(); i++){

                        char c = substring.charAt(i);

                        if(c >= 65 && c <= 90){
                            substring = substring.replace(c+"","_" + (char)(c+32));
                        }
                    }
                    substring = substring.toLowerCase();
                    //System.out.println(substring);
                    //得到set方法传入参数的类型
                    Class<?>[] types = m.getParameterTypes();
                    String ParameterType = null;
                    if(types != null){
                        for(Class<?> type : types){
                            ParameterType = type.getTypeName();
                        }
                    }
                    if("java.lang.String".equals(ParameterType)){
                        m.invoke(t,(String) solrDocument.get(substring));
                    }else if("java.lang.Integer".equals(ParameterType)){
                        m.invoke(t,Integer.parseInt(solrDocument.get(substring).toString()));
                    }else if("java.lang.Long".equals(ParameterType)){
                        m.invoke(t,Long.parseLong(solrDocument.get(substring).toString()));
                    }
                }
            }
            list.add(t);
        }
        return list;
    }

}
