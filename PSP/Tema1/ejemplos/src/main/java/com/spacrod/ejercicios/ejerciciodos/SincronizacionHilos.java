package com.spacrod.ejercicios.ejerciciodos;

import com.spacrod.ejercicios.ejerciciouno.CorredorHilo;

public class SincronizacionHilos {
    public static void main(String[] args) {
        try{
            Contador contador = new Contador();
            Thread hiloUno = new Thread(new HiloContador(contador));
            Thread hiloDos = new Thread(new HiloContador(contador));
            Thread hiloTres = new Thread(new HiloContador(contador));
            hiloUno.start();
            hiloDos.start();
            hiloTres.start();
            hiloUno.join();
            hiloDos.join();
            hiloTres.join();
            System.out.println(contador.getCuenta());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
