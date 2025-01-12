package com.spachecor.ejerciciofinalsgecn.controller.gestion;

import com.spachecor.ejerciciofinalsgecn.controller.service.FXService;
import com.spachecor.ejerciciofinalsgecn.controller.service.tablaservice.EntidadRowListener;
import com.spachecor.ejerciciofinalsgecn.controller.service.tablaservice.TableRecargable;
import com.spachecor.ejerciciofinalsgecn.model.entity.Nota;
import com.spachecor.ejerciciofinalsgecn.model.row.NotaRow;
import com.spachecor.ejerciciofinalsgecn.model.service.repository.GenericRepositoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestionarNotasController implements TableRecargable, EntidadRowListener<Nota> {
    @FXML
    private TableView<NotaRow> tablaCursosTableView;
    @FXML
    private Label errorLabel;
    private GenericRepositoryService<Nota> genericRepositoryService;
    @FXML
    protected void initialize() {
        genericRepositoryService = new GenericRepositoryService<>(Nota.class);
        this.inicializarColumnas();
        recargar();
    }
    @FXML
    private void onClickNuevoButton(){
        FXService.cambiarVentana(FXService.INSERTAR_NOTA);
    }
    @FXML
    private void onClickSalirButton() {
        FXService.cambiarVentana(FXService.MAIN);
    }
    private ObservableList<NotaRow> getObservableListToCursos(){
        List<Nota> notas = genericRepositoryService.listar();
        List<NotaRow> notaRowList = new ArrayList<>();
        for (Nota nota : notas) {
            notaRowList.add(new NotaRow(nota, this));
        }
        return FXCollections.observableList(notaRowList);
    }
    private void inicializarColumnas(){
        TableColumn<NotaRow, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setMinWidth(75);
        this.centrarCentroContenidoComumna(idColumn);
        TableColumn<NotaRow, Double> valorColumn = new TableColumn<>("VALOR");
        valorColumn.setCellValueFactory(new PropertyValueFactory<>("valor"));
        valorColumn.setMinWidth(75);
        this.centrarCentroContenidoComumna(valorColumn);
        TableColumn<NotaRow, LocalDate> fechaColumn = new TableColumn<>("FECHA");
        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        fechaColumn.setMinWidth(150);
        this.centrarCentroContenidoComumna(fechaColumn);
        TableColumn<NotaRow, Integer> idCursoColumn = new TableColumn<>("ID CURSO");
        idCursoColumn.setCellValueFactory(new PropertyValueFactory<>("idCurso"));
        idCursoColumn.setMinWidth(75);
        this.centrarCentroContenidoComumna(idCursoColumn);
        TableColumn<NotaRow, String> nombreCursoColumn = new TableColumn<>("NOMBRE DEL CURSO");
        nombreCursoColumn.setCellValueFactory(new PropertyValueFactory<>("nombreCurso"));
        nombreCursoColumn.setMinWidth(300);
        this.centrarIzqContenidoComumna(nombreCursoColumn);
        TableColumn<NotaRow, Integer> idEstudianteColumn = new TableColumn<>("ID ESTUDIANTE");
        idEstudianteColumn.setCellValueFactory(new PropertyValueFactory<>("idEstudiante"));
        idEstudianteColumn.setMinWidth(75);
        this.centrarCentroContenidoComumna(idEstudianteColumn);
        TableColumn<NotaRow, String> nombreEstudianteColumn = new TableColumn<>("NOMBRE DEL ESTUDIANTE");
        nombreEstudianteColumn.setCellValueFactory(new PropertyValueFactory<>("nombreEstudiante"));
        nombreEstudianteColumn.setMinWidth(300);
        this.centrarIzqContenidoComumna(nombreEstudianteColumn);
        TableColumn<NotaRow, Button> modificarTableColumn = new TableColumn<>("MODIFICAR");
        modificarTableColumn.setCellValueFactory(new PropertyValueFactory<>("modificar"));
        modificarTableColumn.setMinWidth(75);
        this.centrarCentroContenidoComumna(modificarTableColumn);
        TableColumn<NotaRow, Button> eliminarTableColumn = new TableColumn<>("ELIMINAR");
        eliminarTableColumn.setCellValueFactory(new PropertyValueFactory<>("eliminar"));
        eliminarTableColumn.setMinWidth(75);
        this.centrarCentroContenidoComumna(eliminarTableColumn);
        this.tablaCursosTableView.getColumns().addAll(idColumn, valorColumn, fechaColumn, idCursoColumn, nombreCursoColumn, idEstudianteColumn, nombreEstudianteColumn, modificarTableColumn, eliminarTableColumn);
    }
    private <T> void centrarCentroContenidoComumna(TableColumn<NotaRow, T> column){
        this.centrarContenidoColumna(column, "-fx-alignment: CENTER;");
    }
    private <T> void centrarIzqContenidoComumna(TableColumn<NotaRow, T> column){
        this.centrarContenidoColumna(column, "-fx-alignment: CENTER-LEFT;");
    }
    private <T> void centrarContenidoColumna(TableColumn<NotaRow, T> column, String style) {
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
    public void eliminarEntidad(Nota nota) {
        try{
            this.genericRepositoryService.eliminar(nota);
        }catch (org.hibernate.exception.ConstraintViolationException e){
            errorLabel.setText("No puedes eliminar un curso si tiene alumnos inscritos.");
            System.err.println("Se ha intentado eliminar un curso con alumnos inscritos. Proceso abortado.");
        }
        this.recargar();
    }
}
