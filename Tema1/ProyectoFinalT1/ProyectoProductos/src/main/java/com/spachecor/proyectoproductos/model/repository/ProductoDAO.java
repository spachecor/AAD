package com.spachecor.proyectoproductos.model.repository;

import com.spachecor.proyectoproductos.model.entity.Producto;
import com.spachecor.proyectoproductos.model.util.Productos;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

/**
 * Clase encargada que gestionar el CRUD de los productos
 * @author Selene
 * @version 1.0
 */
public class ProductoDAO {
    private File file;

    public ProductoDAO() {
        this.file = new File("src/main/resources/com/spachecor/proyectoproductos/xml/productos.xml");
    }

    /**
     * Método encargado de agregar productos al XML
     * @param producto El producto a agregar
     * @return true o false dependiendo de si se agrega o no
     */
    public boolean agregarProducto(Producto producto) {
        Productos productos = new Productos();
        if (file.exists()) {//si ya existe, primero lo leemos para tomar los productos ya existentes
            productos = this.getProductos();
        }
        productos.getProductos().add(producto);
        return this.constructorXML(productos);
    }

    /**
     * Método encargado de eliminar productos del XML
     * @param producto El producto a eliminar
     * @return true o false dependiendo de si se ha eliminado o no
     */
    public boolean eliminarProducto(Producto producto) {
        Productos productos = this.getProductos();
        boolean eliminacion = productos.getProductos().remove(producto);
        if(eliminacion) {
            this.constructorXML(productos);
            return true;
        }else return false;
    }

    /**
     * Método encargado de actualizar un producto en el XML
     * @param nombre El nombre del producto a actualizar
     * @param producto Una copia del producto con los atributos ya modificados
     * @return true o false según si se ha modificado o no
     */
    public boolean actualizarProducto(String nombre, Producto producto) {
        Productos productos = this.getProductos();
        for(Producto productoTemp : productos.getProductos()) {
            if(productoTemp.getNombre().equals(nombre)) {
                productoTemp.setNombre(producto.getNombre());
                productoTemp.setPrecio(producto.getPrecio());
                productoTemp.setCantidad(producto.getCantidad());
            }
        }
        this.constructorXML(productos);
        return true;
    }

    /**
     * Método que obtiene un objeto del tipo Productos que es una colección de objetos del tipo Producto
     * del XML
     * @return Un objeto del tipo Productos
     * @see Productos
     * @see Producto
     */
    public Productos getProductos() {
        Productos productos = new Productos();
        if(!file.exists()){
            return productos;
        }
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(Productos.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            productos = (Productos) unmarshaller.unmarshal(file);
        }catch (Exception e) {
            System.out.println("Problema al recuperar los productos");
        }
        return productos;
    }

    /**
     * Método que construye el documento XML con la lista de Productos a almacenar en el
     * @param productos Los productos a almacenar en el XML
     * @return true o false según si se ha generado correctamente
     */
    public boolean constructorXML(Productos productos){
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(Productos.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.marshal(productos, file);
            return Boolean.TRUE;
        }catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
