package com.spacrod.ejerciciosemaforo;

import java.util.concurrent.Semaphore;

public class Empleado implements Runnable{
    private Semaphore impresora;
    private String nombre;
    public Empleado(Semaphore impresora, String nombre) {
        this.impresora = impresora;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        try{
            System.out.println("El empleado " + this.getNombre() + " está esperando para usar la impresora.");
            this.getImpresora().acquire();
            System.out.println("El empleado " + this.getNombre() + " está usando la impresora.");
            Thread.sleep(2000);
            System.out.println("El empleado " + this.getNombre() + " ha terminado de usar la impresora.");
            this.getImpresora().release();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Semaphore getImpresora() {
        return impresora;
    }

    public void setImpresora(Semaphore impresora) {
        this.impresora = impresora;
    }
}
