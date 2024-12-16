package com.spachecor.client;

import com.spachecor.models.Client;
import com.spachecor.server.MessageOption;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

public class ClientThread implements Runnable{
    private static ArrayList<Client> clients;
    private Socket socket;
    private ObjectInputStream in;
    private Client client;
    private Boolean conectado;
    private String log;
    static {
        clients = new ArrayList<>();
    }
    public ClientThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new ObjectInputStream(socket.getInputStream());
        this.conectado = true;
        this.log = "";
    }
    @Override
    public void run() {
        while(conectado) {
            this.readMessage();
        }
    }
    private synchronized void readMessage(){
        try{
            ArrayList<String> message = (ArrayList<String>) this.in.readObject();
            if(message.getFirst().equals(RequestOption.CONNECTION.getValue())){
                this.showConnection(message);
            }else if(message.getFirst().equals(RequestOption.MESSAGE.getValue())){
                this.sendMessage(message);
            }else if(message.getFirst().equals(RequestOption.DISCONNECTION.getValue())){
                this.showDisconnection();
            }else System.err.println("Terrible, opcion no valida de mensaje.");
        }catch(Exception _){
        }
    }
    private void showConnection(ArrayList<String> message){
        this.client = new Client(message.get(1), this.socket);
        ClientThread.clients.add(this.client);
        System.out.println("Nuevo cliente: " + this.client.getId() + " - " + this.client.getName());
        this.sendClientListToAllClients();
    }
    private void sendMessage(ArrayList<String> message){
        Integer idReceptor = Integer.parseInt(message.get(1).trim());
        Client clienteReceptor = null;
        for(Client cliente : ClientThread.getClients()){
            if(Objects.equals(cliente.getId(), idReceptor))clienteReceptor = cliente;
        }
        if(clienteReceptor != null){
            try{
                ArrayList<String> mensajeParaReceptor = new ArrayList<>();
                mensajeParaReceptor.add(MessageOption.MESSAGE.getValue());
                mensajeParaReceptor.add("##### "+this.client.getId()+ " - " + this.client.getName() +" #####\n"+message.get(2));
                ObjectOutputStream outReceptor = new ObjectOutputStream(clienteReceptor.getSocket().getOutputStream());
                System.out.println(clienteReceptor.toString());
                outReceptor.writeObject(mensajeParaReceptor);
                outReceptor.flush();
            }catch(IOException e){
                System.err.println("Error al enviar el mensaje");
            }
        }else System.err.println("No se encontró el cliente con id: "+ idReceptor);
    }
    private void showDisconnection(){
        ClientThread.getClients().remove(this.client);
        System.out.println("El cliente \"" + this.client.getId() + " - " + this.client.getName() + "\" se ha desconectado.");
        this.conectado = false;
        this.sendClientListToAllClients();
    }
    private void sendClientListToAllClients(){
        ArrayList<String> mensajeListaClientes = new ArrayList<>();
        mensajeListaClientes.add(MessageOption.USERS_LIST.getValue());
        for(Client cliente : ClientThread.getClients()){
            mensajeListaClientes.add(cliente.getId()+ " - " + cliente.getName());
        }
        try{
            for(Client cliente : ClientThread.getClients()){
                ObjectOutputStream outReceptor = new ObjectOutputStream(cliente.getSocket().getOutputStream());
                outReceptor.writeObject(mensajeListaClientes);
                outReceptor.flush();
            }
        }catch (Exception e){
            System.err.println("Error al enviar la lista de clientes conectados.");
            e.printStackTrace();
        }
    }
    public String getLog() {
        return log;
    }
    private void setLog(String log) {
        this.log += log;
    }
    public static ArrayList<Client> getClients() {
        return clients;
    }
}
