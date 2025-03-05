package com.spachecor.ejerciciofinalsgb.controller;

import com.spachecor.ejerciciofinalsgb.controller.service.FXService;
import com.spachecor.ejerciciofinalsgb.controller.service.XMLDocumentService;
import com.spachecor.ejerciciofinalsgb.model.dao.EntidadGenericDAOImpl;
import com.spachecor.ejerciciofinalsgb.model.dao.LibroDAOGenericImpl;
import com.spachecor.ejerciciofinalsgb.model.dao.PrestamoDAOGenericImpl;
import com.spachecor.ejerciciofinalsgb.model.dao.UsuarioDAOGenericImpl;
import com.spachecor.ejerciciofinalsgb.model.entity.Libro;
import com.spachecor.ejerciciofinalsgb.model.entity.Prestamo;
import com.spachecor.ejerciciofinalsgb.model.entity.Usuario;
import com.spachecor.ejerciciofinalsgb.model.mapper.Mapper;
import com.spachecor.ejerciciofinalsgb.model.mapper.PrestamoMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PrestamosController {
    @FXML
    private TextField idTextField;
    @FXML
    private ChoiceBox<Usuario> usuarioChoiceBox;
    @FXML
    private ChoiceBox<Libro> libroChoiceBox;
    @FXML
    private DatePicker fechaPrestamoDatePicker;
    @FXML
    private DatePicker fechaDevolucionDatePicker;
    @FXML
    private ChoiceBox<String> devueltoChoiceBox;
    @FXML
    private TableView<Prestamo> prestamoTableView;
    @FXML
    private TableColumn<Prestamo, String> idTableColumn;
    @FXML
    private TableColumn<Prestamo, Usuario> usuarioTableColumn;
    @FXML
    private TableColumn<Prestamo, Libro> libroTableColumn;
    @FXML
    private TableColumn<Prestamo, LocalDate> fechaPrestamoTableColumn;
    @FXML
    private TableColumn<Prestamo, LocalDate> fechaDevolucionTableColumn;
    @FXML
    private TableColumn<Prestamo, String> devueltoTableColumn;

    private EntidadGenericDAOImpl<Prestamo> prestamoEntidadGenericDAO;
    private ObservableList<Prestamo> prestamosObservableList;
    private String[] devueltoOptions;

    @FXML
    protected void initialize(){
        //inicializamos valores
        this.prestamoEntidadGenericDAO = new PrestamoDAOGenericImpl();
        EntidadGenericDAOImpl<Libro> libroEntidadGenericDAO = new LibroDAOGenericImpl();
        EntidadGenericDAOImpl<Usuario> usuarioEntidadGenericDAO = new UsuarioDAOGenericImpl();
        devueltoOptions = new String[]{"Sí", "No"};
        this.devueltoChoiceBox.setItems(FXCollections.observableArrayList(devueltoOptions));
        this.libroChoiceBox.setItems(FXCollections.observableList(libroEntidadGenericDAO.listar()));
        this.usuarioChoiceBox.setItems(FXCollections.observableList(usuarioEntidadGenericDAO.listar()));
        this.fechaPrestamoDatePicker.setValue(LocalDate.now());
        this.fechaDevolucionDatePicker.setValue(LocalDate.now().plusDays(7));
        this.idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.usuarioTableColumn.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        this.libroTableColumn.setCellValueFactory(new PropertyValueFactory<>("libro"));
        this.fechaPrestamoTableColumn.setCellValueFactory(new PropertyValueFactory<>("fechaPrestamo"));
        this.fechaDevolucionTableColumn.setCellValueFactory(new PropertyValueFactory<>("fechaDevolucion"));
        this.devueltoTableColumn.setCellValueFactory(new PropertyValueFactory<>("devuelto"));
        this.cargarListaTableView();
    }

    @FXML
    protected void onClickAniadirButton(){
        if(
            this.validarCampos()
        ){
            Mapper<Prestamo> prestamoMapper = new PrestamoMapper();
            Prestamo prestamo = new Prestamo(
                    Integer.parseInt(this.idTextField.getText()),
                    this.usuarioChoiceBox.getValue(),
                    this.libroChoiceBox.getValue(),
                    this.fechaPrestamoDatePicker.getValue(),
                    this.fechaDevolucionDatePicker.getValue(),
                    Objects.equals(this.devueltoChoiceBox.getValue(), this.devueltoOptions[0])
            );
            String ruta = XMLDocumentService.crearDocumento(prestamoMapper.aXML(prestamo));
            this.prestamoEntidadGenericDAO.crear(ruta, null);
            XMLDocumentService.eliminarDocumento(ruta);
            this.limpiarCampos();
            this.prestamosObservableList.add(prestamo);
            this.prestamoTableView.refresh();
        }else JOptionPane.showMessageDialog(null, "Los campos no deben estar vacíos", "Error", JOptionPane.ERROR_MESSAGE);
    }
    @FXML
    protected void onClickModificarButton(){
        Prestamo prestamo = this.prestamoTableView.getSelectionModel().getSelectedItem();
        if(
                this.validarCampos()
                && prestamo != null
        ){
            if(Integer.parseInt(this.idTextField.getText()) != prestamo.getId()){
                JOptionPane.showMessageDialog(null, "El ID no es modificable", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            prestamo.setUsuario(this.usuarioChoiceBox.getValue());
            prestamo.setLibro(this.libroChoiceBox.getValue());
            prestamo.setFechaPrestamo(this.fechaPrestamoDatePicker.getValue());
            prestamo.setFechaDevolucion(this.fechaDevolucionDatePicker.getValue());
            prestamo.setDevuelto(this.devueltoChoiceBox.getValue().equals(this.devueltoOptions[0]));
            this.prestamoEntidadGenericDAO.actualizar(prestamo);
            this.prestamoTableView.refresh();
            this.limpiarCampos();
        }else JOptionPane.showMessageDialog(null, "Los campos no deben estar vacíos y debes tener un préstamo seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
    }
    @FXML
    protected void onClickEliminarButton(){
        Prestamo prestamo = this.prestamoTableView.getSelectionModel().getSelectedItem();
        if(prestamo != null){
            this.prestamoEntidadGenericDAO.eliminar(prestamo);
            this.prestamosObservableList.remove(prestamo);
            this.prestamoTableView.refresh();
            this.limpiarCampos();
        }else JOptionPane.showMessageDialog(null, "Debes tener un préstamo seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
    }
    @FXML
    protected void onClickBuscarButton(){
        if(!this.idTextField.getText().isEmpty()){
            Optional<Prestamo> oPrestamo = this.prestamoEntidadGenericDAO.buscarPorId(Integer.parseInt(this.idTextField.getText()));
            if(oPrestamo.isPresent()){
                this.prestamosObservableList.clear();
                this.prestamosObservableList.add(oPrestamo.get());
                this.prestamoTableView.refresh();
                this.limpiarCampos();
            }else JOptionPane.showMessageDialog(null, "Préstamo no encontrado", "Terrible", JOptionPane.WARNING_MESSAGE);
        }else JOptionPane.showMessageDialog(null, "Debes introducir un id", "Error", JOptionPane.ERROR_MESSAGE);
    }
    @FXML
    protected void onClickListarButton(){
        this.rellenarListaTableView();
        this.limpiarCampos();
    }
    @FXML
    protected void onClickSalirButton(){
        FXService.cambiarVentana(FXService.MENU_VIEW);
    }
    @FXML
    protected void onClickPrestamosTableView(){
        Prestamo prestamo = this.prestamoTableView.getSelectionModel().getSelectedItem();
        if(prestamo != null){
            this.idTextField.setText(prestamo.getId().toString());
            this.usuarioChoiceBox.getSelectionModel().select(prestamo.getUsuario());
            this.libroChoiceBox.getSelectionModel().select(prestamo.getLibro());
            this.fechaPrestamoDatePicker.setValue(prestamo.getFechaPrestamo());
            this.fechaDevolucionDatePicker.setValue(prestamo.getFechaDevolucion());
            this.devueltoChoiceBox.setValue((prestamo.getDevuelto())?this.devueltoOptions[0]:this.devueltoOptions[1]);
        }
    }
    private void cargarListaTableView(){
        this.prestamosObservableList = FXCollections.observableArrayList();
        this.rellenarListaTableView();
        this.prestamoTableView.setItems(this.prestamosObservableList);
    }
    private void rellenarListaTableView(){
        List<Prestamo> prestamos = prestamoEntidadGenericDAO.listar();
        this.prestamosObservableList.clear();
        this.prestamosObservableList.addAll(prestamos);
    }
    private void limpiarCampos(){
        this.idTextField.setText("");
        this.usuarioChoiceBox.getSelectionModel().clearSelection();
        this.libroChoiceBox.getSelectionModel().clearSelection();
        this.fechaPrestamoDatePicker.setValue(LocalDate.now());
        this.fechaDevolucionDatePicker.setValue(LocalDate.now().plusDays(7));
        this.devueltoChoiceBox.getSelectionModel().clearSelection();
    }
    private boolean validarCampos(){
        return !this.idTextField.getText().isEmpty()
                && !this.usuarioChoiceBox.getSelectionModel().isEmpty()
                && !this.libroChoiceBox.getSelectionModel().isEmpty()
                && !this.fechaPrestamoDatePicker.getValue().isBefore(LocalDate.now())
                && !this.fechaDevolucionDatePicker.getValue().isBefore(this.fechaPrestamoDatePicker.getValue())
                && !this.devueltoChoiceBox.getSelectionModel().isEmpty();
    }
}
