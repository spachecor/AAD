module com.spachecor.gestorbiblioteca.gestorbiblioteca {
    requires javafx.controls;
    requires javafx.fxml;
    requires basex;
    requires java.sql;


    opens com.spachecor.gestorbiblioteca to javafx.fxml;
    exports com.spachecor.gestorbiblioteca;
    exports com.spachecor.gestorbiblioteca.controller;
    opens com.spachecor.gestorbiblioteca.controller to javafx.fxml;
}