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
                registry.addViewController("/anchor").setViewName("anchor");
            }
        };
        return webMvcConfigurer;
    }
}
