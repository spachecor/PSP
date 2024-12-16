package com.spachecor;

import com.spachecor.server.ServerHandler;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        boolean puertoCorrecto = false;
        do{
            System.out.println("Por favor, introduzca el puerto a conectarse(49152-65535): ");
            String puerto = sc.nextLine();
            if(Main.containsOnlyNumbers(puerto) && Integer.parseInt(puerto)>=49152 && Integer.parseInt(puerto)<=65535){
                try{
                    new ServerHandler(Integer.parseInt(puerto));
                    puertoCorrecto = true;
                }catch (Exception e){
                    System.err.println("Imposible conectar al puerto, pruebe con otro distinto");
                    puertoCorrecto = false;
                }
            }else System.err.println("Por favor, introduzca un puerto correcto, dentro de los rangos ofrecidos.");
        }while(!puertoCorrecto);
    }
    private static boolean containsOnlyNumbers(String input) {
        return input != null && input.matches("\\d+");
    }
}