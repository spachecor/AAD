package com.spachecor.ejerciciofinalsgecn.controller.insercion;

import com.spachecor.ejerciciofinalsgecn.controller.service.FXService;
import com.spachecor.ejerciciofinalsgecn.controller.service.FontService;
import com.spachecor.ejerciciofinalsgecn.model.entity.Curso;
import com.spachecor.ejerciciofinalsgecn.model.entity.Estudiante;
import com.spachecor.ejerciciofinalsgecn.model.entity.Nota;
import com.spachecor.ejerciciofinalsgecn.model.service.repository.GenericRepositoryService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

/**
 * Clase InsertarNotaController, que es el controlador de la interfaz gráfica con la que el usuario agregar o
 * modificar notas
 * @author Selene
 * @version 1.0
 */
public class InsertarNotaController {
    @FXML
    private Label valorLabel;
    @FXML
    private TextField valorTextField;
    @FXML
    private Label fechaLabel;
    @FXML
    private DatePicker fechaDatePicker;
    @FXML
    private Label cursoLabel;
    @FXML
    private ChoiceBox<Curso> cursoChoiceBox;
    @FXML
    private Label estudianteLabel;
    @FXML
    private ChoiceBox<Estudiante> estudianteChoiceBox;
    @FXML
    private Label errorLabel;
    @FXML
    private Button guardarButton;
    @FXML
    private Button volverButton;
    private Nota nota;
    @FXML
    public void initialize() {
        FontService.setFont(
                FontService.NORMAL_FONT,
                this.valorLabel,
                this.valorTextField,
                this.fechaLabel,
                this.fechaDatePicker,
                this.estudianteLabel,
                this.estudianteChoiceBox,
                this.cursoLabel,
                this.cursoChoiceBox,
                this.errorLabel,
                this.guardarButton,
                this.volverButton
        );
        this.recargarCursoChoiceBox();
        if(FXService.getNota()!=null){
            this.valorTextField.setText(String.valueOf(FXService.getNota().getValor()));
            this.fechaDatePicker.setValue(FXService.getNota().getFecha());
            this.cursoChoiceBox.setValue(FXService.getNota().getCurso());
            this.recargarEstudianteChoiceBox(cursoChoiceBox.getValue());
            this.estudianteChoiceBox.setValue(FXService.getNota().getEstudiante());
            this.nota=FXService.getNota();
        }else{
            this.nota = new Nota();
            this.fechaDatePicker.setValue(LocalDate.now());
        }
        this.cursoChoiceBox.getSelectionModel().selectedItemProperty().addListener((_, _, newValue) -> {
            this.recargarEstudianteChoiceBox(newValue);
        });
    }

    /**
     * Método que guarda la nueva o modificada entidad en la base de datos usando el repositorio. Valida que no haya campos
     * vacíos antes de guardar, si los hay, avisa y no guarda.
     */
    @FXML
    private void onClickGuardarButton(){
        if(this.valorTextField.getText().isEmpty() || this.fechaDatePicker.getValue()==null || this.cursoChoiceBox.getValue()==null || this.estudianteChoiceBox.getValue()==null){
            this.errorLabel.setText("El valor debe ser numérico");
            return;
        }
        if (!this.containsNumbers(this.valorTextField.getText())){
            this.errorLabel.setText("El valor debe ser numérico");
            return;
        }
        this.nota.setValor(Double.parseDouble(valorTextField.getText()));
        this.nota.setFecha(fechaDatePicker.getValue());
        this.nota.setCurso(cursoChoiceBox.getValue());
        this.nota.setEstudiante(estudianteChoiceBox.getValue());
        GenericRepositoryService<Nota> notaGenericRepositoryService = new GenericRepositoryService<>(Nota.class);
        notaGenericRepositoryService.guardar(this.nota);
        this.onClickVolverButton();
    }

    /**
     * Método que nos devuelve a la pantalla anterior
     */
    @FXML
    private void onClickVolverButton(){
        FXService.cambiarVentana(FXService.GESTIONAR_NOTAS);
    }

    /**
     * Método que obtiene todos los cursos de la base de datos y los recarga en el ChoiceBox correspondiente
     */
    private void recargarCursoChoiceBox(){
        GenericRepositoryService<Curso> cursoGenericRepositoryService = new GenericRepositoryService<>(Curso.class);
        this.cursoChoiceBox.getItems().clear();
        this.cursoChoiceBox.getItems().addAll(cursoGenericRepositoryService.listar());
    }

    /**
     * Método que obtiene todos los estudiantes del curso y los recarga en el ChoiceBox correspondiente
     */
    private void recargarEstudianteChoiceBox(Curso curso){
        this.estudianteChoiceBox.getItems().clear();
        this.estudianteChoiceBox.getItems().addAll(curso.getEstudiantes());
    }

    /**
     * Método que comprueba si la cadena entrante es completamente numérica
     * @param input La cadena a comprobar
     * @return true si es completamente numérica y false si no lo es
     */
    private boolean containsNumbers(String input) {
        return input.matches(".*\\d.*");
    }
}
