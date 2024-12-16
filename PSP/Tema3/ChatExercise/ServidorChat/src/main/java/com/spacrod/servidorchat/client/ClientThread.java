package com.spacrod.servidorchat.client;

import com.spacrod.servidorchat.controllers.MainController;
import com.spacrod.servidorchat.models.Client;
import com.spacrod.servidorchat.server.MessageOption;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

public class ClientThread implements Runnable {
    private static ArrayList<Client> clients;
    private Socket socket;
    private ObjectInputStream in;
    private Client client;
    static {
        clients = new ArrayList<>();
    }
    public ClientThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new ObjectInputStream(socket.getInputStream());
    }
    @Override
    public void run() {
        while(true){
            try{
                this.readMessage();
            }catch(Exception e){
                System.out.println("Line 32 ClientThread. Excepción: "+e.getMessage());
                break;
            }
        }
    }
    private synchronized void readMessage() throws Exception{
        ArrayList<String> message = (ArrayList<String>) this.in.readObject();
        System.out.println("Line 38 ClientThread. Opcion que llega: "+message.getFirst());
        if(message.getFirst().equals(RequestOption.CONNECTION.getValue())){
            this.showConnection(message);
        }else if(message.getFirst().equals(RequestOption.MESSAGE.getValue())){
            this.sendMessage(message);
        }else if(message.getFirst().equals(RequestOption.DISCONNECTION.getValue())){
            this.showDisconnection();
        }else throw new Exception("Terrible, tipo de mensaje no correcto");
    }
    private void showConnection(ArrayList<String> message) throws IOException {
        this.client = new Client(message.get(1), this.socket);
        ClientThread.getClients().add(this.client);
        MainController.logMessage("Nuevo cliente: " + this.client.getId() + " - " + this.client.getName());
        this.sendClientListToAllClients();
    }
    private void sendMessage(ArrayList<String> message) throws IOException {
        Integer idReceptor = Integer.parseInt(message.get(1).trim());
        System.out.println("Line 59 ClientThread. idReceptor que llega: "+message.get(1));
        Client clienteReceptor = null;
        for(Client cliente : ClientThread.getClients()){
            if(Objects.equals(cliente.getId(), idReceptor))clienteReceptor = cliente;
        }
        if(clienteReceptor != null){
            ArrayList<String> mensajeParaReceptor = new ArrayList<>();
            mensajeParaReceptor.add(MessageOption.MESSAGE.getValue());
            mensajeParaReceptor.add("##### "+this.client.getId()+ " - " + this.client.getName() +" #####\n"+message.get(2));
            System.out.println(clienteReceptor.toString());
            clienteReceptor.getOut().writeObject(mensajeParaReceptor);
            clienteReceptor.getOut().flush();
            System.out.println("Line 71 ClientThread. El mensaje ha sido enviado: " + mensajeParaReceptor.toString());
        }else MainController.logMessage("No se encontró el cliente con id: "+ idReceptor);
    }
    private void showDisconnection() throws IOException {
        ClientThread.getClients().remove(this.client);
        MainController.logMessage("El cliente \"" + this.client.getId() + " - " + this.client.getName() + "\" se ha desconectado.");
        this.sendClientListToAllClients();
    }
    private void sendClientListToAllClients() throws IOException {
        ArrayList<String> mensajeListaClientes = new ArrayList<>();
        mensajeListaClientes.add(MessageOption.USERS_LIST.getValue());
        for(Client cliente : ClientThread.getClients()){
            mensajeListaClientes.add(cliente.getId()+ " - " + cliente.getName());
        }
        for(Client cliente : ClientThread.getClients()){
            cliente.getOut().writeObject(mensajeListaClientes);
            cliente.getOut().flush();
            System.out.println("Line 87 ClientThread se han mandado los clientes: " + mensajeListaClientes.toString());
        }
    }
    public static ArrayList<Client> getClients() {
        return clients;
    }
}
