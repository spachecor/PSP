package com.spacrod.clientechat.client;

import com.spacrod.clientechat.controllers.MainController;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 * Clase ClientThread que es la clase que define el hilo que escucha constantemente nuevos mensajes entrantes
 * @author Selene
 * @version 1.0
 */
public class ClientThread implements Runnable {
    private ObjectInputStream in;
    private String name;
    protected ClientThread(Socket socket, String name) throws IOException {
        this.in = new ObjectInputStream(socket.getInputStream());
        this.name = name;
    }
    @Override
    public void run() {
        while(true){
            try {
                this.listeningMessages();
            }catch (SocketException e){
                JOptionPane.showMessageDialog(null, "Servidor desconectado. Se procederá a cerrar el programa.",
                        "Alerta", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    /**
     * Funcion que escucha el mensaje, lo filtra (si es mensaje o actualizacion de lista de usuarios disponibles) y
     * deriva su gestion a otras funciones
     * @throws IOException Exception que puede saltar
     * @throws ClassNotFoundException Excepcion que puede saltar
     */
    private void listeningMessages() throws IOException, ClassNotFoundException {
        System.out.println("Line 26 ClientThread. Se espera para obtener nuevos mensajes");
        Object messageObj = this.in.readObject();
        ArrayList<String> message = (ArrayList<String>) messageObj;
        System.out.println("Line 28 ClientThread. Se ha recibido un nuevo mensaje: "+message.toString());
        System.out.println("Line 27 ClientThread. Opcion que llega: "+message.getFirst());
        if(message.getFirst().equals(MessageOption.USERS_LIST.getValue())){
            ArrayList<String> clientList = new ArrayList<>();
            for(int i = 1; i < message.size(); i++){
                if(!this.name.equals(message.get(i).split("-")[1].trim())){
                    clientList.add(message.get(i));
                }
            }
            MainController.updateClientList(clientList);
        }else if(message.getFirst().equals(MessageOption.MESSAGE.getValue())){
            MainController.logMessage(message.get(1));
        }
    }
}
