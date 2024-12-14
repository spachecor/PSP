package com.spacrod.servidorchat.controllers;

import com.spacrod.servidorchat.server.ServerHandler;
import com.spacrod.servidorchat.services.FXService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ConfiguracionComunicacionController {
    @FXML
    private Label labelError;
    @FXML
    private TextField textFieldPort;
    @FXML
    protected void onClickButtonAceptar(ActionEvent actionEvent){
        String contenidoTextFieldPort = textFieldPort.getText();
        if(
                contenidoTextFieldPort != null
                && !contenidoTextFieldPort.isEmpty()
                && containsOnlyNumbers(contenidoTextFieldPort)
                && Integer.parseInt(contenidoTextFieldPort) >= 10000 && Integer.parseInt(contenidoTextFieldPort) <= 99999
        ){
            try{
                new ServerHandler(Integer.parseInt(contenidoTextFieldPort));
                FXService.loadNewWindow(new Stage(), FXService.MAIN_VIEW, FXService.MAIN_TITLE, FXService.MAIN_SIZES);
                MainController.logMessage("Inicializando servidor... [ok]");
            }catch (IOException ioe) {
                labelError.setText(ioe.getMessage());
            }catch(Exception e){
                e.printStackTrace();
            }finally {
                FXService.closeWindow(actionEvent);
            }
        }else{
            labelError.setText("Por favor ingrese un valor valido.(10000-99999)");
        }
    }
    @FXML
    protected void onClickButtonCancelar(ActionEvent actionEvent){
        FXService.closeWindow(actionEvent);
    }
    private boolean containsOnlyNumbers(String input) {
        return input != null && input.matches("\\d+");
    }
}
