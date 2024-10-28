package com.spacrod;

import com.spacrod.entity.Producto;
import com.spacrod.repository.ProductoDAO;
import com.spacrod.service.conversores.Conversor;
import com.spacrod.service.conversores.ConversorCSV;

public class Main {
    public static void main(String[] args) {
        /*ProductoDAO productoDAO = new ProductoDAO();
        Producto productoUno = new Producto();
        productoUno.setNombre("Mayonesa");
        productoUno.setCantidad(4);
        productoUno.setPrecio(1.67D);
        System.out.println(productoDAO.actualizarProducto("Mayonesa", productoUno));
        productoDAO.getProductos().getProductos().forEach(System.out::println);
        Producto productoDos = new Producto();
        productoDos.setNombre("Salsa Pesto");
        System.out.println(productoDAO.eliminarProducto(productoDos));
        productoDAO.getProductos().getProductos().forEach(System.out::println);*/
        ConversorCSV conversorCSV = new ConversorCSV();
        System.out.println(conversorCSV.obtenerExtensionDocumento("productos.html"));
    }
}