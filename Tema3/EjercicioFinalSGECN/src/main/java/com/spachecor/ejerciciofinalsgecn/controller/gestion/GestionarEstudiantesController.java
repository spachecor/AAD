package com.spachecor.ejerciciofinalsgecn.controller.gestion;

import com.spachecor.ejerciciofinalsgecn.controller.service.tablaservice.EntidadRowListener;
import com.spachecor.ejerciciofinalsgecn.controller.service.FXService;
import com.spachecor.ejerciciofinalsgecn.controller.service.tablaservice.TableRecargable;
import com.spachecor.ejerciciofinalsgecn.model.entity.Estudiante;
import com.spachecor.ejerciciofinalsgecn.model.row.EstudianteRow;
import com.spachecor.ejerciciofinalsgecn.model.service.repository.GenericRepositoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class GestionarEstudiantesController implements TableRecargable, EntidadRowListener<Estudiante> {
    @FXML
    private TableView<EstudianteRow> tablaCursosTableView;
    @FXML
    private Label errorLabel;
    private GenericRepositoryService<Estudiante> genericRepositoryService;
    @FXML
    protected void initialize() {
        genericRepositoryService = new GenericRepositoryService<>(Estudiante.class);
        this.inicializarColumnas();
        recargar();
    }
    @FXML
    private void onClickSalirButton() {
        FXService.cambiarVentana(FXService.MAIN);
    }
    @FXML
    private void onClickNuevoButton(){
        FXService.cambiarVentana(FXService.INSERTAR_ESTUDIANTE);
    }
    private ObservableList<EstudianteRow> getObservableListToCursos(){
        List<Estudiante> estudiantes = genericRepositoryService.listar();
        List<EstudianteRow> estudianteRowList = new ArrayList<>();
        for (Estudiante estudiante : estudiantes) {
            estudianteRowList.add(new EstudianteRow(estudiante, this));
        }
        return FXCollections.observableList(estudianteRowList);
    }
    private void inicializarColumnas(){
        TableColumn<EstudianteRow, Integer> idTableColumn = new TableColumn<>("ID");
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idTableColumn.setMinWidth(75);
        this.centrarCentroContenidoComumna(idTableColumn);
        TableColumn<EstudianteRow, String> nombreColumn = new TableColumn<>("NOMBRE");
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        nombreColumn.setMinWidth(260);
        this.centrarIzqContenidoComumna(nombreColumn);
        TableColumn<EstudianteRow, String> apellidoColumn = new TableColumn<>("APELLIDO");
        apellidoColumn.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        apellidoColumn.setMinWidth(330);
        this.centrarIzqContenidoComumna(apellidoColumn);
        TableColumn<EstudianteRow, String> emailColumn = new TableColumn<>("EMAIL");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumn.setMinWidth(330);
        this.centrarIzqContenidoComumna(emailColumn);
        TableColumn<EstudianteRow, Integer> nCursosEstudianteColumn = new TableColumn<>("Nº CURSOS");
        nCursosEstudianteColumn.setCellValueFactory(new PropertyValueFactory<>("nCursosInscrito"));
        nCursosEstudianteColumn.setMinWidth(75);
        this.centrarCentroContenidoComumna(nCursosEstudianteColumn);
        TableColumn<EstudianteRow, Button> modificarTableColumn = new TableColumn<>("MODIFICAR");
        modificarTableColumn.setCellValueFactory(new PropertyValueFactory<>("modificar"));
        modificarTableColumn.setMinWidth(75);
        this.centrarCentroContenidoComumna(modificarTableColumn);
        TableColumn<EstudianteRow, Button> eliminarTableColumn = new TableColumn<>("ELIMINAR");
        eliminarTableColumn.setCellValueFactory(new PropertyValueFactory<>("eliminar"));
        eliminarTableColumn.setMinWidth(75);
        this.centrarCentroContenidoComumna(eliminarTableColumn);
        this.tablaCursosTableView.getColumns().addAll(idTableColumn, nombreColumn, apellidoColumn, emailColumn, nCursosEstudianteColumn, modificarTableColumn, eliminarTableColumn);
    }
    private <T> void centrarCentroContenidoComumna(TableColumn<EstudianteRow, T> column){
        this.centrarContenidoColumna(column, "-fx-alignment: CENTER;");
    }
    private <T> void centrarIzqContenidoComumna(TableColumn<EstudianteRow, T> column){
        this.centrarContenidoColumna(column, "-fx-alignment: CENTER-LEFT;");
    }
    private <T> void centrarContenidoColumna(TableColumn<EstudianteRow, T> column, String style) {
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
    public void eliminarEntidad(Estudiante estudiante) {
        try{
            this.genericRepositoryService.eliminar(estudiante);
        }catch (org.hibernate.exception.ConstraintViolationException e){
            errorLabel.setText("No puedes eliminar un curso si tiene alumnos inscritos.");
            System.err.println("Se ha intentado eliminar un curso con alumnos inscritos. Proceso abortado.");
        }
        this.recargar();
    }
}
