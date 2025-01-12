package com.spachecor.ejerciciofinalsgecn.controller.insercion;

import com.spachecor.ejerciciofinalsgecn.controller.service.FXService;
import com.spachecor.ejerciciofinalsgecn.controller.service.tablaservice.EntidadRowListener;
import com.spachecor.ejerciciofinalsgecn.controller.service.tablaservice.TableRecargable;
import com.spachecor.ejerciciofinalsgecn.model.entity.Curso;
import com.spachecor.ejerciciofinalsgecn.model.entity.Estudiante;
import com.spachecor.ejerciciofinalsgecn.model.row.EstudianteRow;
import com.spachecor.ejerciciofinalsgecn.model.service.repository.EstudianteCursoRepository;
import com.spachecor.ejerciciofinalsgecn.model.service.repository.GenericRepositoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.*;

/**
 * Clase InsertarCursoController, que es el controlador de la interfaz gráfica con la que el usuario agregar o
 * modificar cursos
 * @author Selene
 * @version 1.0
 */
public class InsertarCursoController implements TableRecargable, EntidadRowListener<Estudiante> {
    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField descripcionTextField;
    @FXML
    private ChoiceBox<Estudiante> listaAlumnosChoiceBox;
    @FXML
    private TableView<EstudianteRow> tablaAlumnosTableView;
    @FXML
    private Label errorLabel;
    private Curso curso;
    private List<Estudiante> estudiantesEliminados;

    @FXML
    protected void initialize(){
        this.estudiantesEliminados = new ArrayList<>();
        if(FXService.getCurso()!=null){
            this.curso = FXService.getCurso();
            this.nombreTextField.setText(curso.getNombre());
            this.descripcionTextField.setText(curso.getDescripcion());
        }else this.curso = new Curso();
        this.refrescarAlumnosChoiceBox();
        this.inicializarColumnas();
        this.recargar();
    }

    /**
     * Método que agrega un nuevo estudiante a la lista de estudiantes del curso
     */
    @FXML
    private void onClickAniadirButton(){
        if(this.listaAlumnosChoiceBox.getValue()!=null){
            this.curso.getEstudiantes().add(this.listaAlumnosChoiceBox.getValue());
            this.listaAlumnosChoiceBox.getValue().getCursos().add(this.curso);
            this.refrescarAlumnosChoiceBox();
            this.recargar();
            this.estudiantesEliminados.remove(this.listaAlumnosChoiceBox.getValue());
        }else this.errorLabel.setText("Debes seleccionar un estudiante.");
    }

    /**
     * Método que guarda la nueva o modificada entidad en la base de datos usando el repositorio. Valida que no haya campos
     * vacíos antes de guardar, si los hay, avisa y no guarda.
     */
    @FXML
    private void onClickGuardarButton(){
        if(!this.nombreTextField.getText().isEmpty() || !this.descripcionTextField.getText().isEmpty()){
            this.curso.setNombre(nombreTextField.getText());
            this.curso.setDescripcion(descripcionTextField.getText());
            GenericRepositoryService<Curso> cursoGenericRepositoryService = new GenericRepositoryService<>(Curso.class);
            cursoGenericRepositoryService.guardar(this.curso);
            EstudianteCursoRepository estudianteCursoRepository = new EstudianteCursoRepository();
            for(Estudiante estudiante: this.estudiantesEliminados){
                estudianteCursoRepository.eliminarEstudianteDeCurso(estudiante, this.curso);
            }
            GenericRepositoryService<Estudiante> estudianteGenericRepositoryService = new GenericRepositoryService<>(Estudiante.class);
            for(Estudiante estudiante: this.curso.getEstudiantes()){
                estudianteGenericRepositoryService.guardar(estudiante);
            }
            this.onClickVolverButton();
        }else this.errorLabel.setText("El nombre y la descripción son obligatorios.");
    }

    /**
     * Método que nos devuelve a la pantalla anterior
     */
    @FXML
    private void onClickVolverButton(){
        FXService.setCurso(null);
        FXService.cambiarVentana(FXService.GESTIONAR_CURSOS);
    }

