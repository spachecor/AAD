package com.spachecor.ejerciciofinalsgecn.controller.consulta;

import com.spachecor.ejerciciofinalsgecn.controller.service.FXService;
import com.spachecor.ejerciciofinalsgecn.model.entity.Curso;
import com.spachecor.ejerciciofinalsgecn.model.entity.Estudiante;
import com.spachecor.ejerciciofinalsgecn.model.entity.Nota;
import com.spachecor.ejerciciofinalsgecn.model.row.CursoRow;
import com.spachecor.ejerciciofinalsgecn.model.row.NotaEstudianteCursoRow;
import com.spachecor.ejerciciofinalsgecn.model.service.repository.GenericRepositoryService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class ConsultaNotasEstudianteCursoController {
    @FXML
    private ChoiceBox<Curso> cursoChoiceBox;
    @FXML
    private ChoiceBox<Estudiante> estudianteChoiceBox;
    @FXML
    private TableView<NotaEstudianteCursoRow> tablaTableView;
    GenericRepositoryService<Curso> cursoGenericRepositoryService;
    GenericRepositoryService<Estudiante> estudianteGenericRepositoryService;
    @FXML
    protected void initialize() {
        this.inicializarColumnas();
        this.cursoGenericRepositoryService = new GenericRepositoryService<>(Curso.class);
        this.estudianteGenericRepositoryService = new GenericRepositoryService<>(Estudiante.class);
        this.cursoChoiceBox.getItems().addAll(cursoGenericRepositoryService.listar());
        this.cursoChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.estudianteChoiceBox.getItems().clear();
            this.estudianteChoiceBox.getItems().addAll(newValue.getEstudiantes());
        });
        this.estudianteChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                List<NotaEstudianteCursoRow> notaEstudianteCursoRows = new ArrayList<>();
                if(estudianteChoiceBox.getValue().getNotas().isEmpty()){
                    notaEstudianteCursoRows.add(new NotaEstudianteCursoRow(newValue, this.cursoChoiceBox.getValue(), new Nota()));
                }else{
                    for(Nota nota: newValue.getNotas()) {
                        if(nota.getCurso().equals(this.cursoChoiceBox.getValue())) {
                            notaEstudianteCursoRows.add(new NotaEstudianteCursoRow(newValue, this.cursoChoiceBox.getValue(), nota));
                        }
                    }
                }
                this.tablaTableView.getItems().clear();
                this.tablaTableView.getItems().addAll(notaEstudianteCursoRows);
            }
        });
    }
    @FXML
    private void onClickVolverButton(){
        FXService.cambiarVentana(FXService.MAIN);
    }
    private void inicializarColumnas(){
        TableColumn<NotaEstudianteCursoRow, Integer> idEstudianteColumn = new TableColumn<>("ID ESTUDIANTE");
        idEstudianteColumn.setCellValueFactory(new PropertyValueFactory<>("idEstudiante"));
        idEstudianteColumn.setMinWidth(125);
        this.centrarCentroContenidoComumna(idEstudianteColumn);
        TableColumn<NotaEstudianteCursoRow, String> nombreEstudianteColumn = new TableColumn<>("NOMBRE ESTUDIANTE");
        nombreEstudianteColumn.setCellValueFactory(new PropertyValueFactory<>("nombreEstudiante"));
        nombreEstudianteColumn.setMinWidth(250);
        this.centrarIzqContenidoComumna(nombreEstudianteColumn);
        TableColumn<NotaEstudianteCursoRow, Integer> idCursoColumn = new TableColumn<>("ID CURSO");
        idCursoColumn.setCellValueFactory(new PropertyValueFactory<>("idCurso"));
        idCursoColumn.setMinWidth(75);
        this.centrarCentroContenidoComumna(idCursoColumn);
        TableColumn<NotaEstudianteCursoRow, String> nombreCursoColumn = new TableColumn<>("NOMBRE CURSO");
        nombreCursoColumn.setCellValueFactory(new PropertyValueFactory<>("nombreCurso"));
        nombreCursoColumn.setMinWidth(250);
        this.centrarIzqContenidoComumna(nombreCursoColumn);
        TableColumn<NotaEstudianteCursoRow, Double> notaColumn = new TableColumn<>("NOTA");
        notaColumn.setCellValueFactory(new PropertyValueFactory<>("nota"));
        notaColumn.setMinWidth(75);
        this.centrarCentroContenidoComumna(notaColumn);
        this.tablaTableView.getColumns().addAll(idEstudianteColumn, nombreEstudianteColumn, idCursoColumn, nombreCursoColumn, notaColumn);
    }
    private <T> void centrarCentroContenidoComumna(TableColumn<NotaEstudianteCursoRow, T> column){
        this.centrarContenidoColumna(column, "-fx-alignment: CENTER;");
    }
    private <T> void centrarIzqContenidoComumna(TableColumn<NotaEstudianteCursoRow, T> column){
        this.centrarContenidoColumna(column, "-fx-alignment: CENTER-LEFT;");
    }
    private <T> void centrarContenidoColumna(TableColumn<NotaEstudianteCursoRow, T> column, String style) {
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
}
