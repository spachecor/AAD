package com.spachecor.ejerciciofinalsgecn.model.row;

import com.spachecor.ejerciciofinalsgecn.Main;
import com.spachecor.ejerciciofinalsgecn.controller.service.FXService;
import com.spachecor.ejerciciofinalsgecn.controller.service.tablaservice.EntidadRowListener;
import com.spachecor.ejerciciofinalsgecn.model.entity.Curso;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * Clase CursoRow, que define como se representan los cursos en las tablas
 * @author Selene
 * @version 1.0
 */
public class CursoRow {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer nEstudiantes;
    private Button modificar;
    private Button eliminar;
    public CursoRow(Curso curso, EntidadRowListener<Curso> entidadRowListener) {
        this.id = curso.getId();
        this.nombre = curso.getNombre();
        this.descripcion = curso.getDescripcion();
        this.nEstudiantes = curso.getEstudiantes().size();
        this.modificar = new Button();
        this.modificar.setOnAction(_ ->{
            FXService.cambiarVentana(FXService.INSERTAR_CURSO, curso);
        });
        this.setIcon(this.modificar, new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/com/spachecor/ejerciciofinalsgecn/img/modificar-icon.png"))));
        this.eliminar = new Button();
        this.eliminar.setOnAction(_ -> {
            entidadRowListener.eliminarEntidad(curso);
        });
        this.setIcon(this.eliminar, new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/com/spachecor/ejerciciofinalsgecn/img/eliminar-icon.png"))));
    }

    /**
     * Método que fija un icono a un botón
     * @param button El botón al que agregar el icono
     * @param image El icono a agregar
     */
    private void setIcon(Button button, Image image){
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        button.setGraphic(imageView);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getNEstudiantes() {
        return nEstudiantes;
    }

    public void setNEstudiantes(Integer nEstudiantes) {
        this.nEstudiantes = nEstudiantes;
    }

    public Button getModificar() {
        return modificar;
    }

    public void setModificar(Button modificar) {
        this.modificar = modificar;
    }

    public Button getEliminar() {
        return eliminar;
    }

    public void setEliminar(Button eliminar) {
        this.eliminar = eliminar;
    }
}
