package com.spacrod.herencias;

public abstract class Persona extends Thread{
    private String nombre;
    private Integer edad;

    public Persona() {}
    public Persona(String nombre, Integer edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    protected void ticarEntrada(){
        System.out.println(this.getNombre()+" ha realizado el chech IN");
    }

    protected void ticarSalida(){
        System.out.println(this.getNombre()+" ha realizado el chech OUT");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }
}
