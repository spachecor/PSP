package com.spachecor.models;

import java.net.Socket;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        return Objects.equals(id, client.id);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", socket=" + socket +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
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
