package com.spachecor.server;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerHandler {
    private Integer port;
    private ServerSocket serverSocket;
    public ServerHandler(Integer port) throws Exception {
        if(this.connectToPort(port)){
            this.port = port;
            try {
                Thread thread = new Thread(new ServerThread(serverSocket));
                thread.start();
                System.out.println("Inicializando servidor... [ok]");
            }catch (RuntimeException e){
                throw new IOException("La conexión ha sufrido un fallo");
            }
        }else throw new Exception("Imposible conectar con el puerto " + port+". Proporcione otro puerto.");
    }
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
