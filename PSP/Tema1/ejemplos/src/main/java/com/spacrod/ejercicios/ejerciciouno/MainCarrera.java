package com.spacrod.ejercicios.ejerciciouno;

public class MainCarrera {
    public static void main(String[] args) {
        try{
            CorredorHilo corredorUno = new CorredorHilo("Juan");
            CorredorHilo corredorDos = new CorredorHilo("Antonio");
            CorredorHilo corredorTres = new CorredorHilo("Fernanda");
            System.out.println(corredorUno.getProgreso());
            System.out.println(corredorDos.getProgreso());
            System.out.println(corredorTres.getProgreso());
            Thread hiloCorredorUno = new Thread(corredorUno);
            Thread hiloCorredorDos = new Thread(corredorDos);
            Thread hiloCorredorTres = new Thread(corredorTres);
            hiloCorredorUno.start();
            hiloCorredorDos.start();
            hiloCorredorTres.start();
            hiloCorredorUno.join();
            hiloCorredorDos.join();
            hiloCorredorTres.join();
            System.out.println("Todos los corredores han llegado a la meta");
            //eleccion del ganador
            String primero = null;
            if(corredorUno.getProgreso() < corredorDos.getProgreso() && corredorUno.getProgreso() < corredorTres.getProgreso())primero = corredorUno.getNombre();
            else if(corredorDos.getProgreso() < corredorUno.getProgreso() && corredorDos.getProgreso() < corredorTres.getProgreso())primero = corredorDos.getNombre();
            else if(corredorTres.getProgreso() < corredorUno.getProgreso() && corredorTres.getProgreso() < corredorDos.getProgreso())primero = corredorTres.getNombre();
            System.out.println("Carrera terminada, ganador: "+primero);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
