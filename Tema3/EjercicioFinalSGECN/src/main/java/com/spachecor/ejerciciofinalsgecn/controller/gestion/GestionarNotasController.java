package com.spachecor.ejerciciofinalsgecn.controller.gestion;

import com.spachecor.ejerciciofinalsgecn.controller.service.FXService;
import com.spachecor.ejerciciofinalsgecn.controller.service.FontService;
import com.spachecor.ejerciciofinalsgecn.controller.service.tablaservice.EntidadRowListener;
import com.spachecor.ejerciciofinalsgecn.controller.service.tablaservice.TableRecargable;
import com.spachecor.ejerciciofinalsgecn.model.entity.Nota;
import com.spachecor.ejerciciofinalsgecn.model.row.EstudianteRow;
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

/**
 * Clase GestionarNotasController, que es el controlador de la interfaz gráfica con la que el usuario puede gestionar
 * las notas.
 * @author Selene
 * @version 1.0
 */
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

    /**
     * Método que nos envía a la interfaz gráfica para insertar una nueva nota
     */
    @FXML
    private void onClickNuevoButton(){
        FXService.cambiarVentana(FXService.INSERTAR_NOTA);
    }

    /**
     * Método que nos devuelve a la pantalla anterior
     */
    @FXML
    private void onClickSalirButton() {
        FXService.cambiarVentana(FXService.MAIN);
    }

    /**
     * Método que nos devuelve una lista tipo ObservableList con todas las notas actuales preparada para usarla
     * en la tabla
     * @return Una lista tipo ObservableList con todas las notas preparada para usarse en la tabla
     */
    private ObservableList<NotaRow> getObservableListToCursos(){
        List<Nota> notas = genericRepositoryService.listar();
        List<NotaRow> notaRowList = new ArrayList<>();
        for (Nota nota : notas) {
            notaRowList.add(new NotaRow(nota, this));
        }
        return FXCollections.observableList(notaRowList);
    }

    /**
     * Método que inicializa las columnas de la tabla, vinculandolas a cada atributo de la entidad NotaRow, específicamente
     * hecha para mostrar notas en la tabla.
     */
    private void inicializarColumnas(){
        TableColumn<NotaRow, Integer> idColumn = new TableColumn<>();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setMinWidth(75);
        this.setCustomColumnHeader(idColumn, "ID");
        this.centrarCentroContenidoComumna(idColumn);
        TableColumn<NotaRow, Double> valorColumn = new TableColumn<>();
        valorColumn.setCellValueFactory(new PropertyValueFactory<>("valor"));
        valorColumn.setMinWidth(75);
        this.setCustomColumnHeader(valorColumn, "VALOR");
        this.centrarCentroContenidoComumna(valorColumn);
        TableColumn<NotaRow, LocalDate> fechaColumn = new TableColumn<>();
        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        fechaColumn.setMinWidth(150);
        this.setCustomColumnHeader(fechaColumn, "FECHA");
        this.centrarCentroContenidoComumna(fechaColumn);
        TableColumn<NotaRow, Integer> idCursoColumn = new TableColumn<>();
        idCursoColumn.setCellValueFactory(new PropertyValueFactory<>("idCurso"));
        idCursoColumn.setMinWidth(75);
        this.setCustomColumnHeader(idCursoColumn, "ID CURSO");
        this.centrarCentroContenidoComumna(idCursoColumn);
        TableColumn<NotaRow, String> nombreCursoColumn = new TableColumn<>();
        nombreCursoColumn.setCellValueFactory(new PropertyValueFactory<>("nombreCurso"));
        nombreCursoColumn.setMinWidth(250);
        this.setCustomColumnHeader(nombreCursoColumn, "NOMBRE DEL CURSO");
        this.centrarIzqContenidoComumna(nombreCursoColumn);
        TableColumn<NotaRow, Integer> idEstudianteColumn = new TableColumn<>();
        idEstudianteColumn.setCellValueFactory(new PropertyValueFactory<>("idEstudiante"));
        idEstudianteColumn.setMinWidth(125);
        this.setCustomColumnHeader(idEstudianteColumn, "ID ESTUDIANTE");
        this.centrarCentroContenidoComumna(idEstudianteColumn);
        TableColumn<NotaRow, String> nombreEstudianteColumn = new TableColumn<>();
        nombreEstudianteColumn.setCellValueFactory(new PropertyValueFactory<>("nombreEstudiante"));
        nombreEstudianteColumn.setMinWidth(250);
        this.setCustomColumnHeader(nombreEstudianteColumn, "NOMBRE DEL ESTUDIANTE");
        this.centrarIzqContenidoComumna(nombreEstudianteColumn);
        TableColumn<NotaRow, Button> modificarTableColumn = new TableColumn<>();
        modificarTableColumn.setCellValueFactory(new PropertyValueFactory<>("modificar"));
        modificarTableColumn.setMinWidth(100);
        this.setCustomColumnHeader(modificarTableColumn, "MODIFICAR");
        this.centrarCentroContenidoComumna(modificarTableColumn);
        TableColumn<NotaRow, Button> eliminarTableColumn = new TableColumn<>();
        eliminarTableColumn.setCellValueFactory(new PropertyValueFactory<>("eliminar"));
        eliminarTableColumn.setMinWidth(100);
        this.setCustomColumnHeader(eliminarTableColumn, "ELIMINAR");
        this.centrarCentroContenidoComumna(eliminarTableColumn);
        this.tablaCursosTableView.getColumns().addAll(idColumn, valorColumn, fechaColumn, idCursoColumn, nombreCursoColumn, idEstudianteColumn, nombreEstudianteColumn, modificarTableColumn, eliminarTableColumn);
    }

    /**
     * Método que aplica una fuente personalizada al encabezado de la columna.
     *
     * @param column La columna a la que se aplicará la fuente.
     * @param text   El texto del encabezado.
     * @param <T>    El tipo de dato de la columna.
     */
    private <T> void setCustomColumnHeader(TableColumn<NotaRow, T> column, String text) {
        Label header = new Label(text);
        header.setFont(FontService.SMALL_FONT);
        column.setGraphic(header);
    }

    /**
     * Método que centra el contenido de una columna en el centro
     * @param column La columna a centrar
     * @param <T> El tipo de dato que contiene la columna
     */
    private <T> void centrarCentroContenidoComumna(TableColumn<NotaRow, T> column){
        this.centrarContenidoColumna(column, "-fx-alignment: CENTER;");
    }

    /**
     * Método que centra el contenido de una columna en la izquierda
     * @param column La columna a centrar
     * @param <T> El tipo de dato que contiene la columna
     */
    private <T> void centrarIzqContenidoComumna(TableColumn<NotaRow, T> column){
        this.centrarContenidoColumna(column, "-fx-alignment: CENTER-LEFT;");
    }

    /**
     * Método que centra el contenido de una columna según se le indique
     * @param column La columna a centrar
     * @param style Como queremos que se centre la columna (EJ: -fx-alignment: CENTER-LEFT; para centro izquierda)
     * @param <T> El tipo de dato que contiene la columna
     */
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
                    setFont(FontService.NORMAL_FONT);
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
            System.err.println("Se ha intentado eliminar una nota y la operación falló.");
        }
        this.recargar();
    }
}
