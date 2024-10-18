package com.spacrod.ejercicios.ejerciciotres;

public class PiscinaTareas {
    public static void main(String[] args) {
        Thread suma = new TareaOperacion(5, "suma");
        Thread resta = new TareaOperacion(50, "resta");
        Thread multiplicacion = new TareaOperacion(5, "multiplicacion");
        Thread division = new TareaOperacion(10, "division");

        try{
            suma.start();
            suma.join();
            resta.start();
            resta.join();
            multiplicacion.start();
            multiplicacion.join();
            division.start();
            division.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }
}
