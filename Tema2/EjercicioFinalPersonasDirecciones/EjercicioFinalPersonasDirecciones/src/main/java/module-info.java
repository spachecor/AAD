module com.spacrod.ejerciciofinalpersonasdirecciones {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.unsupported.desktop;


    opens com.spacrod.ejerciciofinalpersonasdirecciones to javafx.fxml;
    exports com.spacrod.ejerciciofinalpersonasdirecciones;
    exports com.spacrod.ejerciciofinalpersonasdirecciones.controller;
    opens com.spacrod.ejerciciofinalpersonasdirecciones.controller to javafx.fxml;
}