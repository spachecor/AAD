package com.spachecor.ejerciciofinalsgb.controller;

import com.spachecor.ejerciciofinalsgb.controller.service.FXService;
import com.spachecor.ejerciciofinalsgb.model.documents.DocumentosManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.Arrays;

public class DocumentosController {
    @FXML
    private TextField modificarTextField;
    @FXML
    private TableView<String> documentosTableView;
    @FXML
    private TableColumn<String,String> documentosTableColumn;
    private ObservableList<String> documentos;

    @FXML
    protected void initialize() {
        this.documentosTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
        this.cargarDocumentos();
    }

    @FXML
    protected void onClickAniadirButton(){}
    @FXML
    protected void onClickModificarButton(){}
    @FXML
    protected void onClickEliminarButton(){}
    @FXML
    protected void onClickExportarButton(){}
    @FXML
    protected void onClickSalirButton(){
        FXService.cambiarVentana(FXService.MENU_VIEW);
    }

    private void cargarDocumentos(){
        this.documentosTableView.getItems().clear();
        this.documentos = FXCollections.observableArrayList(Arrays.asList(DocumentosManager.listarDocumentos()));
        this.documentosTableView.setItems(this.documentos);
    }
}
