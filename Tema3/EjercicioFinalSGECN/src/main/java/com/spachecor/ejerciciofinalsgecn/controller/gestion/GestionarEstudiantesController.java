package com.spachecor.ejerciciofinalsgecn.controller.gestion;

import com.spachecor.ejerciciofinalsgecn.controller.service.FontService;
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

/**
 * Clase GestionarEstudiantesController, que es el controlador de la interfaz gráfica con la que el usuario puede gestionar
 * los estudiantes.
 * @author Selene
 * @version 1.0
 */
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
