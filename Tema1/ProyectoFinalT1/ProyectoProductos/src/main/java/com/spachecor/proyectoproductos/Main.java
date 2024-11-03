package com.spachecor.proyectoproductos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Clase que es la entrada principal del programa y lanza la interfaz visual.
 * @author Selene
 * @version 1.0
 */
public class Main extends Application {
    public static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        Main.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/productos.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Productos");
        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("img/icono.png"))));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}