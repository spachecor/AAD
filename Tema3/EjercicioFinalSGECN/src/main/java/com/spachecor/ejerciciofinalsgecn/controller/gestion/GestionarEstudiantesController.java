package com.spachecor.ejerciciofinalsgecn.controller.gestion;

import com.spachecor.ejerciciofinalsgecn.controller.service.FontService;
import com.spachecor.ejerciciofinalsgecn.controller.service.tablaservice.EntidadRowListener;
import com.spachecor.ejerciciofinalsgecn.controller.service.FXService;
import com.spachecor.ejerciciofinalsgecn.controller.service.tablaservice.TableRecargable;
import com.spachecor.ejerciciofinalsgecn.model.entity.Estudiante;
import com.spachecor.ejerciciofinalsgecn.model.row.CursoRow;
import com.spachecor.ejerciciofinalsgecn.model.row.EstudianteRow;
import com.spachecor.ejerciciofinalsgecn.model.service.repository.GenericRepositoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase GestionarEstudiantesController, que es el controlador de la interfaz gráfica con la que el usuario puede gestionar
 * los estudiantes.
 * @author Selene
 * @version 1.0
 */
public class GestionarEstudiantesController implements TableRecargable, EntidadRowListener<Estudiante> {
    @FXML
    private Label eduTrackLabel;
    @FXML
    private TableView<EstudianteRow> tablaCursosTableView;
    @FXML
    private Button nuevoButton;
    @FXML
    private Button salirButton;
    private GenericRepositoryService<Estudiante> genericRepositoryService;
    @FXML
    protected void initialize() {
        FontService.setFont(FontService.LARGE_FONT, this.eduTrackLabel);
        FontService.setFont(FontService.NORMAL_FONT, this.nuevoButton, this.salirButton);
        genericRepositoryService = new GenericRepositoryService<>(Estudiante.class);
        this.inicializarColumnas();
        recargar();
    }

    /**
     * Método que nos devuelve a la pantalla anterior
     */
    @FXML
    private void onClickSalirButton() {
        FXService.cambiarVentana(FXService.MAIN);
    }

    /**
     * Método que nos envía a la interfaz gráfica para insertar un nuevo estudiante
     */
    @FXML
    private void onClickNuevoButton(){
        FXService.cambiarVentana(FXService.INSERTAR_ESTUDIANTE);
    }

    /**
     * Método que nos devuelve una lista tipo ObservableList con todos los estudiantes actuales preparada para usarla
     * en la tabla
     * @return Una lista tipo ObservableList con todos los estudiantes preparada para usarse en la tabla
     */
    private ObservableList<EstudianteRow> getObservableListToCursos(){
        List<Estudiante> estudiantes = genericRepositoryService.listar();
        List<EstudianteRow> estudianteRowList = new ArrayList<>();
        for (Estudiante estudiante : estudiantes) {
            estudianteRowList.add(new EstudianteRow(estudiante, this));
        }
        return FXCollections.observableList(estudianteRowList);
    }

    /**
     * Método que inicializa las columnas de la tabla, vinculandolas a cada atributo de la entidad EstudianteRow, específicamente
     * hecha para mostrar cursos en la tabla.
     */
    private void inicializarColumnas(){
        TableColumn<EstudianteRow, Integer> idTableColumn = new TableColumn<>();
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idTableColumn.setMinWidth(75);
        this.setCustomColumnHeader(idTableColumn, "ID");
        this.centrarCentroContenidoComumna(idTableColumn);
        TableColumn<EstudianteRow, String> nombreColumn = new TableColumn<>();
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        nombreColumn.setMinWidth(260);
        this.setCustomColumnHeader(nombreColumn, "NOMBRE");
        this.centrarIzqContenidoComumna(nombreColumn);
        TableColumn<EstudianteRow, String> apellidoColumn = new TableColumn<>();
        apellidoColumn.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        apellidoColumn.setMinWidth(280);
        this.setCustomColumnHeader(apellidoColumn, "APELLIDO");
        this.centrarIzqContenidoComumna(apellidoColumn);
        TableColumn<EstudianteRow, String> emailColumn = new TableColumn<>();
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumn.setMinWidth(280);
        this.setCustomColumnHeader(emailColumn, "EMAIL");
        this.centrarIzqContenidoComumna(emailColumn);
        TableColumn<EstudianteRow, Integer> nCursosEstudianteColumn = new TableColumn<>();
        nCursosEstudianteColumn.setCellValueFactory(new PropertyValueFactory<>("nCursosInscrito"));
        nCursosEstudianteColumn.setMinWidth(125);
        this.setCustomColumnHeader(nCursosEstudianteColumn, "N CURSOS");
        this.centrarCentroContenidoComumna(nCursosEstudianteColumn);
        TableColumn<EstudianteRow, Button> modificarTableColumn = new TableColumn<>();
        modificarTableColumn.setCellValueFactory(new PropertyValueFactory<>("modificar"));
        modificarTableColumn.setMinWidth(100);
        this.setCustomColumnHeader(modificarTableColumn, "MODIFICAR");
        this.centrarCentroContenidoComumna(modificarTableColumn);
        TableColumn<EstudianteRow, Button> eliminarTableColumn = new TableColumn<>();
        eliminarTableColumn.setCellValueFactory(new PropertyValueFactory<>("eliminar"));
        eliminarTableColumn.setMinWidth(100);
        this.setCustomColumnHeader(eliminarTableColumn, "ELIMINAR");
        this.centrarCentroContenidoComumna(eliminarTableColumn);
        this.tablaCursosTableView.getColumns().addAll(idTableColumn, nombreColumn, apellidoColumn, emailColumn, nCursosEstudianteColumn, modificarTableColumn, eliminarTableColumn);
    }

    /**
     * Método que aplica una fuente personalizada al encabezado de la columna.
     *
     * @param column La columna a la que se aplicará la fuente.
     * @param text   El texto del encabezado.
     * @param <T>    El tipo de dato de la columna.
     */
    private <T> void setCustomColumnHeader(TableColumn<EstudianteRow, T> column, String text) {
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
    private <T> void centrarCentroContenidoComumna(TableColumn<EstudianteRow, T> column){
        this.centrarContenidoColumna(column, "-fx-alignment: CENTER;");
    }

    /**
     * Método que centra el contenido de una columna en la izquierda
     * @param column La columna a centrar
     * @param <T> El tipo de dato que contiene la columna
     */
    private <T> void centrarIzqContenidoComumna(TableColumn<EstudianteRow, T> column){
        this.centrarContenidoColumna(column, "-fx-alignment: CENTER-LEFT;");
    }

    /**
     * Método que centra el contenido de una columna según se le indique
     * @param column La columna a centrar
     * @param style Como queremos que se centre la columna (EJ: -fx-alignment: CENTER-LEFT; para centro izquierda)
     * @param <T> El tipo de dato que contiene la columna
     */
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
    public void eliminarEntidad(Estudiante estudiante) {
        try{
            this.genericRepositoryService.eliminar(estudiante);
        }catch (org.hibernate.exception.ConstraintViolationException e){
            System.err.println("Se ha intentado eliminar un estudiante y la operación falló.");
        }
        this.recargar();
    }
}
