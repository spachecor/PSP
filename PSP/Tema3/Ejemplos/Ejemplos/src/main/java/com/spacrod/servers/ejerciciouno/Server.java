package com.spacrod.servers.ejerciciouno;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    /*Actividad 1. Realiza un programa servidor TCP que acepte dos clientes
    Muestre por cada cliente conectado sus puertos local y remoto. Crea también
    el programa cliente que se conecte a ese servidor. Muestra los puertos locales
    y remotos a los que está conectado su socket, y la dirección IP de la máquina
    remota a la que se conecta*/
    public static final int PORT = 8080;
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(PORT);
        }catch(IOException e){
            System.out.println("No se puede escuchar el puerto: "+PORT);
        }
        try{
            System.out.println("Escuchando el puerto: "+PORT);
            Socket cliente1 = serverSocket.accept();
            System.out.println("Cliente 1 conectado: "+cliente1);
            Socket cliente2 = serverSocket.accept();
            System.out.println("Cliente 2 conectado: "+cliente2);
            System.out.println("Ya se ha escuchado todo lo que tenemos que escuchar");
            serverSocket.close();
            cliente1.close();
            cliente2.close();
        }catch(IOException e){
            System.out.println("No se pudo escuchar al cliente");
        }
    }
}
