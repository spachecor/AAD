package com.spacrod;

import com.spacrod.entity.Producto;
import com.spacrod.repository.ProductoDAO;

public class Main {
    public static void main(String[] args) {
        ProductoDAO productoDAO = new ProductoDAO();
        Producto salsaPesto = new Producto();
        salsaPesto.setNombre("Salsa Pesto");
        salsaPesto.setPrecio(3.5D);
        salsaPesto.setCantidad(3);
        System.out.println(productoDAO.agregarProducto(salsaPesto)?"Producto ingresado correctamente":"Error al ingresar el producto");
        productoDAO.getProductos().getProductos().forEach(System.out::println);
    }
}