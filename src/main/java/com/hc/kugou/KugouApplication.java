package com.hc.kugou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan("com.hc.kugou.mapper")
@EnableCaching //开启基于注解的缓存
@SpringBootApplication
public class KugouApplication {

    public static void main(String[] args) {
        SpringApplication.run(KugouApplication.class, args);
    }

}
