package com.spacrod;

import java.io.File;

public class EjemploCuatro {
    public static void main(String[] args){
        try{
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "type ejemplito.txt");
            pb.redirectInput(new File("entrada.txt"));
            pb.redirectOutput(new File("salida.txt"));
            pb.redirectError(new File("error.txt"));
            Process p = pb.start();
            //esperar a que el proceso termina
            int exitVal = p.waitFor();
            System.out.println("El proceso terminó con valor de salida: " + exitVal);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
