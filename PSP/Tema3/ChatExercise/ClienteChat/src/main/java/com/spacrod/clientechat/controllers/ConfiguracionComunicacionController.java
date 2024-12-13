package com.spacrod.clientechat.controllers;

import com.spacrod.clientechat.services.FXService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ConfiguracionComunicacionController {
    @FXML
    protected void onClickButtonAceptar(ActionEvent actionEvent){
        try{
            FXService.loadNewWindow(new Stage(), FXService.MAIN_VIEW, FXService.MAIN_TITLE, FXService.MAIN_SIZES);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            FXService.closeWindow(actionEvent);
        }
    }
    @FXML
    protected void onClickButtonCancelar(ActionEvent actionEvent){FXService.closeWindow(actionEvent);}
}
