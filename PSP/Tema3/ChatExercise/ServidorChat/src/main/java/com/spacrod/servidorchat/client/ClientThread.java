package com.spacrod.servidorchat.client;

import com.spacrod.servidorchat.controllers.MainController;

import java.net.Socket;

public class ClientThread implements Runnable{
    private Socket socket;
    public ClientThread(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        MainController.logMessage("Nuevo cliente conectado: "+socket.getInetAddress());
    }
}
