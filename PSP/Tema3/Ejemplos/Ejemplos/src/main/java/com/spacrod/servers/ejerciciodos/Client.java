package com.spacrod.servers.ejerciciodos;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try(Socket socket = new Socket("localhost", 44444);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Scanner sc = new Scanner(System.in)){
            System.out.println("¿Qué tienes que decirme?(escribe * para finalizar la conexión)");
            String cadena = sc.nextLine();
            out.write(cadena+"\n");
            out.flush();
            String resp = in.readLine();
            System.out.println("Respuesta del servidor: "+resp);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
