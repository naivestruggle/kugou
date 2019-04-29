package com.hc.kugou.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    @Bean
    public WebMvcConfigurer dispatcherHandler(){
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer(){
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("index");
                registry.addViewController("/anchor.html").setViewName("anchor");
                registry.addViewController("/index.html").setViewName("index");
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
            }
        };
        return webMvcConfigurer;
    }
}
