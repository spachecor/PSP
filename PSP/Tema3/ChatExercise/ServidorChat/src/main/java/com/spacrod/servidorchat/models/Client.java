package com.spacrod.servidorchat.models;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Objects;

/**
 * Clase Client, que es la estructura de un cliente que se conecte
 * @author Selene
 * @version 1.0
 */
public class Client {
    private Integer id;
    private String name;
    private ObjectOutputStream out;
    private static Integer counter;
    static {
        counter = 0;
    }
    public Client(String name, Socket socket) throws IOException {
        Client.counter++;
        this.id = counter;
        this.name = name;
        this.out = new ObjectOutputStream(socket.getOutputStream());
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
                ", socket=" + out +
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

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }
}
