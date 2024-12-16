package com.spachecor.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler {
    private String host;
    private int port;
    private String name;
    private Socket socket;
    private ObjectOutputStream out;
    public static String mensajes;
    private static ArrayList<String> clientes;
    static{
        ClientHandler.mensajes = "";
        ClientHandler.clientes = new ArrayList<>();
    }
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
    private void send(ArrayList<String> message){
        try{
            out.writeObject(message);
            out.flush();
        }catch(IOException e){
            System.err.println("No ha sido posible enviar el mensaje.\nContenido: "+message.toString()+"\nMotivo: "+e);
        }
    }
    private void sendUser(){
        ArrayList<String> user = new ArrayList<>();
        user.add(RequestOption.CONNECTION.getValue());
        user.add(this.name);
        this.send(user);
    }
    private void listeningMessages() {
        Thread thread = new Thread(() -> {
            try {
                new ClientThread(this.socket).run();
            } catch (IOException e) {
                System.err.println("Error en el hilo de escucha: " + e.getMessage());
            }
        });
        thread.setDaemon(true);
        thread.start();
    }


    public void sendMessage(String message, String idReceptor){
        ArrayList<String> messageToSend = new ArrayList<>();
        messageToSend.add(MessageOption.MESSAGE.getValue());
        messageToSend.add(idReceptor);
        messageToSend.add(message);
        this.send(messageToSend);
    }
    public void sendDisconnectMessage(){
        ArrayList<String> disconnectMessage =  new ArrayList<>();
        disconnectMessage.add(RequestOption.DISCONNECTION.getValue());
        this.send(disconnectMessage);
        System.exit(0);
    }
    public static String getMensajes() {
        return mensajes;
    }

    public static void setMensajes(String mensajes) {
        ClientHandler.mensajes += mensajes;
    }

    public static ArrayList<String> getClientes() {
        return clientes;
    }
}
