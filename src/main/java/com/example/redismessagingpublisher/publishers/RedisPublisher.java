package com.example.redismessagingpublisher.publishers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisPublisher implements Publisher{

    private final Logger LOG = LoggerFactory.getLogger(RedisPublisher.class);

    private final RedisTemplate<String,Object> redisTemplate;

    private final ChannelTopic channelTopic;

    @Override
    public void publish(Object message) {
        redisTemplate.convertAndSend(channelTopic.getTopic(),message);
        LOG.info("TOPIC: {}", channelTopic.getTopic());
        LOG.info("MESSAGE: {}", message);
    }
}
