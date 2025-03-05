package com.spachecor.ejerciciofinalsgb.controller.service;

import com.spachecor.ejerciciofinalsgb.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Objects;

public class FXService {
    public static final String MENU_VIEW;
    public static final String LIBROS_VIEW;
    public static final String USUARIOS_VIEW;
    public static final String PRESTAMOS_VIEW;
    public static final String COLECCIONES_VIEW;
    public static final String DOCUMENTOS_VIEW;
    static {
        MENU_VIEW = "menu-view.fxml";
        LIBROS_VIEW = "libros-view.fxml";
        USUARIOS_VIEW = "usuarios-view.fxml";
        PRESTAMOS_VIEW = "prestamos-view.fxml";
        COLECCIONES_VIEW = "colecciones-view.fxml";
        DOCUMENTOS_VIEW = "documentos-view.fxml";
    }
    /**
     * Método que se encarga de cambiar las ventanas según el nombre de la vista que le pasemos.
     * @param ventana El nombre del fichero .fxml que le pasamos.
     */
    public static void cambiarVentana(String ventana){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/"+ventana));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
            Main.stage.setTitle("Gestión de Biblioteca");
            Main.stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/com/spachecor/ejerciciofinalsgb/img/icon.png"))));
            Main.stage.setScene(scene);
            Main.stage.show();
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("No se puede cambiar la ventana: "+e.getMessage());
            System.exit(1);
        }
    }
}
