package com.spachecor.ejerciciofinalsgecn.model.row;

import com.spachecor.ejerciciofinalsgecn.Main;
import com.spachecor.ejerciciofinalsgecn.controller.service.FXService;
import com.spachecor.ejerciciofinalsgecn.controller.service.tablaservice.EntidadRowListener;
import com.spachecor.ejerciciofinalsgecn.model.entity.Nota;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.LocalDate;
import java.util.Objects;

public class NotaRow {
    private Integer id;
    private Double valor;
    private LocalDate fecha;
    private Integer idCurso;
    private String nombreCurso;
    private Integer idEstudiante;
    private String nombreEstudiante;
    private Button modificar;
    private Button eliminar;
    public NotaRow(Nota nota, EntidadRowListener<Nota> entidadRowListener) {
        this.id = nota.getId();
        this.valor = nota.getValor();
        this.fecha = nota.getFecha();
        this.idCurso = nota.getCurso().getId();
        this.nombreCurso = nota.getCurso().getNombre();
        this.idEstudiante = nota.getEstudiante().getId();
        this.nombreEstudiante = nota.getEstudiante().getNombre();
        this.modificar = new Button();
        this.modificar.setOnAction(_ ->{
            FXService.cambiarVentana(FXService.INSERTAR_NOTA, nota);
        });
        this.setIcon(this.modificar, new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/com/spachecor/ejerciciofinalsgecn/img/modificar-icon.png"))));
        this.eliminar = new Button();
        this.eliminar.setOnAction(_ -> {
            entidadRowListener.eliminarEntidad(nota);
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
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
