package com.spacrod;

import java.io.IOException;

public class EjemploUno {
    public static void main(String[] args) {
        try{
            //Process proceso = new ProcessBuilder("NOTEPAD").start();
            ProcessBuilder pb = new ProcessBuilder("notepad.exe");
            Process p = pb.start();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}