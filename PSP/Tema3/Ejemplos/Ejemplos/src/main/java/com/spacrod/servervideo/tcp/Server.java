package com.spacrod.servervideo.tcp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 44444; // Puerto de escucha

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor escuchando en el puerto " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress());

                try (InputStream in = socket.getInputStream();
                     FileOutputStream fileOut = new FileOutputStream("src/main/resources/video/video_recibido.mp4")) {

                    byte[] buffer = new byte[4096]; // Tamaño del buffer
                    int bytesRead;
                    while ((bytesRead = in.read(buffer)) != -1) {
                        fileOut.write(buffer, 0, bytesRead);
                    }
                    System.out.println("Archivo recibido y guardado como video_recibido.mp4");
                } catch (IOException e) {
                    System.err.println("Error al recibir el archivo: " + e.getMessage());
                }
                socket.close();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
