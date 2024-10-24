package com.spacrod.util;

import com.spacrod.entity.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductosList{
    private List<Producto> productos;
    public ProductosList(){
        productos = new ArrayList<Producto>();
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
    public List<Producto> getProductos() {
        return productos;
    }
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
