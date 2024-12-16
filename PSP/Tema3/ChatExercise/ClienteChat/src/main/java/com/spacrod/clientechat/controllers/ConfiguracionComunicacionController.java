package com.spacrod.clientechat.controllers;

import com.spacrod.clientechat.client.ClientHandler;
import com.spacrod.clientechat.services.FXService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * Clase ConfiguracionController que es el controlador de la configuracion-comunicacion.fxml
 * @author Selene
 * @version 1.0
 */
public class ConfiguracionComunicacionController {
    @FXML
    private TextField textFieldHost;
    @FXML
    private TextField textFieldPort;
    @FXML
    private TextField textFieldName;
    @FXML
    protected void onClickButtonAceptar(ActionEvent actionEvent){
        try{
            ClientHandler clientHandler = new  ClientHandler(textFieldHost.getText(), Integer.parseInt(textFieldPort.getText()), textFieldName.getText());
            FXService.loadNewWindow(new Stage(), FXService.MAIN_VIEW, FXService.MAIN_TITLE, FXService.MAIN_SIZES);
            FXService.setClientHandler(clientHandler);
            FXService.closeWindow(actionEvent);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Debe introducir correctamente los datos.",
                    "Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    @FXML
    protected void onClickButtonCancelar(ActionEvent actionEvent){FXService.closeWindow(actionEvent);}
}
