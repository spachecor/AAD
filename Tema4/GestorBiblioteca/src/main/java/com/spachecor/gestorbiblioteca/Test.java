package com.spachecor.gestorbiblioteca;

import com.spachecor.gestorbiblioteca.model.dao.LibroDAOGenericImpl;
import com.spachecor.gestorbiblioteca.model.dao.PrestamoDAOGenericImpl;
import com.spachecor.gestorbiblioteca.model.dao.UsuarioDAOGenericImpl;
import com.spachecor.gestorbiblioteca.model.entity.Libro;
import com.spachecor.gestorbiblioteca.model.entity.Prestamo;
import com.spachecor.gestorbiblioteca.model.entity.Usuario;

import java.time.LocalDate;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        LibroDAOGenericImpl libroDAO = new LibroDAOGenericImpl();
        List<Libro> libros = libroDAO.listar();
        for (Libro libro : libros) {
            System.out.println(libro);
        }
        Libro libro = libroDAO.buscarPorId(1).get();
        libro.setTitulo("Ejemplo");
        libroDAO.actualizar(libro);
        UsuarioDAOGenericImpl usuarioDAO = new UsuarioDAOGenericImpl();
        List<Usuario> usuarios = usuarioDAO.listar();
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
        PrestamoDAOGenericImpl prestamoDAO = new PrestamoDAOGenericImpl();
        List<Prestamo> prestamos = prestamoDAO.listar();
        for (Prestamo prestamo : prestamos) {
            System.out.println(prestamo);
        }
    }
}
