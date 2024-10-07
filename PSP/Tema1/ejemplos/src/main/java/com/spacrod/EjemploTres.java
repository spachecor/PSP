package com.spacrod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EjemploTres {
    public static void main(String[] args) {
        try{
            //Ejecutamos el comando dir
            Process p = new ProcessBuilder("cmd", "/c", "dir").start();
            //Capturamos la salida del proceso
            BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String linea;
            //Imprimimos cada linea de la salida
            while((linea= bf.readLine())!=null){
                System.out.println(linea);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
