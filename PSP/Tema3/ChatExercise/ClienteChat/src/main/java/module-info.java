module com.spacrod.clientechat {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.spacrod.clientechat to javafx.fxml;
    exports com.spacrod.clientechat;
    exports com.spacrod.clientechat.controllers;
    opens com.spacrod.clientechat.controllers to javafx.fxml;
    exports com.spacrod.clientechat.client;
    opens com.spacrod.clientechat.client to javafx.fxml;
}