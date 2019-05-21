package com.hc.kugou.config;

import com.hc.kugou.bean.custombean.CustomMusicPlayList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

/**
 * @Author:
 * @Date:2019/4/30
 * @Description:com.hc.kugou.config
 * @Version:1.0
 */
@Configuration
public class MyRedisConfig {
    /**
     * 自定义Redis缓存模版 改变默认的序列化规则（序列化为json）
     */
    @Bean
    public RedisTemplate<Object, CustomMusicPlayList> objectRedisTemplate
    (RedisConnectionFactory redisConnectionFactory)throws UnknownHostException {

        RedisTemplate<Object,CustomMusicPlayList> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<CustomMusicPlayList> ser = new Jackson2JsonRedisSerializer<CustomMusicPlayList>(CustomMusicPlayList.class);
        redisTemplate.setDefaultSerializer(ser);
        return redisTemplate;
    }

}
