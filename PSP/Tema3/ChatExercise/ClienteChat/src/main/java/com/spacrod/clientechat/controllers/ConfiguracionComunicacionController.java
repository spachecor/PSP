package com.spacrod.clientechat.controllers;

import com.spacrod.clientechat.client.ClientHandler;
import com.spacrod.clientechat.services.FXService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.Socket;

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
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            FXService.closeWindow(actionEvent);
        }
    }
    @FXML
    protected void onClickButtonCancelar(ActionEvent actionEvent){FXService.closeWindow(actionEvent);}
}
