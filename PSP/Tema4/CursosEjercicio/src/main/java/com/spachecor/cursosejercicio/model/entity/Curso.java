package com.spachecor.cursosejercicio.model.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.Objects;

@JacksonXmlRootElement(localName = "Curso")
public class Curso {
    @JacksonXmlProperty(localName = "Nombre")
    private String nombre;
    @JacksonXmlProperty(localName = "Duracion")
    private Integer duracion;
    @JacksonXmlProperty(localName = "Horario")
    private String horario;

    public Curso() {}

    public Curso(String nombre, Integer duracion, String horario) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.horario = horario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return Objects.equals(nombre, curso.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "nombre='" + nombre + '\'' +
                ", duracion=" + duracion +
                ", horario='" + horario + '\'' +
                '}';
    }
}
