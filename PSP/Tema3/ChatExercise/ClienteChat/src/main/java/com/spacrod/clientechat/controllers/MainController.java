package com.spacrod.clientechat.controllers;

import com.spacrod.clientechat.services.FXService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class MainController {
    private static MainController instance;
    @FXML
    private TextArea textAreaLog;
    @FXML
    private VBox vBoxRoot;
    @FXML
    private ChoiceBox<String> choiceBoxDestinatario;
    @FXML
    private Button buttonEnviar;
    @FXML
    private TextField textFieldMensaje;
    public MainController() {
        MainController.instance = this;
    }
    @FXML
    protected void initialize() {
        this.manageClosure();
    }
    @FXML
    protected void onClickButtonEnviar(){
        Platform.runLater(()->{
            if(this.choiceBoxDestinatario!=null && this.choiceBoxDestinatario.getValue()!=null){
                if(!this.choiceBoxDestinatario.getValue().isEmpty()){
                    String receptor = choiceBoxDestinatario.getValue().trim();
                    String[] receptorArray = receptor.split("-");
                    try {
                        FXService.getClientHandler().sendMessage(textFieldMensaje.getText(), receptorArray);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else{
                    this.showAlert("¡Nada de campos vacíos!", "Debe escoger un usuario válido. Si no hay uno, espere a que otro usuario se conecte para poder chatear con él.");
                }
            }else{
                this.showAlert("¡Nada de campos vacíos!", "Debe escoger un usuario válido. Si no hay uno, espere a que otro usuario se conecte para poder chatear con él.");
            }
        });
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
        System.out.println("Line 61 MainController. Se va a imprimir un nuevo log en el cliente");
        if(MainController.instance != null){
            instance.addNewLog(log);
        }
    }
    public static void updateClientList(ArrayList<String> clients){
        Platform.runLater(() -> {
            MainController.instance.choiceBoxDestinatario.getItems().clear();
            MainController.instance.choiceBoxDestinatario.getItems().addAll(clients);
            System.out.println("Line 63 MainController. Se actualizan las opciones del choicebox: "+clients.toString());
        });
    }
    private void manageClosure(){
        //ejecuta el código después de que la escena esté completamente inicializada
        Platform.runLater(() -> {
            if (this.vBoxRoot.getScene() != null && this.vBoxRoot.getScene().getWindow() != null) {
                this.vBoxRoot.getScene().getWindow().setOnCloseRequest(event -> {
                    try {
                        FXService.getClientHandler().sendDisconnectMessage();
                        System.exit(0);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            } else {
                System.err.println("Error: la ventana no está disponible.");
            }
        });
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}