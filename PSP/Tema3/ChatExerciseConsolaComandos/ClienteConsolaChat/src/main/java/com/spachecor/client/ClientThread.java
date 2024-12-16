package com.spachecor.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientThread implements Runnable{
    private ObjectInputStream in;
    private Boolean escuchando;
    protected ClientThread(Socket socket) throws IOException {
        //para evitar conflictos, se llama primero al output aunque no lo vayamos a usar
        new ObjectOutputStream(socket.getOutputStream()).flush();
        this.in = new ObjectInputStream(socket.getInputStream());
        this.escuchando = true;
    }
    @Override
    public void run() {
        while(escuchando){
            try{
                this.listeningMessages();
            }catch (Exception e){
                escuchando = false;
            }
        }
    }
    private void listeningMessages() throws IOException, ClassNotFoundException {
        ArrayList<String> message = (ArrayList<String>) this.in.readObject();
        System.out.println(message.toString());
        if(message.getFirst().equals(MessageOption.USERS_LIST.getValue())){
            for(int i = 1; i < message.size(); i++){
                ClientHandler.getClientes().add(message.get(i));
            }
        }else if(message.getFirst().equals(MessageOption.MESSAGE.getValue())){
            System.out.println(message.get(1));
            ClientHandler.setMensajes(message.get(1));
        }
    }
}
