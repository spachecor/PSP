package com.spacrod.servidorchat.server;

import com.spacrod.servidorchat.client.ClientThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerHandler {
    private Integer port;
    private ServerSocket serverSocket;
    static List<Socket> clients;
    public ServerHandler(Integer port) throws IOException {
        ServerHandler.clients = new ArrayList<>();
        if(this.connectToPort(port)){
            this.port = port;
            try {
                Thread thread = new Thread(new ServerThread(serverSocket));
                thread.start();
            }catch (RuntimeException e){
                throw new IOException("La conexión ha sufrido un fallo");
            }
        }
    }
    private boolean connectToPort(Integer port){
        try{
            this.serverSocket = new ServerSocket(port);
            return true;
        }catch(IOException e){
            return false;
        }
    }

    /**
     * Método que gestiona la aceptación de las conexiones, creando los diferentes hilos para los clientes
     * @throws IOException Saltará una excepción si ocurre un fallo en la conexión
     */
    private void acceptRequest() throws IOException {
        while (true) {
            Socket socket = this.serverSocket.accept();
            ServerHandler.clients.add(socket);
            Thread thread = new Thread(new ClientThread(socket));
            thread.start();
        }
    }
    public Integer getPort() {
        return port;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public static List<Socket> getClients() {
        return clients;
    }
}
