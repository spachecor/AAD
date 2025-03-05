package com.spachecor.ejerciciofinalsgb;

import com.spachecor.ejerciciofinalsgb.controller.service.FXService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Stage stage;
    @Override
    public void start(Stage stage){
        Main.stage = stage;
        FXService.cambiarVentana(FXService.MENU_VIEW);
    }

    public static void main(String[] args) {
        launch();
    }
}