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
/*
* TODO CUIDAO, SEGUIR POR DONDE LO DEJASTE, EN CLIENTE, AHORA ESTAMOS CREANDO UNA LISTA DE CLIENTES EN VEZ DE UNA LISTA
*  DE SOCKETS. HAY QUE ELIMINAR LA LISTA DE SOCKETS, QUE SERÁ UNA LISTA DE CLIENTES, CNON SU ID, NAME Y SOCKET RELACIONADO.
*   DEBE SER TAL QUE UN CLIENTE SE CONECTA, COMPARTE ID Y NOMBRE Y YA SE ACTIVA EL ENVIO Y RECEPCION DE MENSAJES.
*   Revisar esto, está raro y lo mismo hay que modificarlo
*   +++++ OTRO PUNTO +++++
*   Ojo cuidao, deja que tó se haga con una misma stage, solo inicia nuevas stages para las alertas. Plantea eliminar las alertas
* */
    public static void main(String[] args) {
        launch();
    }
}