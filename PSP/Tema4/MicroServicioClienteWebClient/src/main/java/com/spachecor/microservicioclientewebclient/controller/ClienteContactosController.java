package com.spachecor.microservicioclientewebclient.controller;

import com.spachecor.microservicioclientewebclient.model.Persona;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ClienteContactosController {
    @Value("${app.user}")
    String user;
    @Value("${app.pass}")
    String pass;
    // Base URL del servicio remoto
    private final String urlBase = "http://localhost:4444/contactos";

    // Cliente WebClient inyectado
    private final WebClient webClient;

    // Se inyecta el WebClient.Builder para construir un WebClient configurado con la URL base
    public ClienteContactosController(WebClient.Builder webClientBuilder) {
        //this.webClient = webClientBuilder.baseUrl(urlBase).build();
        this.webClient = WebClient.builder()
                .baseUrl(urlBase)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @GetMapping(value="/personas/{nombre}/{email}/{edad}", produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> altaNueva(@PathVariable("nombre") String nombre,
                                   @PathVariable("email") String email,
                                   @PathVariable("edad") int edad) {
        Persona persona = new Persona(nombre, email, edad);

        // Llamada para dar de alta a una nueva persona
        webClient.post()
                .uri("")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(persona)
                //.header("Authorization", "Basic " + getBase64(user,pass))
                .retrieve()
                .bodyToMono(Void.class)
                .block();

        // Llamada para obtener todas las personas existentes
        Persona[] personas = webClient.get()
                .uri("")
                //.header("Authorization", "Basic " + getBase64(user,pass))
                .retrieve()
                .bodyToMono(Persona[].class)
                .block();

        return (personas != null) ? Arrays.asList(personas) : Collections.emptyList();
    }

    @GetMapping(value="/personas/{edad1}/{edad2}", produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> buscarEdades(@PathVariable("edad1") int edad1, @PathVariable("edad2") int edad2) {

        // Recupera todas las personas del servicio remoto
        Persona[] personas = webClient.get()
                .uri("")
                .retrieve()
                .bodyToMono(Persona[].class)
                .block();

        // Filtra las personas por el rango de edades especificado
        return Arrays.stream(personas)
                .filter(p -> p.getEdad() >= edad1 && p.getEdad() <= edad2)
                .collect(Collectors.toList());
    }
    private String getBase64(String user, String pass) {
        String cad=user+":"+pass;
        return Base64.getEncoder().encodeToString(cad.getBytes());
    }
}
