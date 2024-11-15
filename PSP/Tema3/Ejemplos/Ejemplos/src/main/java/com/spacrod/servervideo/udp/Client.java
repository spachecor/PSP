package com.spacrod.servervideo.udp;

import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    public static void main(String[] args) {
        final int PORT = 8080;
        final String SERVER_IP = "127.0.0.1";

        try (DatagramSocket socket = new DatagramSocket()) {
            File file = new File("src/main/resources/video/video.mp4");
            byte[] buffer = new byte[4096];
            InetAddress serverAddress = InetAddress.getByName(SERVER_IP);

            try (FileInputStream fis = new FileInputStream(file)) {
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    DatagramPacket packet = new DatagramPacket(buffer, bytesRead, serverAddress, PORT);
                    socket.send(packet);
                }

                DatagramPacket endPacket = new DatagramPacket(new byte[0], 0, serverAddress, PORT);
                socket.send(endPacket);
                System.out.println("Archivo enviado al servidor.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
