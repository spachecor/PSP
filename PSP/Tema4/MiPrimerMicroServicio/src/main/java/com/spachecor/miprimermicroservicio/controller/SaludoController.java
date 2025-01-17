package com.spachecor.miprimermicroservicio.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//Anotacion Spring MVC que indica que esta clase es un controlador web
@RestController
public class SaludoController {
    //Anotacion que asocia este metodo a una peticion GET para clientes que admitan texto plano como respuesta
    @GetMapping(value = "saludo", produces = MediaType.TEXT_PLAIN_VALUE)
    public String saludo(@RequestParam String valorX, @RequestParam String valorY) {
        return "Pa qué me dices "+valorX+" y "+valorY+", vaya kk.";
    }
    @GetMapping(value = "saludo/{nombre}/{apellidos}/{email}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String saludo(@PathVariable String nombre, @PathVariable String apellidos, @PathVariable String email) {
        return "Venga, "+nombre+" "+apellidos+", te saludo. Tu email es: "+email+".";
    }
}