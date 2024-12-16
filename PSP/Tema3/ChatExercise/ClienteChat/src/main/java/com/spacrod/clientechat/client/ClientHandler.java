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
    public ClientHandler(String host, int port, String name) throws Exception {
        this.host = host;
        this.port = port;
        this.name = name;
        this.makeConnection();
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.sendUser();
        this.listeningMessages();
    }
    private void makeConnection() throws IOException {
        this.socket = new Socket(this.host, this.port);
    }
    public void send(ArrayList<String> message){
        try{
            out.writeObject(message);
            out.flush();
            System.out.println("Line 30 ClientHandler. Se envía el siguiente mensaje: "+message.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void sendUser() throws IOException {
        ArrayList<String> user =  new ArrayList<>();
        user.add(RequestOption.CONNECTION.getValue());
        user.add(this.name);
        this.send(user);
    }
    private void listeningMessages() throws IOException {
        Thread thread = new Thread(new ClientThread(this.socket));
        thread.start();
        System.out.println("Line 41 ClientHandler. Se genera un nuevo hilo ClientThread para gestionar la recepción de mensajes");
    }
    public void sendMessage(String message, String idReceptor) throws IOException {
        ArrayList<String> messageToSend = new ArrayList<>();
        messageToSend.add(MessageOption.MESSAGE.getValue());
        messageToSend.add(idReceptor);
        messageToSend.add(message);
        System.out.println("Line 46 ClientHandler. Se prepara el siguiente mensaje: "+message.toString());
        this.send(messageToSend);
    }
    public void sendDisconnectMessage() throws IOException {
        ArrayList<String> disconnectMessage =  new ArrayList<>();
        disconnectMessage.add(RequestOption.DISCONNECTION.getValue());
        out.writeObject(disconnectMessage);
        out.flush();
    }
}
