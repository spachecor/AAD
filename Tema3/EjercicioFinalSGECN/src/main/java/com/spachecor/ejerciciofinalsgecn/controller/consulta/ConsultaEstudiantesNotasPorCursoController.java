package com.spachecor.ejerciciofinalsgecn.controller.consulta;

import com.spachecor.ejerciciofinalsgecn.controller.service.FXService;
import com.spachecor.ejerciciofinalsgecn.controller.service.FontService;
import com.spachecor.ejerciciofinalsgecn.model.entity.Curso;
import com.spachecor.ejerciciofinalsgecn.model.entity.Estudiante;
import com.spachecor.ejerciciofinalsgecn.model.entity.Nota;
import com.spachecor.ejerciciofinalsgecn.model.row.CursoRow;
import com.spachecor.ejerciciofinalsgecn.model.row.EstudianteNotasPorCursoRow;
import com.spachecor.ejerciciofinalsgecn.model.service.repository.GenericRepositoryService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase ConsultaEstudiantesNotasPorCursoController, que es el controlador de la interfaz gráfica con la que el usuario
 * puede consultar las notas de un estudiante en concreto y un curso en específico.
 * @author Selene
 * @version 1.0
 */
public class ConsultaEstudiantesNotasPorCursoController {
    @FXML
    private Label cursoLabel;
    @FXML
    private ChoiceBox<Curso> cursoChoiceBox;
    @FXML
    private Button volverButton;
    @FXML
    private TableView<EstudianteNotasPorCursoRow> tablaTableView;
    @FXML
    protected void initialize() {
        FontService.setFont(
                FontService.NORMAL_FONT,
                this.cursoLabel,
                this.cursoChoiceBox,
                this.volverButton
        );
        this.inicializarColumnas();
        GenericRepositoryService<Curso> cursoGenericRepositoryService = new GenericRepositoryService<>(Curso.class);
        this.cursoChoiceBox.getItems().addAll(cursoGenericRepositoryService.listar());
        this.cursoChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            List<EstudianteNotasPorCursoRow> estudianteNotasPorCursoRows = new ArrayList<>();
            for(Estudiante estudiante: newValue.getEstudiantes()){
                if(estudiante.getNotas().isEmpty()){
                    estudianteNotasPorCursoRows.add(new EstudianteNotasPorCursoRow(estudiante, new Nota()));
                }else{
                    for(Nota nota: estudiante.getNotas()){
                        if(nota.getCurso().equals(newValue)){
                            estudianteNotasPorCursoRows.add(new EstudianteNotasPorCursoRow(estudiante, nota));
                        }
                    }
                }
            }
            this.tablaTableView.getItems().clear();
            this.tablaTableView.getItems().addAll(estudianteNotasPorCursoRows);
        });
    }

    /**
     * Método que nos devuelve a la pantalla anterior
     */
    @FXML
    private void onClickVolverButton(){
        FXService.cambiarVentana(FXService.MAIN);
    }

    /**
     * Método que inicializa las columnas de la tabla, vinculandolas a cada atributo de la entidad EstudianteNotasPorCursoRow,
     * específicamente hecha para mostrar esta relación en la tabla
     */
    private void inicializarColumnas(){
        TableColumn<EstudianteNotasPorCursoRow, Integer> idEstudianteColumn = new TableColumn<>();
        idEstudianteColumn.setCellValueFactory(new PropertyValueFactory<>("idEstudiante"));
        idEstudianteColumn.setMinWidth(125);
        this.setCustomColumnHeader(idEstudianteColumn, "ID ESTUDIANTE");
        this.centrarCentroContenidoComumna(idEstudianteColumn);
        TableColumn<EstudianteNotasPorCursoRow, String> nombreEstudianteColumn = new TableColumn<>();
        nombreEstudianteColumn.setCellValueFactory(new PropertyValueFactory<>("nombreEstudiante"));
        nombreEstudianteColumn.setMinWidth(250);
        this.setCustomColumnHeader(nombreEstudianteColumn, "NOMBRE ESTUDIANTE");
        this.centrarIzqContenidoComumna(nombreEstudianteColumn);
        TableColumn<EstudianteNotasPorCursoRow, Double> notaColumn = new TableColumn<>();
        notaColumn.setCellValueFactory(new PropertyValueFactory<>("notaEstudiante"));
        notaColumn.setMinWidth(75);
        this.setCustomColumnHeader(notaColumn, "NOTA");
        this.centrarCentroContenidoComumna(notaColumn);
        this.tablaTableView.getColumns().addAll(idEstudianteColumn, nombreEstudianteColumn, notaColumn);
    }

    /**
     * Método que aplica una fuente personalizada al encabezado de la columna.
     *
     * @param column La columna a la que se aplicará la fuente.
     * @param text   El texto del encabezado.
     * @param <T>    El tipo de dato de la columna.
     */
    private <T> void setCustomColumnHeader(TableColumn<EstudianteNotasPorCursoRow, T> column, String text) {
        Label header = new Label(text);
        header.setFont(FontService.SMALL_FONT);
        column.setGraphic(header);
        column.setStyle("-fx-background-color:  #ECFBFF; -fx-border-color:  #ccffff");
    }

    /**
     * Método que centra el contenido de una columna en el centro
     * @param column La columna a centrar
     * @param <T> El tipo de dato que contiene la columna
     */
    private <T> void centrarCentroContenidoComumna(TableColumn<EstudianteNotasPorCursoRow, T> column){
        this.centrarContenidoColumna(column, "-fx-alignment: CENTER;");
    }

    /**
     * Método que centra el contenido de una columna en la izquierda
     * @param column La columna a centrar
     * @param <T> El tipo de dato que contiene la columna
     */
    private <T> void centrarIzqContenidoComumna(TableColumn<EstudianteNotasPorCursoRow, T> column){
        this.centrarContenidoColumna(column, "-fx-alignment: CENTER-LEFT;");
    }

    /**
     * Método que centra el contenido de una columna según se le indique
     * @param column La columna a centrar
     * @param style Como queremos que se centre la columna (EJ: -fx-alignment: CENTER-LEFT; para centro izquierda)
     * @param <T> El tipo de dato que contiene la columna
     */
    private <T> void centrarContenidoColumna(TableColumn<EstudianteNotasPorCursoRow, T> column, String style) {
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
                    setFont(FontService.NORMAL_FONT);
                    setStyle("-fx-background-color:  #f1f1e6; -fx-border-color:  #ccffff");
                }
            }
        });
    }
}
