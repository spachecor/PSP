package com.spacrod;

public class MiHilo extends Thread{
    private String nombre;

    public MiHilo(String nombre) {
        this.nombre = nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void run(){
        for(int i=1; i<=5; i++){
            System.out.println(getNombre()+" Ejecutando: "+i);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(getNombre()+" Terminando");
    }
}
