package com.example.redismessagingpublisher.config;

import com.example.redismessagingpublisher.publishers.Publisher;
import com.example.redismessagingpublisher.publishers.RedisPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
@RequiredArgsConstructor
public class RedisConfig {

    private final String CHANNEL = "stream:cliente";

    private final RedisConnectionFactory connectionFactory;

    @Bean
    RedisTemplate<String,Object> redisTemplate(){
        final RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        return redisTemplate;
    }

    @Bean
    public ChannelTopic topic(){
        return new ChannelTopic(CHANNEL);
    }
}
