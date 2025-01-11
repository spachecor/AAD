package com.spachecor.ejerciciofinalsgecn;

import com.spachecor.ejerciciofinalsgecn.controller.service.FXService;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage stage;
    @Override
    public void start(Stage stage){
        Main.stage = stage;
        FXService.cambiarVentana(FXService.MAIN);
    }

    public static void main(String[] args) {
        launch();
    }
}