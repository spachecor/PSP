package com.spacrod.servidorchat.controllers;

import com.spacrod.servidorchat.server.ServerHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class MainController {
    private static MainController instance;
    @FXML
    private TextArea textAreaLog;
    public MainController() {
        MainController.instance = this;
    }
    /**
     * Método que agrega un nuevo log al label labelLog
     * @param log El nuevo mensaje para el log
     */
    private void addNewLog(String log){
        this.textAreaLog.setText(this.textAreaLog.getText()+log+"\n");
    }

    /**
     * Método intermediario para agregar un nuevo mensaje al log. Usa una instancia de la clase para permitir agregar
     * logs de manera estática
     * @param log El nuevo mensaje del log
     */
    public static void logMessage(String log){
        if(MainController.instance != null){
            instance.addNewLog(log);
        }
    }
}