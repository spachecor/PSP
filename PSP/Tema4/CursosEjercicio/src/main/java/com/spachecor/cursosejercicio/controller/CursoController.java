package com.spachecor.cursosejercicio.controller;

import com.spachecor.cursosejercicio.model.Curso;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CursoController {
    @GetMapping(value = "curso", produces = MediaType.TEXT_PLAIN_VALUE)
    public String curso(@RequestParam String nombre, @RequestParam Integer duracion, @RequestParam String horario) {
        Curso curso = new Curso(nombre, duracion, horario);
        return "Curso: " + curso;
    }
}
