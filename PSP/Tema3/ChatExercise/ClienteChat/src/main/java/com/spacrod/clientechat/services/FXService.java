package com.spacrod.clientechat.services;

import com.spacrod.clientechat.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FXService {
    public static final String MAIN_VIEW = "main-view.fxml";
    public static final String MAIN_TITLE = "Cliente Chat";
    public static final Integer[] MAIN_SIZES = new Integer[]{480, 640};
    public static void loadNewWindow(Stage stage, String view, String title, Integer[] sizes) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/"+view));
        Scene scene = new Scene(fxmlLoader.load(), sizes[0], sizes[1]);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}
