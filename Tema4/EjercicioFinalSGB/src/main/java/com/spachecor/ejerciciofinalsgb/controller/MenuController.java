package com.spachecor.ejerciciofinalsgb.controller;

import com.spachecor.ejerciciofinalsgb.controller.service.FXService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MenuController {
    @FXML
    private void onClickLibrosButton(){
        FXService.cambiarVentana(FXService.LIBROS_VIEW);
    }
    @FXML
    private void onClickUsuariosButton(){
        FXService.cambiarVentana(FXService.USUARIOS_VIEW);
    }
    @FXML
    private void onClickPrestamosButton(){
        FXService.cambiarVentana(FXService.PRESTAMOS_VIEW);
    }
    @FXML
    private void onClickColeccionesButton(){
        FXService.cambiarVentana(FXService.COLECCIONES_VIEW);
    }
    @FXML
    private void onClickGestionarDocumentosButton(){
        FXService.cambiarVentana(FXService.DOCUMENTOS_VIEW);
    }
}