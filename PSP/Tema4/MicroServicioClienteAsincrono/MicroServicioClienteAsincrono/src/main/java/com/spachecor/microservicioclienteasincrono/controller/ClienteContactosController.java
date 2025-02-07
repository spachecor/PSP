package com.spachecor.microservicioclienteasincrono.controller;

import com.spachecor.microservicioclienteasincrono.model.Persona;
import com.spachecor.microservicioclienteasincrono.service.AccesoService;
import com.spachecor.microservicioclienteasincrono.service.AccesoServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
public class ClienteContactosController {
    String urlBase="http://localhost:4444";


    private final RestTemplate template;
    private final AccesoService accesoService;

    // Constructor para inyectar la dependencia de RestTemplate que se necesita para realizar las peticiones al servicio remoto de contactos
    public ClienteContactosController(RestTemplate template) {   //Para evitar el uso de @Autowired y hacer el objeto template inmutable
        this.template = template;
        this.accesoService = new AccesoServiceImpl(template);
    }

    @GetMapping(value="/personas/{nombre}/{email}/{edad}", produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> altaNueva(@PathVariable("nombre") String nombre,
                                   @PathVariable("email") String email,
                                   @PathVariable("edad") int edad) {

        Persona persona = new Persona(nombre,email,edad);
        CompletableFuture<List<Persona>> personaCompletableFuture = accesoService.llamadaServicio(persona);
        for(int i=1;i<50;i++) {
            System.out.println("esperando ");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            return personaCompletableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
