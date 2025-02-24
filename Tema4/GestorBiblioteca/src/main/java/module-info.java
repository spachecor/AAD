module com.spachecor.gestorbiblioteca.gestorbiblioteca {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.spachecor.gestorbiblioteca.gestorbiblioteca to javafx.fxml;
    exports com.spachecor.gestorbiblioteca.gestorbiblioteca;
}