module com.spachecor.ejerciciofinalsgecn {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;


    opens com.spachecor.ejerciciofinalsgecn to javafx.fxml;
    exports com.spachecor.ejerciciofinalsgecn;
    exports com.spachecor.ejerciciofinalsgecn.controller;
    opens com.spachecor.ejerciciofinalsgecn.controller to javafx.fxml;
}