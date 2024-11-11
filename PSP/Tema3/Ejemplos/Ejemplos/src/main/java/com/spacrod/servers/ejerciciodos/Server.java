package com.spacrod.servers.ejerciciodos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;

public class Server {
    /*Actividad 2.1.
    * Programa servidor que escuche en el puerto 44444. Cada vez que se conecte un cliente creará un
    * nuevo hilo para atenderlo. Se mostrará en la consola del servidor la dirección IP y el puerto remoto del cliente
    * que se conecta y cuando el cliente se desconecte debe mostrar un mensaje indicando que se ha desconectado.
    *
    * En el hilo que atiende al cliente se recibe una cadena de caracteres, si es distinta de “*” se enviará de nuevo al
    * cliente en mayúsculas.
    *
    * En el programa cliente se muestra una pantalla donde el cliente escribe la cadena y un botón Enviar. También hay un
    * botón limpiar y un botón salir que envía un “*” al servidor y finaliza la ejecución.*/
    public static final Integer PORT;
    public static volatile Boolean isRunning;
    static{
        PORT = 44444;
        isRunning = true;
    }
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        try(ServerSocket serverSocket = new ServerSocket(PORT)){
            while(isRunning){
                try{
                    System.out.println("Escuchando....");
                    Socket socket = serverSocket.accept();
                    System.out.println(
                            "Se ha conectado un nuevo cliente:\n-Dirección IP: "
                                    +socket.getInetAddress().getHostAddress()
                                    +"\n-Puerto remoto del cliente: "
                                    +socket.getPort()
                    );
                    Thread thread = new Thread(new ServerHilo(semaphore, socket));
                    thread.start();
                    System.out.println(
                            "El cliente con dirección ip: "
                                    +socket.getInetAddress().getHostAddress()
                                    +", y nombre del puerto remoto: "
                                    +socket.getPort()
                                    +" se ha desconectado."
                    );
                    if(!isRunning){
                        socket.close();
                        serverSocket.close();
                        break;
                    }
                }catch(IOException e){
                    System.out.println("No se pudo escuchar al cliente: "+PORT);
                }
            }
        }catch(IOException e){
            System.out.println("No se puede escuchar el puerto: "+PORT);
        }
    }
}
