package com.spacrod.clientechat;

import com.spacrod.clientechat.services.FXService;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXService.loadNewWindow(stage, FXService.MAIN_VIEW, FXService.MAIN_TITLE, FXService.MAIN_SIZES);
    }

    public static void main(String[] args) {
        launch();
    }
}