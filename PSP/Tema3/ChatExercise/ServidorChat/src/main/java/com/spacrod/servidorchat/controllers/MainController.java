package com.spacrod.servidorchat.controllers;

import com.spacrod.servidorchat.server.ServerHandler;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainController {
    private static MainController instance;
    @FXML
    private VBox vBoxRoot;
    @FXML
    private TextArea textAreaLog;
    public MainController() {
        MainController.instance = this;
    }
    @FXML
    protected void initialize() {
        this.manageClosure();
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
    public static synchronized void logMessage(String log){
        if(MainController.instance != null){
            instance.addNewLog(log);
        }
    }
    private void manageClosure(){
        //ejecuta el código después de que la escena esté completamente inicializada
        javafx.application.Platform.runLater(() -> {
            if (this.vBoxRoot.getScene() != null && this.vBoxRoot.getScene().getWindow() != null) {
                this.vBoxRoot.getScene().getWindow().setOnCloseRequest(event -> {
                    System.exit(0);
                });
            } else {
                System.err.println("Error: la ventana no está disponible.");
            }
        });
    }
}