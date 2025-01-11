module com.spachecor.ejerciciofinalsgecn {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;


    opens com.spachecor.ejerciciofinalsgecn;
    exports com.spachecor.ejerciciofinalsgecn;
    exports com.spachecor.ejerciciofinalsgecn.controller;
    opens com.spachecor.ejerciciofinalsgecn.controller;
    exports com.spachecor.ejerciciofinalsgecn.model.entity;
    opens com.spachecor.ejerciciofinalsgecn.model.entity;
    exports com.spachecor.ejerciciofinalsgecn.model.row;
    opens com.spachecor.ejerciciofinalsgecn.model.row;
}