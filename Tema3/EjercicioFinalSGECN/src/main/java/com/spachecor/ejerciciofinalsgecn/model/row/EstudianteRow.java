package com.spachecor.ejerciciofinalsgecn.model.row;

import com.spachecor.ejerciciofinalsgecn.Main;
import com.spachecor.ejerciciofinalsgecn.controller.service.tablaservice.EntidadRowListener;
import com.spachecor.ejerciciofinalsgecn.model.entity.Estudiante;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class EstudianteRow {
    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    private Integer nCursosInscrito;
    private Button modificar;
    private Button eliminar;
    public EstudianteRow(Estudiante estudiante, EntidadRowListener<Estudiante> entidadRowListener) {
        this.id = estudiante.getId();
        this.nombre = estudiante.getNombre();
        this.apellido = estudiante.getApellido();
        this.email = estudiante.getEmail();
        this.nCursosInscrito = estudiante.getCursos().size();
        this.modificar = new Button();
        this.setIcon(this.modificar, new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/com/spachecor/ejerciciofinalsgecn/img/modificar-icon.png"))));
        this.eliminar = new Button();
        this.eliminar.setOnAction(_ -> {
            entidadRowListener.eliminarEntidad(estudiante);
        });
        this.setIcon(this.eliminar, new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/com/spachecor/ejerciciofinalsgecn/img/eliminar-icon.png"))));
    }
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNCursosInscrito() {
        return nCursosInscrito;
    }

    public void setNCursosInscrito(Integer nCursosInscrito) {
        this.nCursosInscrito = nCursosInscrito;
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
