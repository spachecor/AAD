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
    private File file;

    public ProductoDAO() {
        this.file = new File("src/main/resources/xml/productos.xml");
    }

    public boolean agregarProducto(Producto producto) {
        Productos productos = new Productos();
        if (file.exists()) {//si ya existe, primero lo leemos para tomar los productos ya existentes
            productos = this.getProductos();
        }
        productos.getProductos().add(producto);
        return this.constructorXML(productos);
    }
    public boolean eliminarProducto(Producto producto) {
        Productos productos = this.getProductos();
        boolean eliminacion = productos.getProductos().remove(producto);
        if(eliminacion) {
            this.constructorXML(productos);
            return true;
        }else return false;
    }
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
