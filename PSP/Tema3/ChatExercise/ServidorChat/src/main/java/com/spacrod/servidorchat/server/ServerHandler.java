package com.spacrod.servidorchat.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Clase ServerHandler, que se encarga de gestionar la conexión del servidor con el puerto y crear el hilo del servidor
 * que aceptará las conexiones
 * @author Selene
 * @version 1.0
 */
public class ServerHandler {
    private Integer port;
    private ServerSocket serverSocket;
    public ServerHandler(Integer port) throws Exception {
        if(this.connectToPort(port)){
            this.port = port;
            try {
                Thread thread = new Thread(new ServerThread(serverSocket));
                thread.start();
            }catch (RuntimeException e){
                throw new IOException("La conexión ha sufrido un fallo");
            }
        }else throw new Exception("Imposible conectar con el puerto " + port+". Proporcione otro puerto.");
    }

    /**
     * Función que se encarga de realizar la conexion con el puerto dado
     * @param port El puerto al que conectarse
     * @return true si la conexion es exitosa o false si no lo es
     */
    private boolean connectToPort(Integer port){
        try{
            this.serverSocket = new ServerSocket(port);
            return true;
        }catch(IOException e){
            return false;
        }
    }
    public Integer getPort() {
        return port;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
}
