package com.example.redismessagingpublisher.config;

import com.example.redismessagingpublisher.publishers.Publisher;
import com.example.redismessagingpublisher.publishers.RedisPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

@Configuration
public class RedisConfig {

    @Autowired
    RedisConnectionFactory connectionFactory;

    @Bean
    RedisTemplate<String,Object> redisTemplate(){
        final RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public ChannelTopic topic(){
        return new ChannelTopic("stream:cliente");
    }

    @Bean
    Publisher redisPublisher() {
        return new RedisPublisher(redisTemplate(),topic());
    }
}
