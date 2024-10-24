package com.spacrod.service.xml;

import com.spacrod.entity.Producto;

import java.util.List;

/**
 * Clase encargada que gestionar el CRUD de los productos
 * @author Selene
 * @version 1.0
 */
public class CRUDProducto {
    public boolean agregarProducto(Producto producto) {
        return true;
    }
    public boolean eliminarProducto(Producto producto) {
        return true;
    }
    public boolean actualizarProducto(Producto producto) {
        return true;
    }
    public Producto getProducto(String nombre) {
        return null;
    }
    public List<Producto> getProductos() {
        return null;
    }
}
