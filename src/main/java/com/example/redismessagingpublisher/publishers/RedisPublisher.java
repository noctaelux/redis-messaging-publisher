package com.example.redismessagingpublisher.publishers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

public class RedisPublisher implements Publisher{

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private ChannelTopic channelTopic;

    public RedisPublisher(RedisTemplate<String,Object> redisTemplate,ChannelTopic channelTopic){
        this.redisTemplate = redisTemplate;
        this.channelTopic = channelTopic;
    }

    @Override
    public void publish(Object message) {
        redisTemplate.convertAndSend(channelTopic.getTopic(),message);
    }
}
