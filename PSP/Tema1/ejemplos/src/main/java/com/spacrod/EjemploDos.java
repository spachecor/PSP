package com.spacrod;

import java.io.IOException;

public class EjemploDos {
    public static void main(String[] args) {
        try{
            Process p = new ProcessBuilder("NOTEPAD", System.getProperty("user.home")+"\\Downloads\\listaContadores.txt").start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
