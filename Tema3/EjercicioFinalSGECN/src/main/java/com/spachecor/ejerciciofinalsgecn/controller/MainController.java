package com.spachecor.ejerciciofinalsgecn.controller;

import com.spachecor.ejerciciofinalsgecn.controller.service.FXService;
import com.spachecor.ejerciciofinalsgecn.controller.service.FontService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Clase MainController, que actúa como controlador de la vista principal.
 * @author Selene
 * @version 1.0
 */
public class MainController {
    @FXML
    private Label eduTrackLabel;
    @FXML
    private Button gestionarCursosButton;
    @FXML
    private Button gestionarEstudiantesButton;
    @FXML
    private Button gestionarNotasButton;
    @FXML
    private Button consultaNotasEstudiantePorCursoButton;
    @FXML
    private Button consultaEstudianteNotaCursoButton;
    @FXML
    protected void initialize(){
        FontService.setFont(
                FontService.BIG_FONT,
                this.eduTrackLabel);
        FontService.setFont(
                FontService.NORMAL_FONT,
                this.gestionarCursosButton,
                this.gestionarEstudiantesButton,
                this.gestionarNotasButton,
                this.consultaNotasEstudiantePorCursoButton,
                this.consultaEstudianteNotaCursoButton
        );
    }
    /**
     * Método que nos envía a la interfaz donde ponermos gestionar los cursos
     */
    @FXML
    private void onClickGestionarCursosButton() {
        FXService.cambiarVentana(FXService.GESTIONAR_CURSOS);
    }

    /**
     * Método que nos envía a la interfaz donde ponermos gestionar los estudiantes
     */
    @FXML
    private void onClickGestionarEstudiantesButton() {
        FXService.cambiarVentana(FXService.GESTIONAR_ESTUDIANTES);
    }

    /**
     * Método que nos envía a la interfaz donde ponermos gestionar las notas
     */
    @FXML
    private void onClickGestionarNotasButton() {
        FXService.cambiarVentana(FXService.GESTIONAR_NOTAS);
    }

    /**
     * Método que nos envía a la interfaz donde ponermos consultar las notas de un estudiante en un curso concreto
     */
    @FXML
    private void onClickconsultaNotasEstudiantePorCursoButton() {
        FXService.cambiarVentana(FXService.CONSULTAR_NOTAS_ESTUDIANTE_CURSO);
    }

    /**
     * Método que nos envía a la interfaz donde ponermos consultar las notas de todos los estudiantes de un curso concreto
     */
    @FXML
    private void onClickConsultaEstudianteNotaCursoButton() {
        FXService.cambiarVentana(FXService.CONSULTAR_ESTUDIANTES_NOTAS_POR_CURSO);
    }
}