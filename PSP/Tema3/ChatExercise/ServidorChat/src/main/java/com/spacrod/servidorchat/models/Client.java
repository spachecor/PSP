package com.spacrod.servidorchat.models;

import java.net.Socket;

public class Client {
    private Integer id;
    private String name;
    private Socket socket;
    private static Integer counter;
    static {
        counter = 0;
    }
    public Client(String name, Socket socket) {
        Client.counter++;
        this.id = counter;
        this.name = name;
        this.socket = socket;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
