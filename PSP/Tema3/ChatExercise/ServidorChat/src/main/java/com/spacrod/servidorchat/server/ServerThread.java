package com.spacrod.servidorchat.server;

import com.spacrod.servidorchat.client.ClientThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Clase ServerThread que es el hilo que se ejecuta para aceptar las peticiones. Se ejecuta un hilo para esto por conflictos
 * con la interfaz gráfica.
 * @author Selene
 * @version 1.0
 */
public class ServerThread implements Runnable {
    private ServerSocket serverSocket;
    ServerThread(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }
    @Override
    public void run(){
        try {
            this.acceptRequest();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Método que gestiona la aceptación de las conexiones, creando los diferentes hilos para los clientes
     * @throws IOException Saltará una excepción si ocurre un fallo en la conexión
     */
    private void acceptRequest() throws IOException {
        while (true) {
            Socket socket = this.serverSocket.accept();
            Thread thread = new Thread(new ClientThread(socket));
            thread.start();
        }
    }
}
