package com.spacrod.repository;

import com.spacrod.entity.Producto;
import com.spacrod.util.Productos;
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
    private String pathName;

    public ProductoDAO() {
        this.pathName = "src/main/resources/xml/productos.xml";;
    }

    public boolean agregarProducto(Producto producto) {
        Productos productos = new Productos();
        //primero comprobamos si el xml existe
        File file = new File(pathName);
        if (file.exists()) {//si ya existe, primero lo leemos para tomar los productos ya existentes
            productos = this.getProductos();
        }
        productos.getProductos().add(producto);
        return this.constructorXML(productos, file);
    }
    public boolean eliminarProducto(Producto producto) {
        return true;
    }
    public boolean actualizarProducto(Producto producto) {
        return true;
    }
    public Producto getProducto(String nombre){
        return null;
    }
    public Productos getProductos() {
        File file = new File(pathName);
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
    private boolean constructorXML(Productos productos, File file){
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
