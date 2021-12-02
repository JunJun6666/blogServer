package com.wang.server.common.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String,Object> redisTemplate(LettuceConnectionFactory connectionFactory){
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        //string类型key序列器
        template.setKeySerializer(new StringRedisSerializer());
        //string类型value序列器
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        //hash类型key序列器
        template.setHashKeySerializer(new StringRedisSerializer());
        //hash类型value序列器
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}
