module com.spachecor.ejemplotablabotones {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.spachecor.ejemplotablabotones to javafx.fxml;
    exports com.spachecor.ejemplotablabotones;
}