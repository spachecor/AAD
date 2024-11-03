module com.spachecor.proyectoproductos {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.xml.bind;
    requires com.google.gson;


    opens com.spachecor.proyectoproductos;
    opens com.spachecor.proyectoproductos.model.entity;
    opens com.spachecor.proyectoproductos.model.conversores;
    opens com.spachecor.proyectoproductos.model.repository;
    opens com.spachecor.proyectoproductos.model.util;
    exports com.spachecor.proyectoproductos;
    exports com.spachecor.proyectoproductos.controller;
    opens com.spachecor.proyectoproductos.controller;
}