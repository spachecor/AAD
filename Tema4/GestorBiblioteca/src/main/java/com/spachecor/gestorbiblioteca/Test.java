package com.spachecor.gestorbiblioteca;

import com.spachecor.gestorbiblioteca.model.dao.LibroDAOGenericImpl;
import com.spachecor.gestorbiblioteca.model.entity.Libro;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        LibroDAOGenericImpl libroDAO = new LibroDAOGenericImpl();
        List<Libro> libros = libroDAO.listar();
        for (Libro libro : libros) {
            System.out.println(libro);
        }
    }
}
