package com.spacrod.chatexample;

import java.io.IOException;
import java.net.Socket;

public class ClientOne {
    public static void main(String[] args) {
        try(Socket socket = new Socket("127.0.0.1", 44444)){
            System.out.println("Cliente conectado");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
