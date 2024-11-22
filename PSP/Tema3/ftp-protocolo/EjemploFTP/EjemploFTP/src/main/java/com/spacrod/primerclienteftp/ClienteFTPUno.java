package com.spacrod.primerclienteftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ClienteFTPUno {
    public static void main(String[] args) throws IOException {
        FTPClient cliente = new FTPClient();
        String servidorFTP = "ftp.rediris.es";
        System.out.println("Nos conectamos a: "+servidorFTP);
        String user = "anonymous";
        String pass = "anonymous";

        cliente.connect(servidorFTP);
        //para entrar en modo pasivo
        cliente.enterLocalPassiveMode();
        boolean login = cliente.login(user, pass);
        if (login) {
            System.out.println("Login correcto...");
        }else{
            System.out.println("Login incorrecto...");
            cliente.disconnect();
            System.exit(1);
        }
        System.out.println("Directorio actual: "+cliente.printWorkingDirectory());

        FTPFile[] files = cliente.listFiles();
        System.out.println("Ficheros en el directorio actual: "+files.length);

        //array para visualizar el tipo de fichero
        String[] tipos = {"Fichero", "Directorio", "Link"};
        List<FTPFile> mirrorDirectory = new ArrayList<>();
        for(FTPFile file : files){
            System.out.println(tipos[file.getType()]+": "+file.getName());
            if(file.getName().equals("mirror")){
                cliente.changeWorkingDirectory(file.getName());
                mirrorDirectory = List.of(cliente.listFiles());
            }
        }
        if(!mirrorDirectory.isEmpty()){
            System.out.println("Leyendo dentro de /mirror");
            for(FTPFile file : mirrorDirectory){
                System.out.println(tipos[file.getType()]+": "+file.getName());
                if(file.getName().equals("freerainbowtables")){
                    File localFile = new File("freerainbowtables-local");
                    try(OutputStream outputStream = new FileOutputStream(localFile)){
                        boolean success = cliente.retrieveFile(file.getName(), outputStream);
                        if(success){
                            System.out.println("Archivo 'freerainbowtables' descargado existosamente");
                        }else {
                            System.out.println("Error al descargar el archivo 'freerainbowtables'");
                        }
                    }
                }
            }
        }
        boolean logout = cliente.logout();
        if (logout) {
            System.out.println("Logout del servidor FTP correcto...");
        } else {
            System.out.println("Logout del servidor FTP incorrecto...");
        }

        //obtener codigo de respuesta
        String respuesta = cliente.getReplyString();
        int replyCode = cliente.getReplyCode();
        System.out.println(respuesta);



        //comprobar la respuesta
        if(!FTPReply.isPositiveCompletion(replyCode)){
            cliente.disconnect();
            System.out.println("Conexión rechazada: "+respuesta);
            System.exit(0);
        }
        cliente.disconnect();
        System.out.println("Fin.");
    }
}
