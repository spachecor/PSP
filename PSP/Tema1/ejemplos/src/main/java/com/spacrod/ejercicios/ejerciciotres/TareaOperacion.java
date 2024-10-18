package com.spacrod.ejercicios.ejerciciotres;

public class TareaOperacion extends Thread{
    private Integer numero;
    private String operacion;

    public TareaOperacion(Integer numero, String operacion) {
        this.numero = numero;
        this.operacion = operacion;
    }

    @Override
    public void run() {
        switch(operacion){
            case "suma":
                System.out.println(Thread.currentThread().getName() + " está sumando: " + (numero+10));
                break;
            case "resta":
                System.out.println(Thread.currentThread().getName() + " está restando: " + (numero-10));
                break;
            case "multiplicacion":
                System.out.println(Thread.currentThread().getName() + " está multiplicando: " + (numero*10));
                break;
            case "division":
                System.out.println(Thread.currentThread().getName() + " está dividiendo: " + (numero/10));
                break;
        }
    }
}
