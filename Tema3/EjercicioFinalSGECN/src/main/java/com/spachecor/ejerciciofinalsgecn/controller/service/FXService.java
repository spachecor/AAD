package com.spachecor.ejerciciofinalsgecn.controller.service;

import com.spachecor.ejerciciofinalsgecn.Main;
import com.spachecor.ejerciciofinalsgecn.model.entity.Curso;
import com.spachecor.ejerciciofinalsgecn.model.entity.Entidad;
import com.spachecor.ejerciciofinalsgecn.model.entity.Estudiante;
import com.spachecor.ejerciciofinalsgecn.model.entity.Nota;
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
    public static final String INSERTAR_CURSO;
    public static final String INSERTAR_ESTUDIANTE;
    public static final String INSERTAR_NOTA;
    public static final String CONSULTAR_NOTAS_ESTUDIANTE_CURSO;
    public static final String CONSULTAR_ESTUDIANTES_NOTAS_POR_CURSO;
    private static Curso curso;
    private static Estudiante estudiante;
    private static Nota nota;
    static{
        MAIN = "main-view.fxml";
        GESTIONAR_CURSOS = "gestion/gestionar-cursos-view.fxml";
        GESTIONAR_ESTUDIANTES = "gestion/gestionar-estudiantes-view.fxml";
        GESTIONAR_NOTAS = "gestion/gestionar-notas-view.fxml";
        INSERTAR_CURSO = "insercion/insertar-curso-view.fxml";
        INSERTAR_ESTUDIANTE = "insercion/insertar-estudiante-view.fxml";
        INSERTAR_NOTA = "insercion/insertar-nota-view.fxml";
        CONSULTAR_NOTAS_ESTUDIANTE_CURSO = "consulta/consulta-notas-estudiante-curso-view.fxml";
        CONSULTAR_ESTUDIANTES_NOTAS_POR_CURSO = "consulta/consulta-estudiantes-notas-por-curso-view.fxml";
        curso = null;
        estudiante = null;
        nota = null;
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

    /**
     * Método que se encarga de cambiar las ventanas según el nombre de la vista que le pasemos. También se encarga de
     * definir una entidad. Esta variante del método se usa para modificar entidades
     * @param ventana El nombre del fichero .fxml que le pasamos.
     * @param t La entidad a modificar
     * @param <T> Entidad que herede de la clase Entidad
     */
    public static <T extends Entidad> void cambiarVentana(String ventana, T t){
        if(t instanceof Curso){
            FXService.setCurso((Curso) t);
        }else if(t instanceof Estudiante){
            FXService.setEstudiante((Estudiante) t);
        }else if(t instanceof Nota){
            FXService.setNota((Nota) t);
        }
        FXService.cambiarVentana(ventana);
    }

    public static Curso getCurso() {
        return curso;
    }

    public static void setCurso(Curso curso) {
        FXService.curso = curso;
    }

    public static Estudiante getEstudiante() {
        return estudiante;
    }

    public static void setEstudiante(Estudiante estudiante) {
        FXService.estudiante = estudiante;
    }

    public static Nota getNota() {
        return nota;
    }

    public static void setNota(Nota nota) {
        FXService.nota = nota;
    }
}