    /**
     * Método que nos devuelve una lista tipo ObservableList con todos los estudiantes actuales preparada para usarla
     * en la tabla
     * @return Una lista tipo ObservableList con todos los estudiantes preparada para usarse en la tabla
     */
    private ObservableList<EstudianteRow> obtenerEstudiantesCurso(){
        Set<Estudiante> estudiantes = this.curso.getEstudiantes();
        List<EstudianteRow> estudianteRowList = new ArrayList<>();
        for (Estudiante estudiante : estudiantes) {
            estudianteRowList.add(new EstudianteRow(estudiante, this));
        }
        return FXCollections.observableList(estudianteRowList);
    }

    /**
     * Método que inicializa las columnas de la tabla, vinculandolas a cada atributo de la entidad EstudianteRow, específicamente
     * hecha para mostrar estudiantes en la tabla.
     */
    private void inicializarColumnas(){
        TableColumn<EstudianteRow, Integer> idTableColumn = new TableColumn<>("ID");
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idTableColumn.setMinWidth(50);
        this.centrarCentroContenidoComumna(idTableColumn);
        TableColumn<EstudianteRow, String> nombreColumn = new TableColumn<>("NOMBRE");
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        nombreColumn.setMinWidth(150);
        this.centrarIzqContenidoComumna(nombreColumn);
        TableColumn<EstudianteRow, String> apellidoColumn = new TableColumn<>("APELLIDO");
        apellidoColumn.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        apellidoColumn.setMinWidth(150);
        this.centrarIzqContenidoComumna(apellidoColumn);
        TableColumn<EstudianteRow, String> emailColumn = new TableColumn<>("EMAIL");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumn.setMinWidth(110);
        this.centrarIzqContenidoComumna(emailColumn);
        TableColumn<EstudianteRow, Integer> nCursosEstudianteColumn = new TableColumn<>("Nº CURSOS");
        nCursosEstudianteColumn.setCellValueFactory(new PropertyValueFactory<>("nCursosInscrito"));
        nCursosEstudianteColumn.setMinWidth(50);
        this.centrarCentroContenidoComumna(nCursosEstudianteColumn);
        TableColumn<EstudianteRow, Button> eliminarTableColumn = new TableColumn<>("ELIMINAR");
        eliminarTableColumn.setCellValueFactory(new PropertyValueFactory<>("eliminar"));
        eliminarTableColumn.setMinWidth(50);
        this.centrarCentroContenidoComumna(eliminarTableColumn);
        this.tablaAlumnosTableView.getColumns().addAll(idTableColumn, nombreColumn, apellidoColumn, emailColumn, nCursosEstudianteColumn, eliminarTableColumn);
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
                }
            }
        });
    }

    /**
     * Método que refresca el correspondiente ChoiceBox de alumnos siempre y cuando estén inscritos todos al mismo
     * curso (que se ha seleccionado anteriormente con otro ChoiceBox)
     */
    private void refrescarAlumnosChoiceBox(){
        this.listaAlumnosChoiceBox.getItems().clear();
        GenericRepositoryService<Estudiante> estudianteGenericRepositoryService = new GenericRepositoryService<>(Estudiante.class);
        List<Estudiante> estudiantes = estudianteGenericRepositoryService.listar();
        List<Estudiante> estudiantesList = new ArrayList<>();
        if(this.curso!=null){
            for(Estudiante estudiante: estudiantes){
                if(!this.curso.getEstudiantes().contains(estudiante))estudiantesList.add(estudiante);
            }
        }else estudiantesList = estudiantes;
        this.listaAlumnosChoiceBox.getItems().addAll(estudiantesList);
    }
    @Override
    public void recargar() {
        this.tablaAlumnosTableView.getItems().clear();
        this.tablaAlumnosTableView.setItems(this.obtenerEstudiantesCurso());
    }

    @Override
    public void eliminarEntidad(Estudiante estudiante) {
        this.curso.getEstudiantes().remove(estudiante);
        this.refrescarAlumnosChoiceBox();
        ObservableList<Estudiante> estudianteObservableList = this.listaAlumnosChoiceBox.getItems();
        for(Estudiante estudianteTemp: estudianteObservableList){
            if(estudianteTemp.getId().equals(estudiante.getId())){
                estudianteTemp.getCursos().remove(this.curso);
            }
        }
        if(!estudiantesEliminados.contains(estudiante))this.estudiantesEliminados.add(estudiante);
        this.recargar();
    }
    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
