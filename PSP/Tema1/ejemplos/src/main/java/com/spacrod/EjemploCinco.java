package com.spacrod;

public class EjemploCinco {
    public static void main(String[] args) {
        MiHilo miHilo1 = new MiHilo("Hilo nuevo");
        miHilo1.start();
        MiHilo miHilo2 = new MiHilo("Hilo nuevo2");
        miHilo2.start();
        MiHilo miHilo3 = new MiHilo("Hilo nuevo3");
        miHilo3.start();
    }
}
