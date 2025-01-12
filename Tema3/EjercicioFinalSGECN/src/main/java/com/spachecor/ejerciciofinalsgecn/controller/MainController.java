package com.spachecor.ejerciciofinalsgecn.controller;

import com.spachecor.ejerciciofinalsgecn.controller.service.FXService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainController {
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
    private void onClickGestionarCursosButton() {
        FXService.cambiarVentana(FXService.GESTIONAR_CURSOS);
    }
    @FXML
    private void onClickGestionarEstudiantesButton() {
        FXService.cambiarVentana(FXService.GESTIONAR_ESTUDIANTES);
    }
    @FXML
    private void onClickGestionarNotasButton() {
        FXService.cambiarVentana(FXService.GESTIONAR_NOTAS);
    }
    @FXML
    private void onClickconsultaNotasEstudiantePorCursoButton() {
        FXService.cambiarVentana(FXService.CONSULTAR_NOTAS_ESTUDIANTE_CURSO);
    }
    @FXML
    private void onClickConsultaEstudianteNotaCursoButton() {
        FXService.cambiarVentana(FXService.CONSULTAR_ESTUDIANTES_NOTAS_POR_CURSO);
    }
}