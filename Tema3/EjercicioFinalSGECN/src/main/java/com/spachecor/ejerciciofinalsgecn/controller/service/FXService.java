package com.spachecor.ejerciciofinalsgecn.controller.service;

import com.spachecor.ejerciciofinalsgecn.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Objects;

/**
 * Clase FXService, que se encarga de apoyar a los controladores en la parte visual con acciones como gestionar el
 * cambio de ventanas, etc.
 * @author Selene
 * @version 1.0
 */
public class FXService {
    public static final String MAIN;
    public static final String GESTIONAR_CURSOS;
    public static final String GESTIONAR_ESTUDIANTES;
    public static final String GESTIONAR_NOTAS;
    static{
        MAIN = "main-view.fxml";
        GESTIONAR_CURSOS = "gestionar-cursos-view.fxml";
        GESTIONAR_ESTUDIANTES = "gestionar-estudiantes-view.fxml";
        GESTIONAR_NOTAS = "gestionar-notas-view.fxml";
    }

    /**
     * Método que se encarga de cambiar las ventanas según el nombre de la vista que le pasemos.
     * @param ventana El nombre del fichero .fxml que le pasamos.
     */
    public static void cambiarVentana(String ventana){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/"+ventana));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
            Main.stage.setTitle("EduTrack - Gestión de Cursos, Estudiantes y Notas");
            Main.stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/com/spachecor/ejerciciofinalsgecn/img/edutrack-icon.png"))));
            Main.stage.setScene(scene);
            Main.stage.show();
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("No se puede cambiar la ventana: "+e.getMessage());
            System.exit(1);
        }
    }
}
