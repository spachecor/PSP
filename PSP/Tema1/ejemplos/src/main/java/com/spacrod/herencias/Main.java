package com.spacrod.herencias;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Empleado juan = new Empleado("Juan", 50, "Reponedor");
        Empleado ana = new Empleado("Ana", 19, "Reponedor");
        Thread tUno = new Thread(juan);
        Thread tDos = new Thread(ana);
        tUno.start();
        tDos.start();
        tUno.join();
        tDos.join();
        System.out.println("Todos los empleados han terminado su trabajo.");
    }
}
