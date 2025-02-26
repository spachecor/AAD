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
        LibroDAOGenericImpl libroDAO = new LibroDAOGenericImpl();
        Prestamo prestamo = new Prestamo(4, usuarioDAO.buscarPorId(4).get(), libroDAO.buscarPorId(6).get(), LocalDate.now(), LocalDate.now().plusDays(7));
        //prestamoDAO.crear(prestamo);
    }
}
