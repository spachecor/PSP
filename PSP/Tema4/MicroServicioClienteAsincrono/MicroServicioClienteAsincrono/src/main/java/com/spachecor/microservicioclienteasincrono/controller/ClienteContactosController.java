package com.spachecor.microservicioclienteasincrono.controller;

import com.spachecor.microservicioclienteasincrono.model.Persona;
import com.spachecor.microservicioclienteasincrono.service.AccesoService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class ClienteContactosController {
    private final AccesoService accesoService;

    // Inyección de dependencias con Spring
    public ClienteContactosController(AccesoService accesoService) {
        this.accesoService = accesoService;
    }

    @GetMapping(value="/personas/{nombre}/{email}/{edad}", produces= MediaType.APPLICATION_JSON_VALUE)
    public CompletableFuture<List<Persona>> altaNueva(@PathVariable("nombre") String nombre,
                                                      @PathVariable("email") String email,
                                                      @PathVariable("edad") int edad) {

        Persona persona = new Persona(nombre, email, edad);
        CompletableFuture<List<Persona>> personaCompletableFuture = accesoService.llamadaServicio(persona);

        // Ejecutar el bucle de espera en un hilo separado
        CompletableFuture.runAsync(() -> {
            for (int i = 1; i <= 50; i++) {
                System.out.println("esperando " + i);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Procesar la respuesta cuando esté lista (de manera no bloqueante)
        personaCompletableFuture.thenAccept(personas -> {
            System.out.println("listo");
        });

        return personaCompletableFuture;
    }
}
