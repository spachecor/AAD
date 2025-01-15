package com.spachecor.ejerciciofinalsgecn.controller.gestion;

import com.spachecor.ejerciciofinalsgecn.controller.service.FontService;
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
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase GestionarCursosController, que es el controlador de la interfaz gráfica con la que el usuario puede gestionar
 * los cursos.
 * @author Selene
 * @version 1.0
 */
public class GestionarCursosController implements TableRecargable, EntidadRowListener<Curso> {
    @FXML
    private Label eduTrackLabel;
    @FXML
    private TableView<CursoRow> tablaCursosTableView;
    @FXML
    private Label errorLabel;
    @FXML
    private Button nuevoButton;
    @FXML
    private Button salirButton;
    private GenericRepositoryService<Curso> genericRepositoryService;
    @FXML
    protected void initialize() {
        FontService.setFont(FontService.LARGE_FONT, this.eduTrackLabel);
        FontService.setFont(FontService.NORMAL_FONT, this.nuevoButton, this.salirButton, this.errorLabel);
        genericRepositoryService = new GenericRepositoryService<>(Curso.class);
        this.inicializarColumnas();
        this.recargar();
    }

    /**
     * Método que nos envía a la interfaz gráfica para insertar un nuevo curso
     */
    @FXML
    private void onClickNuevoButton(){
        FXService.cambiarVentana(FXService.INSERTAR_CURSO);
    }

    /**
     * Método que nos devuelve a la pantalla anterior
     */
    @FXML
    private void onClickSalirButton() {
        FXService.cambiarVentana(FXService.MAIN);
    }

    /**
     * Método que nos devuelve una lista tipo ObservableList con todos los cursos actuales preparada para usarla
     * en la tabla
     * @return Una lista tipo ObservableList con todos los cursos preparada para usarse en la tabla
     */
    private ObservableList<CursoRow> getObservableListToCursos(){
        List<Curso> cursos = genericRepositoryService.listar();
        List<CursoRow> cursoRowList = new ArrayList<>();
        for (Curso curso : cursos) {
            cursoRowList.add(new CursoRow(curso, this));
        }
        return FXCollections.observableList(cursoRowList);
    }

    /**
     * Método que inicializa las columnas de la tabla, vinculandolas a cada atributo de la entidad CursoRow, específicamente
     * hecha para mostrar cursos en la tabla.
     */
    private void inicializarColumnas(){
        TableColumn<CursoRow, Integer> idTableColumn = new TableColumn<>();
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idTableColumn.setMinWidth(75);
        this.setCustomColumnHeader(idTableColumn, "ID");
        this.centrarCentroContenidoComumna(idTableColumn);
        TableColumn<CursoRow, String> nombreTableColumn = new TableColumn<>();
        nombreTableColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        nombreTableColumn.setMinWidth(200);
        this.setCustomColumnHeader(nombreTableColumn, "NOMBRE");
        this.centrarIzqContenidoComumna(nombreTableColumn);
        TableColumn<CursoRow, String> descripcionTableColumn = new TableColumn<>();
        descripcionTableColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        descripcionTableColumn.setMinWidth(605);
        this.setCustomColumnHeader(descripcionTableColumn, "DESCRIPCION");
        this.centrarIzqContenidoComumna(descripcionTableColumn);
        TableColumn<CursoRow, Integer> nEstudiantesTableColumn = new TableColumn<>();
        nEstudiantesTableColumn.setCellValueFactory(new PropertyValueFactory<>("nEstudiantes"));
        nEstudiantesTableColumn.setMinWidth(150);
        this.setCustomColumnHeader(nEstudiantesTableColumn, "N ESTUDIANTES");
        this.centrarCentroContenidoComumna(nEstudiantesTableColumn);
        TableColumn<CursoRow, Button> modificarTableColumn = new TableColumn<>();
        modificarTableColumn.setCellValueFactory(new PropertyValueFactory<>("modificar"));
        modificarTableColumn.setMinWidth(100);
        this.setCustomColumnHeader(modificarTableColumn, "MODIFICAR");
        this.centrarCentroContenidoComumna(modificarTableColumn);
        TableColumn<CursoRow, Button> eliminarTableColumn = new TableColumn<>();
        eliminarTableColumn.setCellValueFactory(new PropertyValueFactory<>("eliminar"));
        eliminarTableColumn.setMinWidth(100);
        this.setCustomColumnHeader(eliminarTableColumn, "ELIMINAR");
        this.centrarCentroContenidoComumna(eliminarTableColumn);
        this.tablaCursosTableView.getColumns().addAll(idTableColumn, nombreTableColumn, descripcionTableColumn, nEstudiantesTableColumn, modificarTableColumn, eliminarTableColumn);
    }

    /**
     * Método que aplica una fuente personalizada al encabezado de la columna.
     *
     * @param column La columna a la que se aplicará la fuente.
     * @param text   El texto del encabezado.
     * @param <T>    El tipo de dato de la columna.
     */
    private <T> void setCustomColumnHeader(TableColumn<CursoRow, T> column, String text) {
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
    private <T> void centrarCentroContenidoComumna(TableColumn<CursoRow, T> column){
        this.centrarContenidoColumna(column, "-fx-alignment: CENTER;");
    }

    /**
     * Método que centra el contenido de una columna en la izquierda
     * @param column La columna a centrar
     * @param <T> El tipo de dato que contiene la columna
     */
    private <T> void centrarIzqContenidoComumna(TableColumn<CursoRow, T> column){
        this.centrarContenidoColumna(column, "-fx-alignment: CENTER-LEFT;");
    }

    /**
     * Método que centra el contenido de una columna según se le indique
     * @param column La columna a centrar
     * @param style Como queremos que se centre la columna (EJ: -fx-alignment: CENTER-LEFT; para centro izquierda)
     * @param <T> El tipo de dato que contiene la columna
     */
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
                    setFont(FontService.NORMAL_FONT);
                    setStyle("-fx-background-color:  #f1f1e6; -fx-border-color:  #ccffff");
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
