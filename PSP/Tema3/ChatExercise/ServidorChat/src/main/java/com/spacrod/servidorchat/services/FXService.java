package com.spacrod.servidorchat.services;

import com.spacrod.servidorchat.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Clase FXService, que se encarga de crear las nuevas ventanas y gestionarlas
 * @author Selene
 * @version 1.0
 */
public class FXService {
    public static final String CONFIGURACION_COMUNICACION_VIEW = "configuracion-comunicacion.fxml";
    public static final String CONFIGURACION_COMUNICACION_TITLE = "Configuraciones de la comunicacion";
    public static final Integer[] CONFIGURACION_COMUNICACION_SIZES = new Integer[]{600, 400};
    public static final String MAIN_VIEW = "main-view.fxml";
    public static final String MAIN_TITLE = "Servidor Chat";
    public static final Integer[] MAIN_SIZES = new Integer[]{480, 640};

    /**
     * Funcion que se encarga de cargar una nueva ventana
     * @param stage La stage de la nueva ventana
     * @param view El nombre del archivo fxml
     * @param title El titulo de la ventana
     * @param sizes Las dimensiones de la ventana
     * @throws IOException La excepcion que puede saltar
     */
    public static void loadNewWindow(Stage stage, String view, String title, Integer[] sizes) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/"+view));
        Scene scene = new Scene(fxmlLoader.load(), sizes[0], sizes[1]);
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/com/spacrod/servidorchat/img/icono.png"))));
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Funcion que cierra la ventana
     * @param actionEvent El evento que nos permite obtener la stage para cerrar la ventana
     */
    public static void closeWindow(ActionEvent actionEvent) {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }
}
