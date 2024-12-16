package com.spacrod.clientechat.services;

import com.spacrod.clientechat.Main;
import com.spacrod.clientechat.client.ClientHandler;
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
    private static ClientHandler clientHandler;
    public static final String CONFIGURACION_COMUNICACION_VIEW = "configuracion-comunicacion.fxml";
    public static final String CONFIGURACION_COMUNICACION_TITLE = "Configuraciones de la comunicacion";
    public static final Integer[] CONFIGURACION_COMUNICACION_SIZES = new Integer[]{600, 400};
    public static final String MAIN_VIEW = "main-view.fxml";
    public static final String MAIN_TITLE = "Cliente Chat";
    public static final Integer[] MAIN_SIZES = new Integer[]{480, 640};
    private static FXMLLoader fxmlLoader;

    /**
     * Funcion que se encarga de cargar una nueva ventana
     * @param stage La stage de la nueva ventana
     * @param view El nombre del archivo fxml
     * @param title El titulo de la ventana
     * @param sizes Las dimensiones de la ventana
     * @throws IOException La excepcion que puede saltar
     */
    public static void loadNewWindow(Stage stage, String view, String title, Integer[] sizes) throws IOException {
        FXService.fxmlLoader = new FXMLLoader(Main.class.getResource("views/"+view));
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/com/spacrod/clientechat/img/icono.png"))));
        Scene scene = new Scene(fxmlLoader.load(), sizes[0], sizes[1]);
        if (FXService.clientHandler == null) {
            stage.setTitle(title);
        }else{
            stage.setTitle(title+" - "+clientHandler.getName());
        }
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

    public static ClientHandler getClientHandler() {
        return clientHandler;
    }

    public static void setClientHandler(ClientHandler clientHandler) {
        FXService.clientHandler = clientHandler;
    }
}
