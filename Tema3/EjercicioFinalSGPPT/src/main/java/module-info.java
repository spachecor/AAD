module com.spacrod.ejerciciofinalsgppt {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.spacrod.ejerciciofinalsgppt to javafx.fxml;
    exports com.spacrod.ejerciciofinalsgppt;
    exports com.spacrod.ejerciciofinalsgppt.controllers;
    opens com.spacrod.ejerciciofinalsgppt.controllers to javafx.fxml;
}