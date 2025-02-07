package com.spachecor.microservicioclienteasincrono.service;

import com.spachecor.microservicioclienteasincrono.model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AccesoServiceImpl implements AccesoService{
    private final RestTemplate restTemplate;
    public AccesoServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    String url="http://localhost:4444";
    @Override
    @Async
    public CompletableFuture<List<Persona>> llamadaServicio(Persona persona) {
        restTemplate.postForLocation(url+"/contactos/contacto", persona);
        Persona[] personas=restTemplate.getForObject(url+"/contactos", Persona[].class);
        return CompletableFuture.completedFuture(Arrays.asList(personas));
    }
}
