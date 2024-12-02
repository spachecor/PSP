module com.spacrod.servidorchar {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.spacrod.servidorchat to javafx.fxml;
    exports com.spacrod.servidorchat;
    exports com.spacrod.servidorchat.controllers;
    opens com.spacrod.servidorchat.controllers to javafx.fxml;
}