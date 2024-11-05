package com.spachecor.proyectoproductos.controller;

import com.spachecor.proyectoproductos.Main;
import com.spachecor.proyectoproductos.model.conversores.ConversorCSV;
import com.spachecor.proyectoproductos.model.conversores.ConversorJSON;
import com.spachecor.proyectoproductos.model.conversores.ConversorXML;
import com.spachecor.proyectoproductos.model.entity.Producto;
import com.spachecor.proyectoproductos.model.repository.ProductoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Clase que es el controlador de la vista principal de la aplicación
 * @author Selene
 * @version 1.0
 */
public class ProductosController implements Initializable {
    @FXML
    private Label labelNotificacion;
    @FXML
    private TextField inputNombre;
    @FXML
    private TextField inputPrecio;
    @FXML
    private TextField inputCantidad;
    @FXML
    private TableView<Producto> tablaProductos;
    @FXML
    private TableColumn<?, ?> columnaNombre;
    @FXML
    private TableColumn<?, ?> columnaPrecio;
    @FXML
    private TableColumn<?, ?> columnaCantidad;
    private ObservableList<Producto> productos;
    private ProductoDAO productoDAO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productoDAO = new ProductoDAO();
        //ahora vinculamos las columans con el nombre del atributo del objeto producto
        this.columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.columnaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        this.cargarListaTabla();
    }

    /**
     * Método que agrega el producto en el XML y en la tabla de la vista.
     */
    @FXML
    private void aniadirProducto() {
        labelNotificacion.setText("");
        String nombre = this.inputNombre.getText();
        String precio = this.inputPrecio.getText();
        String cantidad = this.inputCantidad.getText();
        if(!nombre.isEmpty() || !precio.isEmpty() || !cantidad.isEmpty()) {
            try{
                //primero creamos el producto
                Producto producto = new Producto();
                producto.setNombre(nombre);
                producto.setPrecio(Double.valueOf(precio));
                producto.setCantidad(Integer.valueOf(cantidad));
                if(!this.productos.contains(producto)){
                    //ahora lo agregamos en la lista de la tabla y en el xml
                    productos.add(producto);
                    this.tablaProductos.setItems(productos);//actualizamos la tabla
                    productoDAO.agregarProducto(producto);
                    //limpiamos campos
                    this.limpiarCampos();
                }else throw new IllegalArgumentException("El producto ya existe.");
            }catch (NumberFormatException e){
                labelNotificacion.setText("Precio y cantidad deben ser\n valores numéricos");
            }catch (IllegalArgumentException e){
                labelNotificacion.setText(e.getMessage());
            }
        }else{
            labelNotificacion.setText("Los campos no pueden estar vacíos");
        }
    }

    /**
     * Método que modifica el producto que se haya seleccionado en la tabla de la vista.
     */
    @FXML
    private void modificarProducto() {
        labelNotificacion.setText("");
        //tomamos el producto que se ha seleccionado
        Producto p = tablaProductos.getSelectionModel().getSelectedItem();
        if(p != null){
            try{
                Producto aux = new Producto();
                aux.setNombre(inputNombre.getText());
                aux.setCantidad(Integer.valueOf(inputCantidad.getText()));
                aux.setPrecio(Double.valueOf(inputPrecio.getText()));
                //modificamos en el xml
                productoDAO.actualizarProducto(p.getNombre(), aux);
                //modificamos los valores
                p.setNombre(aux.getNombre());
                p.setPrecio(aux.getPrecio());
                p.setCantidad(aux.getCantidad());
                //recargamos la lista
                this.tablaProductos.refresh();
                this.limpiarCampos();
                this.tablaProductos.getSelectionModel().clearSelection();
            }catch (NumberFormatException e){
                labelNotificacion.setText("Precio y cantidad deben ser\n valores numéricos");
            }
        }else labelNotificacion.setText("Debe seleccionar un producto");
    }

    /**
     * Método que elimina el producto que se haya seleccionado en la tabla de la vista.
     */
    @FXML
    private void eliminarProducto() {
        labelNotificacion.setText("");
        //tomamos el producto que se ha seleccionado
        Producto p = tablaProductos.getSelectionModel().getSelectedItem();
        if(p!=null){
            //eliminamos p de la tabla y del xml
            this.productos.remove(p);
            this.tablaProductos.refresh();
            productoDAO.eliminarProducto(p);
            this.limpiarCampos();
            this.tablaProductos.getSelectionModel().clearSelection();
        }else labelNotificacion.setText("Debe seleccionar un producto");
    }

    /**
     * Método que selecciona el producto que hemos seleccionado en la tabla de la vista
     */
    @FXML
    private void seleccionar(){
        labelNotificacion.setText("");
        //tomamos el producto que se ha seleccionado
        Producto p = tablaProductos.getSelectionModel().getSelectedItem();
        //siempre que no sea null, asignamos los valores en los inputs
        if(p != null){
            inputNombre.setText(p.getNombre());
            inputPrecio.setText(p.getPrecio().toString());
            inputCantidad.setText(p.getCantidad().toString());
        }
    }

    /**
     * Método que busca un único producto por su nombre entre los productos disponibles
     */
    @FXML
    private void buscar(){
        labelNotificacion.setText("");
        String nombre = this.inputNombre.getText();
        if(!nombre.isEmpty()){
            List<Producto> productosAux = productos.stream().toList();
            Producto productoAux = null;
            for(Producto temp : productosAux){
                //si encontramos el producto lo guardamos
                if(temp.getNombre().equals(nombre)){
                    productoAux = temp;
                }
            }
            if(productoAux != null){
                productos.clear();
                productos.add(productoAux);
                this.tablaProductos.setItems(productos);
                this.limpiarCampos();
            }else labelNotificacion.setText("El producto no existe");
        }else labelNotificacion.setText("El nombre del producto no\npuede estar vacío");
    }

    /**
     * Método que lista todos los productos disponibles en el XML en la tabla de la vista
     */
    @FXML
    private void listar(){
        labelNotificacion.setText("");
        this.cargarListaTabla();
        this.limpiarCampos();
    }

    /**
     * Método que se encarga de seleccionar la ubicación para descargar ahí la copia de los productos en formato CSV
     */
    @FXML
    private void descargarCSV() {
        labelNotificacion.setText("");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar archivo CSV");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv"));

        //seleccionamos de la ubicación de guardado
        File destino = fileChooser.showSaveDialog(Main.stage);

        if (destino != null) {
            ConversorCSV conversorCSV = new ConversorCSV();
            try {
                //convertimos y obtenemos el archivo CSV temporal
                File archivoTemporalCSV = conversorCSV.convert(new File("src/main/resources/com/spachecor/proyectoproductos/xml/productos.xml"));

                //copiamos el archivo CSV temporal al destino seleccionado
                Files.copy(archivoTemporalCSV.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);

                labelNotificacion.setText("Archivo CSV guardado exitosamente.");
            } catch (IOException e) {
                labelNotificacion.setText("Imposible guardar el archivo.");
            }
        }
    }
    /**
     * Método que se encarga de seleccionar la ubicación para descargar ahí la copia de los productos en formato JSON
     */
    @FXML
    private void descargarJSON() {
        labelNotificacion.setText("");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar archivo JSON");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json"));

        //seleccionamos de la ubicación de guardado
        File destino = fileChooser.showSaveDialog(Main.stage);

        if (destino != null) {
            ConversorJSON conversorJSON = new ConversorJSON();
            try {
                //convertimos y obtenemos el archivo CSV temporal
                File archivoTemporalJSON = conversorJSON.convert(new File("src/main/resources/com/spachecor/proyectoproductos/xml/productos.xml"));

                //copiamos el archivo CSV temporal al destino seleccionado
                Files.copy(archivoTemporalJSON.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);

                labelNotificacion.setText("Archivo JSON guardado exitosamente.");
            } catch (IOException e) {
                labelNotificacion.setText("Imposible guardar el archivo.");
            }
        }
    }
    /**
     * Método que se encarga de seleccionar la ubicación para importar ahí los productos en formato CSV
     */
    @FXML
    private void importarCSV(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo CSV para importar");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv"));

        //abrimos el cuadro de diálogo de selección de archivo
        File file = fileChooser.showOpenDialog(Main.stage);

        if (file != null) {
            try {
                ConversorXML conversorXML = new ConversorXML();
                //procesamos el archivo CSV seleccionado
                conversorXML.convert(file);
                this.cargarListaTabla();
                labelNotificacion.setText("Archivo CSV importado exitosamente.");
            } catch (Exception e) {
                labelNotificacion.setText("Error al importar el archivo CSV.");
            }
        }
    }
    /**
     * Método que se encarga de seleccionar la ubicación para importar ahí los productos en formato JSON
     */
    @FXML
    private void importarJSON(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo JSON para importar");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json"));

        //abrimos el cuadro de diálogo de selección de archivo
        File file = fileChooser.showOpenDialog(Main.stage);

        if (file != null) {
            try {
                ConversorXML conversorXML = new ConversorXML();
                //procesamos el archivo CSV seleccionado
                conversorXML.convert(file);
                this.cargarListaTabla();
                labelNotificacion.setText("Archivo JSON importado exitosamente.");
            } catch (IOException e) {
                labelNotificacion.setText("Error al importar el archivo JSON.");
            }
        }
    }

    /**
     * Método que se encarga de cargar todos los productos disponibles en el XML en la tabla de la vista
     */
    private void cargarListaTabla(){
        //tomamos los productos ya existentes y los almacenamos en la lista de productos de JavaFX
        List<Producto> productoList = productoDAO.getProductos().getProductos();
        //se crea la lista vacía
        productos = FXCollections.observableArrayList();
        //almacenamos los productos en la lista de JavaFX
        productos.addAll(productoList);
        //colocamos la lista de productos en la tabla
        this.tablaProductos.setItems(productos);
    }

    /**
     * Método que se encarga de limpiar todos los inputs de la vista
     */
    private void limpiarCampos(){
        inputNombre.setText("");
        inputPrecio.setText("");
        inputCantidad.setText("");
    }
}