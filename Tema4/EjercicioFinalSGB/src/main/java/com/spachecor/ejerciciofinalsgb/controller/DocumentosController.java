package com.spachecor.ejerciciofinalsgb.controller;

import com.spachecor.ejerciciofinalsgb.controller.service.FXService;
import com.spachecor.ejerciciofinalsgb.model.collections.ColeccionesManager;
import com.spachecor.ejerciciofinalsgb.model.documents.DocumentosManager;
import com.spachecor.ejerciciofinalsgb.model.entity.Libro;
import com.spachecor.ejerciciofinalsgb.model.entity.Prestamo;
import com.spachecor.ejerciciofinalsgb.model.entity.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.basex.core.users.User;

import javax.swing.*;
import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public class DocumentosController {
    @FXML
    private TextField modificarTextField;
    @FXML
    private ChoiceBox<String> coleccionChoiceBox;
    @FXML
    private TextField subColeccionTextField;
    @FXML
    private TableView<String> documentosTableView;
    @FXML
    private TableColumn<String,String> documentosTableColumn;
    private ObservableList<String> documentos;

    @FXML
    protected void initialize() {
        this.documentosTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
        this.cargarDocumentos();
        this.coleccionChoiceBox.setItems(FXCollections.observableArrayList(ColeccionesManager.listarColecciones()));
    }

    @FXML
    protected void onClickAniadirButton(){
        if(this.coleccionChoiceBox.getValue()!=null){
            if(!Objects.equals(this.coleccionChoiceBox.getValue(), "books") && !this.subColeccionTextField.getText().isEmpty())
                JOptionPane.showMessageDialog(null, "Escriba una subcolección únicamente cuando agregue libros", "Terrible", JOptionPane.WARNING_MESSAGE);
            else if(Objects.equals(this.coleccionChoiceBox.getValue(), "books") && this.subColeccionTextField.getText().isEmpty())
                JOptionPane.showMessageDialog(null, "Debe introducir una subcolección cuando introduzca libros", "Terrible", JOptionPane.WARNING_MESSAGE);
            else{
                //1º recogemos la url
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Elija un documento para insertar");
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos XML", "*.xml"));
                File file = fileChooser.showOpenDialog(null);
                if(file!=null){
                    //2º clasificamos e insertamos
                    Class clase = null;
                    if(this.coleccionChoiceBox.getValue().equals("books"))clase = Libro.class;
                    else if(this.coleccionChoiceBox.getValue().equals("users"))clase = Usuario.class;
                    else if(this.coleccionChoiceBox.getValue().equals("loans"))clase = Prestamo.class;
                    DocumentosManager.agregarDocumento(clase, file.getAbsolutePath(), (this.subColeccionTextField.getText().isEmpty())?null:this.subColeccionTextField.getText());
                    this.cargarDocumentos();
                    this.limpiarCampos();
                }else JOptionPane.showMessageDialog(null, "Debe seleccionar un archivo válido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else JOptionPane.showMessageDialog(null, "Debes seleccionar una colección", "Terrible", JOptionPane.WARNING_MESSAGE);
    }
    @FXML
    protected void onClickModificarButton(){
        String viejaRuta = this.documentosTableView.getSelectionModel().getSelectedItem();
        if(viejaRuta!=null && !this.modificarTextField.getText().isEmpty()){
            DocumentosManager.modificarDocumento(viejaRuta, this.modificarTextField.getText());
            this.cargarDocumentos();
            this.limpiarCampos();
        }else JOptionPane.showMessageDialog(null, "Debe seleccionar una ruta a cambiar e introducir un nuevo valor");
    }
    @FXML
    protected void onClickEliminarButton(){
        String url = this.documentosTableView.getSelectionModel().getSelectedItem();
        if(url!=null){
            DocumentosManager.eliminarDocumento(url);
            this.cargarDocumentos();
            this.limpiarCampos();
        }
    }
    @FXML
    protected void onClickExportarButton(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Seleccione un directorio donde almacenar la base de datos");
        File file = directoryChooser.showDialog(null);
        if(file!=null){
            DocumentosManager.exportarBBDD(file.getAbsolutePath());
        } else JOptionPane.showMessageDialog(null, "Debe seleccionar un directorio válido", "Terrible", JOptionPane.WARNING_MESSAGE);
    }
    @FXML
    protected void onClickSalirButton(){
        FXService.cambiarVentana(FXService.MENU_VIEW);
    }
    @FXML
    protected void onClickDocumentosTableView(){
        String documento = this.documentosTableView.getSelectionModel().getSelectedItem();
        if(documento!=null){
            this.modificarTextField.setText(documento);
        }
    }
    private void cargarDocumentos(){
        this.documentosTableView.getItems().clear();
        this.documentos = FXCollections.observableArrayList(Arrays.asList(DocumentosManager.listarDocumentos()));
        this.documentosTableView.setItems(this.documentos);
    }
    private void limpiarCampos(){
        this.modificarTextField.setText("");
        this.subColeccionTextField.setText("");
        this.coleccionChoiceBox.setValue(null);
        this.documentosTableView.getSelectionModel().clearSelection();
    }
}
