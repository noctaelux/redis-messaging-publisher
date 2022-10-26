package com.example.redismessagingpublisher.services;

import com.example.redismessagingpublisher.models.Cliente;
import com.example.redismessagingpublisher.publishers.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    Publisher publisher;

    public void streamNuevoCliente(Cliente cliente){
        publisher.publish(cliente);
    }
}
