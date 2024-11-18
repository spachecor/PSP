package com.spacrod.servers.ejerciciouno;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try(Socket socketUno = new Socket("192.168.12.102", 44445)){
            System.out.println("Puerto local: "+socketUno.getLocalPort());
            System.out.println("Puerto remoto: "+socketUno.getPort());
            System.out.println("Nombre host/IP: "+socketUno.getInetAddress());
            System.out.println("Host remoto: "+socketUno.getInetAddress().getHostName());
            System.out.println("Ip Host Remoto: "+socketUno.getInetAddress().getHostAddress());
        }catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        /*try(Socket socketDos = new Socket("192.168.13.220", 4444)){
            System.out.println("Puerto local: "+socketDos.getLocalPort());
            System.out.println("Puerto remoto: "+socketDos.getPort());
            System.out.println("Nombre host/IP: "+socketDos.getInetAddress());
            System.out.println("Host remoto: "+socketDos.getInetAddress().getHostName());
            System.out.println("Ip Host Remoto: "+socketDos.getInetAddress().getHostAddress());
        }catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }*/
    }
}
