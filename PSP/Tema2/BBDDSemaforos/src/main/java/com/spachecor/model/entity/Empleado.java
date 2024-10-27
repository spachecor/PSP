package com.spachecor.model.entity;

/**
 * Clase Empleado que define la entidad Empleado
 * @author Selene
 * @version 1.0
 */
public class Empleado {
    private Integer id;
    private String nombre;

    public Empleado() {}

    public Empleado(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Empleado [id=" + id + ", nombre=" + nombre + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
