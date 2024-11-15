package com.spacrod.servervideo.udp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    public static void main(String[] args) {
        final int PORT = 8080;
        try (DatagramSocket socketUDP = new DatagramSocket(PORT)) {
            byte[] buffer = new byte[4096];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            try (FileOutputStream fos = new FileOutputStream("src/main/resources/video/video-recibidoUDP.mp4")) {
                System.out.println("Servidor esperando paquetes...");
                while (true) {
                    socketUDP.receive(packet);
                    if (packet.getLength() == 0) {
                        System.out.println("Transmisión completada. Archivo guardado como video-recibido.mp4");
                        break;
                    }
                    fos.write(packet.getData(), 0, packet.getLength());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
