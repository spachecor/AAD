package com.spachecor.gestorbiblioteca;

import com.spachecor.gestorbiblioteca.controller.service.FXService;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage stage;
    @Override
    public void start(Stage stage){
        Main.stage = stage;
        FXService.cambiarVentana(FXService.MENU_VIEW);
    }

    public static void main() {
        launch();
    }
}