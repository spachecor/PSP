package com.spachecor.microservicioclienteasincrono.service;

import com.spachecor.microservicioclienteasincrono.model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AccesoServiceImpl implements AccesoService {
    private String url="http://localhost:4444";
    private final RestTemplate restTemplate;

    // Constructor para inyección de dependencias
    public AccesoServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    @Async
    public CompletableFuture<List<Persona>> llamadaServicio(Persona persona) {
        restTemplate.postForLocation(url + "/contactos/contacto", persona);
        Persona[] personas = restTemplate.getForObject(url + "/contactos", Persona[].class);
        return CompletableFuture.completedFuture(Arrays.asList(personas));
    }
}