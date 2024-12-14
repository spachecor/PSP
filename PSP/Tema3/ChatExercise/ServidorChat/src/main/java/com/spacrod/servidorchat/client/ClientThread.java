package com.spacrod.servidorchat.client;

import com.spacrod.servidorchat.controllers.MainController;
import com.spacrod.servidorchat.models.Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientThread implements Runnable{
    private static ArrayList<Client> clients;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    static {
        clients = new ArrayList<>();
    }
    public ClientThread(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
    }
    @Override
    public void run() {
        try {
            //primero gestionamos la conexión del cliente entrante
            ArrayList<String> message = (ArrayList<String>) in.readObject();
            Client client = new Client(message.get(1), this.socket);
            ClientThread.clients.add(client);
            MainController.logMessage("Nuevo cliente: " + client.getId() + " - " + client.getName());
            ArrayList<String> message2 = (ArrayList<String>) in.readObject();
            if(message2.get(0).equals(RequestOption.DISCONNECTION.getValue())){
                clients.remove(client);
                MainController.logMessage("El cliente \"" + client.getId() + " - " + client.getName() + "\" se ha desconectado.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //actualizamos a todos los clientes la lista de clientes con el nuevo cliente

        //ahora gestionamos los mensajes entrantes

    }

    public static ArrayList<Client> getClients() {
        return clients;
    }
}
