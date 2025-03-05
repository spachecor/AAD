module com.spachecor.ejerciciofinalsgb {
    requires javafx.controls;
    requires javafx.fxml;
    requires basex;
    requires java.sql;
    requires java.desktop;
    requires xstream;


    opens com.spachecor.ejerciciofinalsgb to javafx.fxml;
    exports com.spachecor.ejerciciofinalsgb;
    exports com.spachecor.ejerciciofinalsgb.controller;
    opens com.spachecor.ejerciciofinalsgb.controller to javafx.fxml;
    opens com.spachecor.ejerciciofinalsgb.model.entity to javafx.base, javafx.fxml;
}