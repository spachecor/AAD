package com.spachecor.ejerciciofinalsgb.controller;

import com.spachecor.ejerciciofinalsgb.controller.service.FXService;
import com.spachecor.ejerciciofinalsgb.model.collections.ColeccionesManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.util.Arrays;

public class ColeccionesController {
    @FXML
    private TableView<String> coleccionesTableView;
    @FXML
    private TableView<String> subColeccionesTableView;
    @FXML
    private TableColumn<String, String> coleccionesTableColumn;
    @FXML
    private TableColumn<String, String> subColeccionesTableColumn;
    @FXML
    private TextField nombreTextField;
    private ObservableList<String> colecciones;
    private ObservableList<String> subColecciones;

    @FXML
    protected void initialize() {
        this.cargarColeccionesYSubColecciones();
        this.coleccionesTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
        this.subColeccionesTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
        this.coleccionesTableView.setSelectionModel(null);
    }
    @FXML
    protected void onClickSubColeccionesTableView(){
        this.nombreTextField.setText(this.subColeccionesTableView.getSelectionModel().getSelectedItem());
    }
    @FXML
    protected void onClickModificarButton(){
        if(
                this.subColeccionesTableView.getSelectionModel().getSelectedItem()!=null
                        && this.nombreTextField.getText()!=null
                        && !this.nombreTextField.getText().isEmpty()
                        && !this.subColeccionesTableView.getSelectionModel().getSelectedItem().equals(this.nombreTextField.getText())
        ){
            ColeccionesManager.modificarSubcoleccion(this.nombreTextField.getText(), this.subColeccionesTableView.getSelectionModel().getSelectedItem());
            this.limpiarCampos();
            this.cargarColeccionesYSubColecciones();
        }else JOptionPane.showMessageDialog(null, "Seleccione una subcoleccion y no olvide poner el nuevo nombre(Debe ser diferente al anterior)");
    }
    @FXML
    protected void onClickEliminarButton(){
        if(this.subColeccionesTableView.getSelectionModel().getSelectedItem()!=null){
            ColeccionesManager.eliminarSubcoleccion(this.subColeccionesTableView.getSelectionModel().getSelectedItem());
            this.limpiarCampos();
            this.cargarColeccionesYSubColecciones();
        }else JOptionPane.showMessageDialog(null, "Seleccione una subcoleccion");
    }
    @FXML
    protected void onClickSalirButton(){
        FXService.cambiarVentana(FXService.MENU_VIEW);
    }
    private void cargarColeccionesYSubColecciones() {
        this.coleccionesTableView.getItems().clear();
        this.subColeccionesTableView.getItems().clear();
        this.colecciones = FXCollections.observableArrayList(Arrays.asList(ColeccionesManager.listarColecciones()));
        this.subColecciones = FXCollections.observableArrayList(Arrays.asList(ColeccionesManager.listarSubColeccionesLibros()));
        this.coleccionesTableView.setItems(colecciones);
        this.subColeccionesTableView.setItems(subColecciones);
    }
    private void limpiarCampos(){
        this.nombreTextField.setText("");
        this.subColeccionesTableView.getSelectionModel().clearSelection();
    }
}
