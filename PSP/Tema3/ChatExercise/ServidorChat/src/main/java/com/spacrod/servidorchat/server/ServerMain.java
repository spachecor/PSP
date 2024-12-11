package com.spacrod.servidorchat.server;

public class ServerMain {
    private static Integer port;
    public static void initializeServer(Integer port){
        ServerMain.port = port;
    }
    public static Integer getPort() {
        return port;
    }
}
