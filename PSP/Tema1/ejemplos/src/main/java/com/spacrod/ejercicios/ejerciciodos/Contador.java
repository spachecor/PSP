package com.spacrod.ejercicios.ejerciciodos;

public class Contador {
    private Integer cuenta;

    public Contador() {
        this.cuenta = 0;
    }

    public synchronized void incrementar() {
        this.cuenta++;
    }

    public Integer getCuenta() {
        return this.cuenta;
    }

    public void setCuenta(Integer cuenta) {
        this.cuenta = cuenta;
    }
}
