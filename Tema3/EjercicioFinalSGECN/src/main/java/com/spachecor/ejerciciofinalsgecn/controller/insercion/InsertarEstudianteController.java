package com.spachecor.ejerciciofinalsgecn.controller.insercion;

import com.spachecor.ejerciciofinalsgecn.controller.service.FXService;
import com.spachecor.ejerciciofinalsgecn.model.entity.Estudiante;
import com.spachecor.ejerciciofinalsgecn.model.service.repository.GenericRepositoryService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InsertarEstudianteController {
    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField apellidoTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private Label errorLabel;
    private Estudiante estudiante;
    @FXML
    protected void initialize(){
        if(FXService.getEstudiante()!=null){
            this.nombreTextField.setText(FXService.getEstudiante().getNombre());
            this.apellidoTextField.setText(FXService.getEstudiante().getApellido());
            this.emailTextField.setText(FXService.getEstudiante().getEmail());
            this.estudiante = FXService.getEstudiante();
        }else this.estudiante = new Estudiante();
    }
    @FXML
    private void onClickVolverButton(){
        FXService.cambiarVentana(FXService.GESTIONAR_ESTUDIANTES);
    }
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
