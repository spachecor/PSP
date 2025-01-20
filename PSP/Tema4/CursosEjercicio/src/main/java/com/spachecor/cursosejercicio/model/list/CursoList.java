package com.spachecor.cursosejercicio.model.list;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.spachecor.cursosejercicio.model.entity.Curso;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "Cursos")
public class CursoList {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Curso")
    private List<Curso> cursos;

    public CursoList() {
        cursos = new ArrayList<>();
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
}
