package com.spacrod.chatexample;

import java.net.Socket;

/**
 * Clase que se encarga de gestionar al cliente por parte del servidor
 */
public class ClientRunnable implements Runnable {
    private static Integer contador;
    private Integer id;
    private String nombre;
    private Socket socket;
    {
        contador = 1;
    }
    public ClientRunnable(String nombre, Socket socket) {
        this.id = contador++;
        this.nombre = nombre;
        this.socket = socket;
    }
    @Override
    public void run() {
        System.out.println(String.format("El cliente con id %d llamado %s se ha conectado", this.id, this.nombre));
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
