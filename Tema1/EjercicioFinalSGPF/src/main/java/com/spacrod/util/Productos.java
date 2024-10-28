package com.spacrod.util;

import com.spacrod.entity.Producto;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "productos")
public class Productos {
    private List<Producto> productos;
    public Productos(){
        productos = new ArrayList<>();
    }
    public void agregarProducto(Producto producto){
        productos.add(producto);
    }

    /**
     * Método que busca un producto en concreto por su nombre dentro de la lista de productos
     * @param nombre El nombre del producto a buscar
     * @return El objeto que coincide con ese nombre
     */
    public Producto buscarProducto(String nombre){
        Producto producto = null;
        Producto comparador = new Producto();
        comparador.setNombre(nombre);
        for(Producto productoTemp: productos){
            if(productoTemp.compareTo(comparador)==0){
                producto = productoTemp;
            }
        }
        return producto;
    }

    @XmlElement(name = "producto")
    public List<Producto> getProductos() {
        return productos;
    }
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
