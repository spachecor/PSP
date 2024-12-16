package com.spacrod.clientechat.client;

import com.spacrod.clientechat.controllers.MainController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientThread implements Runnable {
    private ObjectInputStream in;
    protected ClientThread(Socket socket) throws IOException {
        this.in = new ObjectInputStream(socket.getInputStream());
    }
    @Override
    public void run() {
        while(true){
            try{
                this.listeningMessages();
            }catch (Exception e){
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
                //break;
            }
        }
    }
    private void listeningMessages() throws IOException, ClassNotFoundException {
        System.out.println("Line 26 ClientThread. Se espera para obtener nuevos mensajes");
        Object messageObj = this.in.readObject();
        ArrayList<String> message = (ArrayList<String>) messageObj;
        System.out.println("Line 28 ClientThread. Se ha recibido un nuevo mensaje: "+message.toString());
        System.out.println("Line 27 ClientThread. Opcion que llega: "+message.getFirst());
        if(message.getFirst().equals(MessageOption.USERS_LIST.getValue())){
            ArrayList<String> clientList = new ArrayList<>();
            for(int i = 1; i < message.size(); i++){
                clientList.add(message.get(i));
            }
            MainController.updateClientList(clientList);
        }else if(message.getFirst().equals(MessageOption.MESSAGE.getValue())){
            MainController.logMessage(message.get(1));
        }
    }
}
