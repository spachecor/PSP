package com.spacrod.servervideo.tcp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // Dirección del servidor
        int port = 44444; // Puerto del servidor
        String filePath = "src/main/resources/video/video.mp4"; // Ruta del archivo a enviar

        try (Socket socket = new Socket(serverAddress, port);
             FileInputStream fileIn = new FileInputStream(filePath);
             OutputStream out = socket.getOutputStream()) {

            System.out.println("Conectado al servidor");

            byte[] buffer = new byte[4096]; // Tamaño del buffer
            int bytesRead;
            while ((bytesRead = fileIn.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            System.out.println("Archivo enviado al servidor");
        } catch (IOException e) {
            System.err.println("Error al enviar el archivo: " + e.getMessage());
        }
    }
}
