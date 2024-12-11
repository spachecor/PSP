package com.spacrod.chatexample;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private final static Integer PORT;
    static {
         PORT = 44444;
    }
    public static void main(String[] args) {
        List<Socket> sockets = new ArrayList<>();
        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Inicializando el servidor... [OK].");
            while(true) {
                Socket socket = serverSocket.accept();
                sockets.add(socket);
                Thread thread = new Thread(new ClientRunnable("Juanito", socket));
                thread.start();
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
