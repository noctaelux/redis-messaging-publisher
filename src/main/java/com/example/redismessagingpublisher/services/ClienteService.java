package com.example.redismessagingpublisher.services;

import com.example.redismessagingpublisher.models.Cliente;

public interface ClienteService {

    void streamNuevoCliente(Cliente cliente);
}
