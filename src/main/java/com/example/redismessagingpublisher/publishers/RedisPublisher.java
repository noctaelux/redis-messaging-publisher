package com.example.redismessagingpublisher.publishers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisPublisher implements Publisher{

    private final RedisTemplate<String,Object> redisTemplate;

    private final ChannelTopic channelTopic;

    @Override
    public void publish(Object message) {
        redisTemplate.convertAndSend(channelTopic.getTopic(),message);
    }
}
