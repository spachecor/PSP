package com.spacrod.ejercicios.ejerciciodos;

public class HiloContador implements Runnable {
    private Contador contador;
    public HiloContador(Contador contador) {
        this.contador = contador;
    }
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            contador.incrementar();
        }
    }
}
