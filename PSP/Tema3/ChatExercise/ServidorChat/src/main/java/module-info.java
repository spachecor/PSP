module com.spacrod.servidorchat {
    requires javafx.controls;
    requires javafx.fxml;


    exports com.spacrod.servidorchat;
    opens com.spacrod.servidorchat to javafx.fxml;
    exports com.spacrod.servidorchat.controllers;
    opens com.spacrod.servidorchat.controllers to javafx.fxml;
}