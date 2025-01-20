package com.spachecor.cursosejercicio.controller;

import com.spachecor.cursosejercicio.model.entity.Curso;
import com.spachecor.cursosejercicio.model.list.CursoList;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class CursoController {
    private CursoList cursoList;
    public CursoController() {
        this.cursoList = new CursoList();
    }

    @GetMapping(value = "curso", produces = MediaType.APPLICATION_XML_VALUE)
    public Curso cursoGet(@RequestParam String nombre) {
        Curso cursoBuscado = new Curso(nombre, null, null);
        Curso curso = new Curso();
        for(Curso c : cursoList.getCursos()) {
            if(c.equals(cursoBuscado)) {
                curso = c;
            }
        }
        return curso;
    }
    @PostMapping(value = "curso", consumes = MediaType.APPLICATION_XML_VALUE)
    public void cursoPost(@RequestBody Curso curso) {
        this.cursoList.getCursos().add(curso);
    }
    @PutMapping(value = "curso", consumes = MediaType.APPLICATION_XML_VALUE)
    public void cursoPut(@RequestBody Curso curso) {
        for(Curso c : this.cursoList.getCursos()) {
            if(c.equals(curso)) {
                c.setDuracion(curso.getDuracion());
                c.setHorario(curso.getHorario());
            }
        }
    }
    @DeleteMapping(value = "curso", consumes = MediaType.APPLICATION_XML_VALUE)
    public void cursoDelete(@RequestBody Curso curso) {
        this.cursoList.getCursos().remove(curso);
    }
}
