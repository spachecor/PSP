package com.spacrod.servidorchat;

import com.spacrod.servidorchat.services.FXService;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXService.loadNewWindow(
                stage,
                FXService.CONFIGURACION_COMUNICACION_VIEW,
                FXService.CONFIGURACION_COMUNICACION_TITLE,
                FXService.CONFIGURACION_COMUNICACION_SIZES
        );
    }

    public static void main(String[] args) {
        launch();
    }
}