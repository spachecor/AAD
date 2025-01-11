package com.spachecor.ejerciciofinalsgecn.controller;

import com.spachecor.ejerciciofinalsgecn.controller.service.tablaservice.EntidadRowListener;
import com.spachecor.ejerciciofinalsgecn.controller.service.FXService;
import com.spachecor.ejerciciofinalsgecn.controller.service.tablaservice.TableRecargable;
import com.spachecor.ejerciciofinalsgecn.model.entity.Curso;
import com.spachecor.ejerciciofinalsgecn.model.row.CursoRow;
import com.spachecor.ejerciciofinalsgecn.model.service.repository.GenericRepositoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class GestionarCursosController implements TableRecargable, EntidadRowListener<Curso> {
    @FXML
    private TableView<CursoRow> tablaCursosTableView;
    @FXML
    private Label errorLabel;
    private GenericRepositoryService<Curso> genericRepositoryService;
    @FXML
    protected void initialize() {
        genericRepositoryService = new GenericRepositoryService<>(Curso.class);
        this.inicializarColumnas();
        recargar();
    }
    @FXML
    private void onClickSalirButton() {
        FXService.cambiarVentana(FXService.MAIN);
    }
    private ObservableList<CursoRow> getObservableListToCursos(){
        List<Curso> cursos = genericRepositoryService.listar();
        List<CursoRow> cursoRowList = new ArrayList<>();
        for (Curso curso : cursos) {
            cursoRowList.add(new CursoRow(curso, this));
        }
        return FXCollections.observableList(cursoRowList);
    }
    private void inicializarColumnas(){
        TableColumn<CursoRow, Integer> idTableColumn = new TableColumn<>("ID");
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idTableColumn.setMinWidth(75);
        this.centrarCentroContenidoComumna(idTableColumn);
        TableColumn<CursoRow, String> nombreTableColumn = new TableColumn<>("NOMBRE");
        nombreTableColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        nombreTableColumn.setMinWidth(200);
        this.centrarIzqContenidoComumna(nombreTableColumn);
        TableColumn<CursoRow, String> descripcionTableColumn = new TableColumn<>("DESCRIPCIÓN");
        descripcionTableColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        descripcionTableColumn.setMinWidth(655);
        this.centrarIzqContenidoComumna(descripcionTableColumn);
        TableColumn<CursoRow, Integer> nEstudiantesTableColumn = new TableColumn<>("Nº ESTUDIANTES");
        nEstudiantesTableColumn.setCellValueFactory(new PropertyValueFactory<>("nEstudiantes"));
        nEstudiantesTableColumn.setMinWidth(150);
        this.centrarCentroContenidoComumna(nEstudiantesTableColumn);
        TableColumn<CursoRow, Button> modificarTableColumn = new TableColumn<>("MODIFICAR");
        modificarTableColumn.setCellValueFactory(new PropertyValueFactory<>("modificar"));
        modificarTableColumn.setMinWidth(75);
        this.centrarCentroContenidoComumna(modificarTableColumn);
        TableColumn<CursoRow, Button> eliminarTableColumn = new TableColumn<>("ELIMINAR");
        eliminarTableColumn.setCellValueFactory(new PropertyValueFactory<>("eliminar"));
        eliminarTableColumn.setMinWidth(75);
        this.centrarCentroContenidoComumna(eliminarTableColumn);
        this.tablaCursosTableView.getColumns().addAll(idTableColumn, nombreTableColumn, descripcionTableColumn, nEstudiantesTableColumn, modificarTableColumn, eliminarTableColumn);
    }
    private <T> void centrarCentroContenidoComumna(TableColumn<CursoRow, T> column){
        this.centrarContenidoColumna(column, "-fx-alignment: CENTER;");
    }
    private <T> void centrarIzqContenidoComumna(TableColumn<CursoRow, T> column){
        this.centrarContenidoColumna(column, "-fx-alignment: CENTER-LEFT;");
    }
    private <T> void centrarContenidoColumna(TableColumn<CursoRow, T> column, String style) {
        column.setCellFactory(_ -> new TableCell<>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    if (item instanceof Button) {
                        setGraphic((Button) item);
                        setText(null);
                    } else {
                        setText(item.toString());
                        setGraphic(null);
                    }
                    setStyle(style);
                }
            }
        });
    }
    @Override
    public void recargar() {
        this.tablaCursosTableView.getItems().clear();
        this.tablaCursosTableView.setItems(this.getObservableListToCursos());
    }
    @Override
    public void eliminarEntidad(Curso curso) {
        try{
            this.genericRepositoryService.eliminar(curso);
        }catch (org.hibernate.exception.ConstraintViolationException e){
            errorLabel.setText("No puedes eliminar un curso si tiene alumnos inscritos.");
            System.err.println("Se ha intentado eliminar un curso con alumnos inscritos. Proceso abortado.");
        }
        this.recargar();
    }
}
