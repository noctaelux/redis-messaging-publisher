package com.example.redismessagingpublisher.services;

import com.example.redismessagingpublisher.models.Cliente;
import com.example.redismessagingpublisher.publishers.RedisPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final RedisPublisher redisPublisher;

    @Override
    public void streamNuevoCliente(Cliente cliente){
        redisPublisher.publish(cliente);
    }
}
