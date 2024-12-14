package com.spacrod.clientechat.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler {
    private String host;
    private int port;
    private String name;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    public ClientHandler(String host, int port, String name) throws Exception {
        this.host = host;
        this.port = port;
        this.name = name;
        this.makeConnection();
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
        this.sendUser();
    }
    private void makeConnection() throws IOException {
        this.socket = new Socket(this.host, this.port);
    }
    public void send(ArrayList<String> message) throws IOException {
        out.writeObject(message);
        out.flush();
    }
    private void sendUser() throws IOException {
        ArrayList<String> user =  new ArrayList<>();
        user.add(RequestOption.CONNECTION.getValue());
        user.add(this.name);
        this.send(user);
    }
    private void sendMessage(String message) throws IOException {

    }
    public void sendDisconnectMessage() throws IOException {
        ArrayList<String> disconnectMessage =  new ArrayList<>();
        disconnectMessage.add(RequestOption.DISCONNECTION.getValue());
        out.writeObject(disconnectMessage);
        out.flush();
    }
}
