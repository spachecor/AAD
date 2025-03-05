package com.spachecor.ejerciciofinalsgb.controller;

import com.spachecor.ejerciciofinalsgb.controller.service.FXService;
import com.spachecor.ejerciciofinalsgb.controller.service.XMLDocumentService;
import com.spachecor.ejerciciofinalsgb.model.dao.EntidadGenericDAOImpl;
import com.spachecor.ejerciciofinalsgb.model.dao.UsuarioDAOGenericImpl;
import com.spachecor.ejerciciofinalsgb.model.entity.Libro;
import com.spachecor.ejerciciofinalsgb.model.entity.Usuario;
import com.spachecor.ejerciciofinalsgb.model.mapper.Mapper;
import com.spachecor.ejerciciofinalsgb.model.mapper.UsuarioMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public class UsuariosController {
    @FXML
    private TextField idTextField;
    @FXML
    private TextField dniTextField;
    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField telefonoTextField;
    @FXML
    private TextField direccionTextField;
    @FXML
    private TableView<Usuario> usuarioTableView;
    @FXML
    private TableColumn<Usuario, Integer> idTableColumn;
    @FXML
    private TableColumn<Usuario, String> dniTableColumn;
    @FXML
    private TableColumn<Usuario, String> nombreTableColumn;
    @FXML
    private TableColumn<Usuario, String> emailTableColumn;
    @FXML
    private TableColumn<Usuario, String> telefonoTableColumn;
    @FXML
    private TableColumn<Usuario, String> direccionTableColumn;

    private EntidadGenericDAOImpl<Usuario> entidadGenericDAO;
    private ObservableList<Usuario> usuariosObservableList;

    @FXML
    private void initialize() {
        this.entidadGenericDAO = new UsuarioDAOGenericImpl();
        this.idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.dniTableColumn.setCellValueFactory(new PropertyValueFactory<>("dni"));
        this.nombreTableColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.emailTableColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.telefonoTableColumn.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        this.direccionTableColumn.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        this.cargarListaTableView();
    }

    @FXML
    protected void onClickAniadirButton(){
        if(
                this.validarCampos()
        ){
            Mapper<Usuario> usuarioMapper = new UsuarioMapper();
            Usuario usuario = new Usuario(
                    Integer.parseInt(this.idTextField.getText()),
                    this.dniTextField.getText(),
                    this.nombreTextField.getText(),
                    this.emailTextField.getText(),
                    this.telefonoTextField.getText(),
                    this.direccionTextField.getText()
            );
            String ruta = XMLDocumentService.crearDocumento(usuarioMapper.aXML(usuario));
            this.entidadGenericDAO.crear(ruta, null);
            XMLDocumentService.eliminarDocumento(ruta);
            this.limpiarCampos();
            this.usuariosObservableList.add(usuario);
            this.usuarioTableView.refresh();
        }else JOptionPane.showMessageDialog(null, "Los campos no deben estar vacíos", "Error", JOptionPane.ERROR_MESSAGE);
    }
    @FXML
    protected void onClickModificarButton(){
        Usuario usuario = this.usuarioTableView.getSelectionModel().getSelectedItem();
        if(
                this.validarCampos()
                && usuario != null
        ){
            if(Integer.parseInt(this.idTextField.getText()) != usuario.getId()){
                JOptionPane.showMessageDialog(null, "El ID no es modificable", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            usuario.setDni(this.dniTextField.getText());
            usuario.setNombre(this.nombreTextField.getText());
            usuario.setEmail(this.emailTextField.getText());
            usuario.setTelefono(this.telefonoTextField.getText());
            usuario.setDireccion(this.direccionTextField.getText());
            this.entidadGenericDAO.actualizar(usuario);
            this.usuarioTableView.refresh();
            this.limpiarCampos();
        }else JOptionPane.showMessageDialog(null, "Los campos no deben estar vacíos y debes tener un usuario seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
    }
    @FXML
    protected void onClickEliminarButton(){
        Usuario usuario = this.usuarioTableView.getSelectionModel().getSelectedItem();
        if(usuario!=null){
            this.entidadGenericDAO.eliminar(usuario);
            this.usuariosObservableList.remove(usuario);
            this.usuarioTableView.refresh();
            this.limpiarCampos();
        }else JOptionPane.showMessageDialog(null, "Debes tener un usuario seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
    }
    @FXML
    protected void onClickBuscarButton(){
        if(!this.idTextField.getText().isEmpty()){
            Optional<Usuario> oUsuario = this.entidadGenericDAO.buscarPorId(Integer.parseInt(this.idTextField.getText()));
            if(oUsuario.isPresent()){
                this.usuariosObservableList.clear();
                this.usuariosObservableList.add(oUsuario.get());
                this.usuarioTableView.refresh();
                this.limpiarCampos();
            }else JOptionPane.showMessageDialog(null, "Usuario no encontrado", "Terrible", JOptionPane.WARNING_MESSAGE);
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
    protected void onClickUsuariosTableView(){
        Usuario usuario = this.usuarioTableView.getSelectionModel().getSelectedItem();
        if(usuario!=null){
            this.idTextField.setText(usuario.getId().toString());
            this.dniTextField.setText(usuario.getDni());
            this.nombreTextField.setText(usuario.getNombre());
            this.emailTextField.setText(usuario.getEmail());
            this.telefonoTextField.setText(usuario.getTelefono());
            this.direccionTextField.setText(usuario.getDireccion());
        }
    }
    private void cargarListaTableView(){
        this.usuariosObservableList = FXCollections.observableArrayList();
        this.rellenarListaTableView();
        this.usuarioTableView.setItems(this.usuariosObservableList);
    }
    private void rellenarListaTableView(){
        List<Usuario> usuarios = this.entidadGenericDAO.listar();
        this.usuariosObservableList.clear();
        this.usuariosObservableList.addAll(usuarios);
    }
    private void limpiarCampos(){
        this.idTextField.setText("");
        this.dniTextField.setText("");
        this.nombreTextField.setText("");
        this.emailTextField.setText("");
        this.telefonoTextField.setText("");
        this.direccionTextField.setText("");
        this.usuarioTableView.getSelectionModel().clearSelection();
    }
    private boolean validarCampos(){
        return !this.idTextField.getText().isEmpty()
                && !this.dniTextField.getText().isEmpty()
                && !this.nombreTextField.getText().isEmpty()
                && !this.emailTextField.getText().isEmpty()
                && !this.telefonoTextField.getText().isEmpty()
                && !this.direccionTextField.getText().isEmpty();
    }
}
