package com.spacrod.servidorchat.controllers;

import com.spacrod.servidorchat.server.ServerHandler;
import com.spacrod.servidorchat.services.FXService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase ConfiguracionComunicacionController que es el controlador de la vista configuracion-comunicacion.fxml
 * @author Selene
 * @version 1.0
 */
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
                && Integer.parseInt(contenidoTextFieldPort) >= 49152 && Integer.parseInt(contenidoTextFieldPort) <= 65535
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
            labelError.setText("Por favor ingrese un valor valido.(49152-65535)");
        }
    }
    @FXML
    protected void onClickButtonCancelar(ActionEvent actionEvent){
        FXService.closeWindow(actionEvent);
    }

    /**
     * Funcion que comprueba que el String pasado solo contenga numeros
     * @param input El string a comprobar
     * @return true si contiene solo numeros o false si contiene algo que no sea un numero
     */
    private boolean containsOnlyNumbers(String input) {
        return input != null && input.matches("\\d+");
    }
}
