package com.spachecor.ejerciciofinalsgecn.controller.insercion;

import com.spachecor.ejerciciofinalsgecn.controller.service.FXService;
import com.spachecor.ejerciciofinalsgecn.controller.service.FontService;
import com.spachecor.ejerciciofinalsgecn.model.entity.Estudiante;
import com.spachecor.ejerciciofinalsgecn.model.service.repository.GenericRepositoryService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Clase InsertarEstudianteController, que es el controlador de la interfaz gráfica con la que el usuario agregar o
 * modificar estudiantes
 * @author Selene
 * @version 1.0
 */
public class InsertarEstudianteController {
    @FXML
    private Label nombreLabel;
    @FXML
    private TextField nombreTextField;
    @FXML
    private Label apellidoLabel;
    @FXML
    private TextField apellidoTextField;
    @FXML
    private Label emailLabel;
    @FXML
    private TextField emailTextField;
    @FXML
    private Label errorLabel;
    @FXML
    private Button guadarButton;
    @FXML
    private Button volverButton;
    private Estudiante estudiante;
    @FXML
    protected void initialize(){
        FontService.setFont(
                FontService.NORMAL_FONT,
                this.nombreLabel,
                this.nombreTextField,
                this.apellidoLabel,
                this.apellidoTextField,
                this.emailLabel,
                this.emailTextField,
                this.errorLabel,
                this.guadarButton,
                this.volverButton
        );
        if(FXService.getEstudiante()!=null){
            this.nombreTextField.setText(FXService.getEstudiante().getNombre());
            this.apellidoTextField.setText(FXService.getEstudiante().getApellido());
            this.emailTextField.setText(FXService.getEstudiante().getEmail());
            this.estudiante = FXService.getEstudiante();
        }else this.estudiante = new Estudiante();
    }

    /**
     * Método que nos devuelve a la pantalla anterior
     */
    @FXML
    private void onClickVolverButton(){
        FXService.cambiarVentana(FXService.GESTIONAR_ESTUDIANTES);
    }

    /**
     * Método que guarda la nueva o modificada entidad en la base de datos usando el repositorio. Valida que no haya campos
     * vacíos antes de guardar, si los hay, avisa y no guarda.
     */
    @FXML
    private void onClickGuardarButton(){
        if(!this.nombreTextField.getText().isEmpty() || !this.apellidoTextField.getText().isEmpty() || !this.emailTextField.getText().isEmpty()){
            this.estudiante.setNombre(this.nombreTextField.getText());
            this.estudiante.setApellido(this.apellidoTextField.getText());
            this.estudiante.setEmail(this.emailTextField.getText());
            GenericRepositoryService<Estudiante> genericRepositoryService = new GenericRepositoryService<>(Estudiante.class);
            genericRepositoryService.guardar(this.estudiante);
            this.onClickVolverButton();
        }else this.errorLabel.setText("Ningún campo puede quedar en blanco.");
    }
}
