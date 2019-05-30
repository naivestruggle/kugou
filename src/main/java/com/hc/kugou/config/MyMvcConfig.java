package com.hc.kugou.config;

import com.hc.commons.MailUtils;
import com.hc.commons.PythonUtils;
import com.hc.kugou.component.CrossDomainHandlerInterceptor;
import com.hc.kugou.solr.MusicSolr;
import com.hc.kugou.solr.MvSolr;
import com.hc.kugou.solr.SingerSolr;
import com.hc.kugou.solr.MusicListSolr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author:杨鑫虎
 * @Date:2019/4/29
 * @Description:com.hc.kugou.config
 * @Version:1.0
 */
@Configuration
public class MyMvcConfig{

    @Value("${verify.real.path}")
    private String verifyPath;

    @Value("${headImage.real.path}")
    private String headImagePath;

    @Bean
    public WebMvcConfigurer dispatcherHandler(){
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer(){
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
//                registry.addViewController("/").setViewName("index");
                registry.addViewController("/anchor.html").setViewName("anchor");
//                registry.addViewController("/index.html").setViewName("index");
                registry.addViewController("/download.html").setViewName("download");
                registry.addViewController("/friendLink.html").setViewName("friendLink");
                registry.addViewController("/hello.html").setViewName("hello");
                registry.addViewController("/list.html").setViewName("list");
                registry.addViewController("/live.html").setViewName("live");
                registry.addViewController("/LiveMusic.html").setViewName("LiveMusic");
                registry.addViewController("/mall.html").setViewName("mall");
                registry.addViewController("/musicMan.html").setViewName("musicMan");
                registry.addViewController("/mv.html").setViewName("mv");
                registry.addViewController("/mvPlay.html").setViewName("mvPlay");
                registry.addViewController("/playsong.html").setViewName("playsong");
                registry.addViewController("/radio.html").setViewName("radio");
                registry.addViewController("/recruit_sages.html").setViewName("recruit_sages");
                registry.addViewController("/register.html").setViewName("register");
                registry.addViewController("/serve.html").setViewName("serve");
                registry.addViewController("/singer.html").setViewName("singer");
                registry.addViewController("/songsheet.html").setViewName("songsheet");
                registry.addViewController("/txsinger.html").setViewName("txsinger");
                registry.addViewController("/vip.html").setViewName("vip");
                registry.addViewController("/queryMusic.html").setViewName("queryMusic");
                registry.addViewController("/queryMv.html").setViewName("queryMv");
                registry.addViewController("/musicTest.html").setViewName("musicTest");
                registry.addViewController("/specialColumn.html").setViewName("specialColumn");
                registry.addViewController("/songsheetInfo.html").setViewName("songsheetInfo");
                registry.addViewController("/search.html").setViewName("search.html");
                registry.addViewController("/songsheetlist.html").setViewName("songsheetlist");
                registry.addViewController("/test01.html").setViewName("test01");
                registry.addViewController("/my_songsheet.html").setViewName("my_songsheet");
                registry.addViewController("/houtai.html").setViewName("houtai");
            }

            /**
             * @param registry
             */
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/verifyImage/**").addResourceLocations(verifyPath);
                registry.addResourceHandler("/userHeadImage/**").addResourceLocations(headImagePath);
            }
        };
        return webMvcConfigurer;
    }

    /**
     * 拦截器
     * @return
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new CrossDomainHandlerInterceptor())
                        .addPathPatterns("/**");
            }
        };
    }

    @Bean
    public MusicSolr musicSolr(){
        return new MusicSolr();
    }

    @Bean
    public MvSolr mvSolr(){
        return new MvSolr();
    }

    @Bean
    public SingerSolr singerSolr(){
        return new SingerSolr();
    }

    @Bean
    public MusicListSolr musicListSolr(){
        return new MusicListSolr();
    }

    @Bean
    public PythonUtils pythonUtils(){return new PythonUtils();}
    /**
     * 注入邮件发送工具
     * @return  邮件发送工具对象
     */
    @Bean
    public MailUtils mailUtils(){
        return new MailUtils();
    }
}
