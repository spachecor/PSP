package com.spacrod.clientechat.client;

import com.spacrod.clientechat.controllers.MainController;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Clase ClientHandler, que es la encargada de gestionar el cliente.
 * @author Selene
 * @version 1.0
 */
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

    /**
     * Funcion que realiza la conexión con el host y el puerto dados
     * @throws IOException Excepcion que puede saltar si el puerto o el host no son correctos(puerto incorrecto u ocupado, host incorrecto, etc.)
     */
    private void makeConnection() throws IOException {
        this.socket = new Socket(this.host, this.port);
    }

    /**
     * Funcion que envía un mensaje al servidor
     * @param message El mensaje a enviar
     */
    public void send(ArrayList<String> message){
        try{
            out.writeObject(message);
            out.flush();
            System.out.println("Line 30 ClientHandler. Se envía el siguiente mensaje: "+message.toString());
        }catch(Exception e){
            System.out.println("Line 34 ClientHandler. No se consigue envía el siguiente mensaje: "+message.toString()+"\nError: "+e.getMessage());
        }
    }

    /**
     * Funcion que define el mensaje de conexion del nuevo cliente y se lo envia al servidor
     * @throws IOException
     */
    private void sendUser() throws IOException {
        ArrayList<String> user =  new ArrayList<>();
        user.add(RequestOption.CONNECTION.getValue());
        user.add(this.name);
        this.send(user);
    }

    /**
     * Funcion que crea el hilo que escucha constantemente los nuevos mensajes entrantes
     * @throws IOException Excepcion que puede generarse al crear el hilo
     */
    private void listeningMessages() throws IOException {
        Thread thread = new Thread(new ClientThread(this.socket, name));
        thread.start();
        System.out.println("Line 41 ClientHandler. Se genera un nuevo hilo ClientThread para gestionar la recepción de mensajes");
    }

    /**
     * Funcion que define el mensaje que se envia a otro cliente a traves del servidor con los parámetros dados
     * @param message El mensaje a enviar
     * @param receptor El receptor (id+nombre)
     */
    public void sendMessage(String message, String[] receptor){
        String idReceptor = receptor[0];
        ArrayList<String> messageToSend = new ArrayList<>();
        messageToSend.add(MessageOption.MESSAGE.getValue());
        messageToSend.add(idReceptor);
        messageToSend.add(message);
        System.out.println("Line 46 ClientHandler. Se prepara el siguiente mensaje: "+message.toString());
        this.send(messageToSend);
        MainController.logMessage("## Yo -> "+receptor[0] + " - " + receptor[1] +" ##:\n"+message);
    }

    /**
     * Funcion que define la estructura del mensaje de desconexion del cliente y se lo envia al servidor
     * @throws IOException Excepcion que puede generarse al enviar el mensaje al servidor
     */
    public void sendDisconnectMessage() throws IOException {
        ArrayList<String> disconnectMessage =  new ArrayList<>();
        disconnectMessage.add(RequestOption.DISCONNECTION.getValue());
        out.writeObject(disconnectMessage);
        out.flush();
    }

    public String getName() {
        return name;
    }
}
