package com.spacrod.clientechat.client;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try(Socket socket = new Socket("127.0.0.1", 10101);){
            System.out.println("Connected");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
