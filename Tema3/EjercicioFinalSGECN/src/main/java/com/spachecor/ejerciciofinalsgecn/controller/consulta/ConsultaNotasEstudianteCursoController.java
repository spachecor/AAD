package com.spachecor.ejerciciofinalsgecn.controller.consulta;

import com.spachecor.ejerciciofinalsgecn.controller.service.FXService;
import com.spachecor.ejerciciofinalsgecn.controller.service.FontService;
import com.spachecor.ejerciciofinalsgecn.model.entity.Curso;
import com.spachecor.ejerciciofinalsgecn.model.entity.Estudiante;
import com.spachecor.ejerciciofinalsgecn.model.entity.Nota;
import com.spachecor.ejerciciofinalsgecn.model.row.NotaEstudianteCursoRow;
import com.spachecor.ejerciciofinalsgecn.model.service.repository.GenericRepositoryService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase ConsultaNotasEstudianteCursoController, que es el controlador de la interfaz gráfica con la que el usuario
 * puede consultar las notas de todos los estudiantes en un curso en específico.
 * @author Selene
 * @version 1.0
 */
public class ConsultaNotasEstudianteCursoController {
    @FXML
    private Label cursoLabel;
    @FXML
    private ChoiceBox<Curso> cursoChoiceBox;
    @FXML
    private Label estudianteLabel;
    @FXML
    private ChoiceBox<Estudiante> estudianteChoiceBox;
    @FXML
    private Button volverButton;
    @FXML
    private TableView<NotaEstudianteCursoRow> tablaTableView;
    GenericRepositoryService<Curso> cursoGenericRepositoryService;
    GenericRepositoryService<Estudiante> estudianteGenericRepositoryService;
    @FXML
    protected void initialize() {
        FontService.setFont(
                FontService.NORMAL_FONT,
                this.cursoLabel,
                this.cursoChoiceBox,
                this.estudianteLabel,
                this.estudianteChoiceBox,
                this.volverButton
        );
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

    /**
     * Método que nos devuelve a la pantalla anterior
     */
    @FXML
    private void onClickVolverButton(){
        FXService.cambiarVentana(FXService.MAIN);
    }

    /**
     * Método que inicializa las columnas de la tabla, vinculandolas a cada atributo de la entidad NotaEstudianteCursoRow,
     * específicamente hecha para mostrar esta relación en la tabla
     */
    private void inicializarColumnas(){
        TableColumn<NotaEstudianteCursoRow, Integer> idEstudianteColumn = new TableColumn<>();
        idEstudianteColumn.setCellValueFactory(new PropertyValueFactory<>("idEstudiante"));
        idEstudianteColumn.setMinWidth(125);
        this.setCustomColumnHeader(idEstudianteColumn, "ID ESTUDIANTE");
        this.centrarCentroContenidoComumna(idEstudianteColumn);
        TableColumn<NotaEstudianteCursoRow, String> nombreEstudianteColumn = new TableColumn<>();
        nombreEstudianteColumn.setCellValueFactory(new PropertyValueFactory<>("nombreEstudiante"));
        nombreEstudianteColumn.setMinWidth(250);
        this.setCustomColumnHeader(nombreEstudianteColumn, "NOMBRE ESTUDIANTE");
        this.centrarIzqContenidoComumna(nombreEstudianteColumn);
        TableColumn<NotaEstudianteCursoRow, Integer> idCursoColumn = new TableColumn<>();
        idCursoColumn.setCellValueFactory(new PropertyValueFactory<>("idCurso"));
        idCursoColumn.setMinWidth(75);
        this.setCustomColumnHeader(idCursoColumn, "ID CURSO");
        this.centrarCentroContenidoComumna(idCursoColumn);
        TableColumn<NotaEstudianteCursoRow, String> nombreCursoColumn = new TableColumn<>();
        nombreCursoColumn.setCellValueFactory(new PropertyValueFactory<>("nombreCurso"));
        nombreCursoColumn.setMinWidth(250);
        this.setCustomColumnHeader(nombreCursoColumn, "NOMBRE CURSO");
        this.centrarIzqContenidoComumna(nombreCursoColumn);
        TableColumn<NotaEstudianteCursoRow, Double> notaColumn = new TableColumn<>();
        notaColumn.setCellValueFactory(new PropertyValueFactory<>("nota"));
        notaColumn.setMinWidth(75);
        this.setCustomColumnHeader(notaColumn, "NOTA");
        this.centrarCentroContenidoComumna(notaColumn);
        this.tablaTableView.getColumns().addAll(idEstudianteColumn, nombreEstudianteColumn, idCursoColumn, nombreCursoColumn, notaColumn);
    }

    /**
     * Método que aplica una fuente personalizada al encabezado de la columna.
     *
     * @param column La columna a la que se aplicará la fuente.
     * @param text   El texto del encabezado.
     * @param <T>    El tipo de dato de la columna.
     */
    private <T> void setCustomColumnHeader(TableColumn<NotaEstudianteCursoRow, T> column, String text) {
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
    private <T> void centrarCentroContenidoComumna(TableColumn<NotaEstudianteCursoRow, T> column){
        this.centrarContenidoColumna(column, "-fx-alignment: CENTER;");
    }

    /**
     * Método que centra el contenido de una columna en la izquierda
     * @param column La columna a centrar
     * @param <T> El tipo de dato que contiene la columna
     */
    private <T> void centrarIzqContenidoComumna(TableColumn<NotaEstudianteCursoRow, T> column){
        this.centrarContenidoColumna(column, "-fx-alignment: CENTER-LEFT;");
    }

    /**
     * Método que centra el contenido de una columna según se le indique
     * @param column La columna a centrar
     * @param style Como queremos que se centre la columna (EJ: -fx-alignment: CENTER-LEFT; para centro izquierda)
     * @param <T> El tipo de dato que contiene la columna
     */
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
                    setFont(FontService.NORMAL_FONT);
                    setStyle("-fx-background-color:  #f1f1e6; -fx-border-color:  #ccffff");
                }
            }
        });
    }
}
