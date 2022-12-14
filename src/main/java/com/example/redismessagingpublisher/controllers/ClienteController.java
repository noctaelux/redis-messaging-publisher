package com.example.redismessagingpublisher.controllers;

import com.example.redismessagingpublisher.models.Cliente;
import com.example.redismessagingpublisher.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ClienteController {

    private final ClienteService clienteService;

    @RequestMapping(value = "/nuevo/cliente", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String nuevoCliente(@RequestBody Cliente cliente){
        clienteService.streamNuevoCliente(cliente);
        return "OK";
    }

}
