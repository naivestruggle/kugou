package com.hc.kugou.config;

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
    public RedisTemplate<Object, Object> objectRedisTemplate
    (RedisConnectionFactory redisConnectionFactory)throws UnknownHostException {

        RedisTemplate<Object,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Object> ser = new Jackson2JsonRedisSerializer<Object>(Object.class);
        redisTemplate.setDefaultSerializer(ser);
        return redisTemplate;
    }

}
