package com.spachecor.ejerciciofinalsgb.controller;

import com.spachecor.ejerciciofinalsgb.controller.service.FXService;
import com.spachecor.ejerciciofinalsgb.controller.service.XMLDocumentService;
import com.spachecor.ejerciciofinalsgb.model.collections.ColeccionesManager;
import com.spachecor.ejerciciofinalsgb.model.dao.LibroDAOGenericImpl;
import com.spachecor.ejerciciofinalsgb.model.entity.Libro;
import com.spachecor.ejerciciofinalsgb.model.mapper.LibroMapper;
import com.spachecor.ejerciciofinalsgb.model.mapper.Mapper;
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

public class LibrosController {
    @FXML
    private TextField idTextField;
    @FXML
    private TextField tituloTextField;
    @FXML
    private TextField autorTextField;
    @FXML
    private TextField anioTextField;
    @FXML
    private TextField categoriaTextField;
    @FXML
    private TextField subCategoriaTextField;
    @FXML
    private TextField isbnTextField;
    @FXML
    private TextField editorialTextField;
    @FXML
    private TextField paginasTextField;
    @FXML
    private TextField copiasTextField;
    @FXML
    private TableView<Libro> librosTableView;
    @FXML
    private TableColumn<Libro, Integer> idTableColumn;
    @FXML
    private TableColumn<Libro, String> tituloTableColumn;
    @FXML
    private TableColumn<Libro, String> autorTableColumn;
    @FXML
    private TableColumn<Libro, Integer> anioTableColumn;
    @FXML
    private TableColumn<Libro, String> categoriaTableColumn;
    @FXML
    private TableColumn<Libro, String> subCategoriaTableColumn;
    @FXML
    private TableColumn<Libro, Long> isbnTableColumn;
    @FXML
    private TableColumn<Libro, String> editorialTableColumn;
    @FXML
    private TableColumn<Libro, Integer> paginasTableColumn;
    @FXML
    private TableColumn<Libro, Integer> copiasTableColumn;

    private LibroDAOGenericImpl entidadGenericDAO;
    private ObservableList<Libro> librosObservableList;

    @FXML
    protected void initialize() {
        this.entidadGenericDAO = new LibroDAOGenericImpl();
        this.idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.tituloTableColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        this.autorTableColumn.setCellValueFactory(new PropertyValueFactory<>("autor"));
        this.anioTableColumn.setCellValueFactory(new PropertyValueFactory<>("anioPublicacion"));
        this.categoriaTableColumn.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        this.subCategoriaTableColumn.setCellValueFactory(new PropertyValueFactory<>("subCategoria"));
        this.isbnTableColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        this.editorialTableColumn.setCellValueFactory(new PropertyValueFactory<>("editorial"));
        this.paginasTableColumn.setCellValueFactory(new PropertyValueFactory<>("numeroPaginas"));
        this.copiasTableColumn.setCellValueFactory(new PropertyValueFactory<>("numeroCopiasDisponibles"));
        this.cargarListaTableView();
    }

