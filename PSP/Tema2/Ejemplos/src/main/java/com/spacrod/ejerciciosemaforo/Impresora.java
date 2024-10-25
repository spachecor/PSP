package com.spacrod.ejerciciosemaforo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Impresora {
    public static void main(String[] args) {
        Semaphore impresora = new Semaphore(2);
        //Creamos los empleados
        List<Empleado> empleados = new ArrayList<>();
        Empleado juan = new Empleado(impresora, "Juan");
        Empleado lisa = new Empleado(impresora, "Lisa");
        Empleado manuel = new Empleado(impresora, "Manuel");
        Empleado selene = new Empleado(impresora, "Selene");
        Empleado alejandro = new Empleado(impresora, "Alejandro");
        Empleado jose = new Empleado(impresora, "Jose");
        empleados.add(juan);
        empleados.add(lisa);
        empleados.add(manuel);
        empleados.add(selene);
        empleados.add(alejandro);
        empleados.add(jose);
        for(Empleado empleado : empleados){
            Thread thread = new Thread(empleado);
            thread.start();
        }

    }
}