package com.spacrod.ejercicios.ejerciciouno;

public class CorredorHilo implements Runnable{
    private String nombre;
    private Integer progreso;

    public CorredorHilo(String nombre) {
        this.nombre = nombre;
        this.progreso = Math.toIntExact(Math.round(Math.random()*(10-1)+1));
    }

    @Override
    public void run() {
        //bucle for con la distancia de la carrera
        for(int i = 0; i <= 100; i++){
            System.out.println("Corredor "+getNombre()+" por el metro: " + i);
            try {
                Thread.sleep(getProgreso());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(this.getNombre()+" ha llegado a la meta");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getProgreso() {
        return progreso;
    }

    public void setProgreso(Integer progreso) {
        this.progreso = progreso;
    }
}
