package com.spachecor;

import com.spachecor.client.ClientHandler;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ClientHandler clientHandler = Main.menuConfiguracionConexion();
        System.out.println(ClientHandler.getClientes());
        Thread.sleep(10000);
        //clientHandler.sendDisconnectMessage();
    }
    private static ClientHandler menuConfiguracionConexion(){
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("Por favor, introduzca un host(Ej: localhost o 127.0.0.1): ");
            String host = sc.nextLine();
            if(host.isEmpty()){
                System.err.println("El host no puede ser vacio");
                continue;
            }
            System.out.println("Por favor, introduzca un puerto(49152-65535): ");
            String puerto = sc.nextLine();
            if(!puerto.isEmpty() && Main.containsOnlyNumbers(puerto) && Integer.parseInt(puerto)>=49152 && Integer.parseInt(puerto)<=65535){
                System.out.println("Introduzca su nombre: ");
                String nombre = sc.nextLine();
                if(nombre.isEmpty())continue;
                try{
                    return new ClientHandler(host, Integer.parseInt(puerto), nombre);
                }catch (Exception e){
                    System.err.println("No ha sido posible conectarse con los datos proporcionados. Intente cambiar el puerto y revise si la información es correcta.");
                }
            }else System.err.println("El puerto debe ser correcto. (49152-65535)");
        }while(true);
    }
    private static boolean containsOnlyNumbers(String input) {
        return input != null && input.matches("\\d+");
    }
}