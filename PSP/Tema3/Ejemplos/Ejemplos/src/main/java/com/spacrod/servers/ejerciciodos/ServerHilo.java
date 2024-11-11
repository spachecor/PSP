package com.spacrod.servers.ejerciciodos;

import java.io.*;
import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.Semaphore;

public class ServerHilo implements Runnable{
    private Semaphore semaphore;
    private Socket socket;
    public ServerHilo(Semaphore semaphore, Socket socket) {
        this.semaphore = semaphore;
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            this.getSemaphore().acquire();
            gestionCliente();
            this.getSemaphore().release();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    private synchronized void gestionCliente(){
        try(BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))){
            System.out.println("Leyendo consulta del cliente....");
            String consulta = in.readLine();
            if(Objects.equals(consulta, "*")){
                Server.isRunning=false;
                return;
            }
            out.write(consulta.toUpperCase());
            out.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