    @FXML
    protected void onClickAniadirButton(){
        if(
                this.validarCampos()
        ){
            Optional<Libro> oLibro = this.entidadGenericDAO.buscarPorId(Integer.parseInt(this.idTextField.getText()));
             if(oLibro.isPresent()){
                 JOptionPane.showMessageDialog(null, "El libro ya existe", "Terrible", JOptionPane.WARNING_MESSAGE);
                 return;
             }
            Mapper<Libro> libroMapper = new LibroMapper();
            //1º creamos el documento con el libro
            Libro libro = new Libro(
                    Integer.parseInt(this.idTextField.getText()),
                    this.tituloTextField.getText(),
                    this.autorTextField.getText(),
                    Integer.parseInt(this.anioTextField.getText()),
                    this.categoriaTextField.getText(),
                    this.subCategoriaTextField.getText(),
                    Long.parseLong(this.isbnTextField.getText()),
                    this.editorialTextField.getText(),
                    Integer.parseInt(this.paginasTextField.getText()),
                    Integer.parseInt(this.copiasTextField.getText())
            );
            String ruta = XMLDocumentService.crearDocumento(libroMapper.aXML(libro));
            //2º Lo introducimos en la base de datos
            this.entidadGenericDAO.crear(ruta, this.categoriaTextField.getText());
            //3º Eliminamos el documento temporal
            XMLDocumentService.eliminarDocumento(ruta);
            //4º Limpiamos campos
            this.limpiarCampos();
            //5º actualizamos la tabla
            this.librosObservableList.add(libro);
            this.librosTableView.refresh();
        }else JOptionPane.showMessageDialog(null, "Los campos no deben estar vacíos", "Error", JOptionPane.ERROR_MESSAGE);
    }
    @FXML
    protected void onClickModificarButton(){
        Libro libro = this.librosTableView.getSelectionModel().getSelectedItem();
        if(
                this.validarCampos()
                && libro != null
        ){
            //comprobamos que no se intente modificar el ID, no es modificable porque sirve para ubicar la entidad, sin el id tendremos fallos
            if(Integer.parseInt(this.idTextField.getText())!=libro.getId()){
                JOptionPane.showMessageDialog(null, "El ID no es modificable", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //0º lo movemos de coleccion si su categoría cambió(único para libros, el resto no funciona con subcolecciones)
            if(!libro.getCategoria().equals(this.categoriaTextField.getText())){
                ColeccionesManager.modificarSubColeccionPorId(libro, this.categoriaTextField.getText(), libro.getCategoria());
            }
            //1º modificamos el libro
            libro.setTitulo(this.tituloTextField.getText());
            libro.setAutor(this.autorTextField.getText());
            libro.setAnioPublicacion(Integer.parseInt(this.anioTextField.getText()));
            libro.setCategoria(this.categoriaTextField.getText());
            libro.setSubCategoria(this.subCategoriaTextField.getText());
            libro.setIsbn(Long.parseLong(this.isbnTextField.getText()));
            libro.setEditorial(this.editorialTextField.getText());
            libro.setNumeroPaginas(Integer.parseInt(this.paginasTextField.getText()));
            libro.setNumeroCopiasDisponibles(Integer.parseInt(this.copiasTextField.getText()));
            //2º lo actualizamos en la base de datos
            this.entidadGenericDAO.actualizar(libro);
            //3º actualizamos interfaz grafica y limpiamos campos
            this.librosTableView.refresh();
            this.limpiarCampos();
        }else JOptionPane.showMessageDialog(null, "Los campos no deben estar vacíos y debes tener un libro seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
    }
    @FXML
    protected void onClickEliminarButton(){
        Libro libro = this.librosTableView.getSelectionModel().getSelectedItem();
        if(libro!=null){
            //1º eliminamos de la base de datos
            this.entidadGenericDAO.eliminar(libro);
            //2º eliminamos de la interfaz grafica, actualizamos y limpiamos campos
            this.librosObservableList.remove(libro);
            this.librosTableView.refresh();
            this.limpiarCampos();
        }else JOptionPane.showMessageDialog(null, "Debes tener un libro seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
    }
    @FXML
    protected void onClickBuscarButton(){
        if(!this.idTextField.getText().isEmpty()){
            Optional<Libro> oLibro = this.entidadGenericDAO.buscarPorId(Integer.parseInt(this.idTextField.getText()));
            if(oLibro.isPresent()){
                this.librosObservableList.clear();
                this.librosObservableList.add(oLibro.get());
                this.librosTableView.refresh();
                this.limpiarCampos();
            }else JOptionPane.showMessageDialog(null, "Libro no encontrado", "Terrible", JOptionPane.WARNING_MESSAGE);
        }else if(!this.categoriaTextField.getText().isEmpty()){
            List<Libro> libros = this.entidadGenericDAO.obtenerLibrosPorCategoria(this.categoriaTextField.getText());
            if(!libros.isEmpty()){
                this.librosObservableList.clear();
                this.librosObservableList.addAll(libros);
                this.librosTableView.refresh();
                this.limpiarCampos();
            }else JOptionPane.showMessageDialog(null, "La categoria no existe", "Terrible", JOptionPane.WARNING_MESSAGE);
        }else JOptionPane.showMessageDialog(null, "Debes introducir un id o una categoría", "Error", JOptionPane.ERROR_MESSAGE);
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
    protected void onClickLibrosTablaView(){
        Libro libro = librosTableView.getSelectionModel().getSelectedItem();
        if(libro != null){
            this.idTextField.setText(libro.getId().toString());
            this.tituloTextField.setText(libro.getTitulo());
            this.autorTextField.setText(libro.getAutor());
            this.anioTextField.setText(libro.getAnioPublicacion().toString());
            this.categoriaTextField.setText(libro.getCategoria());
            this.subCategoriaTextField.setText(libro.getSubCategoria());
            this.isbnTextField.setText(libro.getIsbn().toString());
            this.editorialTextField.setText(libro.getEditorial());
            this.paginasTextField.setText(libro.getNumeroPaginas().toString());
            this.copiasTextField.setText(libro.getNumeroCopiasDisponibles().toString());
        }
    }
    private void cargarListaTableView(){
        this.librosObservableList = FXCollections.observableArrayList();
        this.rellenarListaTableView();
        this.librosTableView.setItems(this.librosObservableList);
    }
    private void rellenarListaTableView(){
        List<Libro> libros = this.entidadGenericDAO.listar();
        this.librosObservableList.clear();
        this.librosObservableList.addAll(libros);
    }
    private void limpiarCampos(){
        this.idTextField.setText("");
        this.tituloTextField.setText("");
        this.autorTextField.setText("");
        this.anioTextField.setText("");
        this.categoriaTextField.setText("");
        this.subCategoriaTextField.setText("");
        this.isbnTextField.setText("");
        this.editorialTextField.setText("");
        this.paginasTextField.setText("");
        this.copiasTextField.setText("");
        this.librosTableView.getSelectionModel().clearSelection();
    }
    private boolean validarCampos(){
        return !this.idTextField.getText().isEmpty()
                && !this.tituloTextField.getText().isEmpty()
                && !this.autorTextField.getText().isEmpty()
                && !this.anioTextField.getText().isEmpty()
                && !this.categoriaTextField.getText().isEmpty()
                && !this.subCategoriaTextField.getText().isEmpty()
                && !this.isbnTextField.getText().isEmpty()
                && !this.editorialTextField.getText().isEmpty()
                && !this.paginasTextField.getText().isEmpty()
                && !this.copiasTextField.getText().isEmpty();
    }
}
