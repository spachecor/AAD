package com.spachecor.gestorbiblioteca.model.mapper;

import com.spachecor.gestorbiblioteca.model.dao.LibroDAOGenericImpl;
import com.spachecor.gestorbiblioteca.model.dao.UsuarioDAOGenericImpl;
import com.spachecor.gestorbiblioteca.model.entity.Libro;
import com.spachecor.gestorbiblioteca.model.entity.Prestamo;
import com.spachecor.gestorbiblioteca.model.entity.Usuario;
import org.w3c.dom.Element;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Clase PrestamoMapper que sirve para definir como se mapea un prestamo de XML a Prestamo y viceversa.
 * @author Selene
 * @version 1.0
 */
public class PrestamoMapper implements Mapper<Prestamo> {
    private LibroDAOGenericImpl libroDAO;
    private UsuarioDAOGenericImpl usuarioDAO;

    public PrestamoMapper() {
        this.libroDAO = new LibroDAOGenericImpl();
        this.usuarioDAO = new UsuarioDAOGenericImpl();
    }
    @Override
    public Prestamo deXML(String xml) {
        Prestamo prestamo = new Prestamo();
        try{
            Element root = this.obtenerElementoRaiz(xml);

            Optional<Libro> oLibro = this.libroDAO.buscarPorId(Integer.parseInt(root.getElementsByTagName("bookId").item(0).getTextContent()));
            Optional<Usuario> oUsuario = this.usuarioDAO.buscarPorId(Integer.parseInt(root.getElementsByTagName("userId").item(0).getTextContent()));

            prestamo.setId(Integer.parseInt(root.getElementsByTagName("id").item(0).getTextContent()));
            prestamo.setUsuario(oUsuario.orElse(null));
            prestamo.setLibro(oLibro.orElse(null));
            prestamo.setFechaPrestamo(LocalDate.parse(root.getElementsByTagName("loanDate").item(0).getTextContent()));
            prestamo.setFechaDevolucion(LocalDate.parse(root.getElementsByTagName("returnDate").item(0).getTextContent()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return prestamo;
    }

    @Override
    public String aXML(Prestamo prestamo) {
        return "<loan>" +
                "<id>"+prestamo.getId()+"</id>" +
                "<userId>"+prestamo.getUsuario().getId()+"</userId>" +
                "<bookId>"+prestamo.getLibro().getId()+"</bookId>" +
                "<loanDate>"+prestamo.getFechaPrestamo()+"</loanDate>" +
                "<returnDate>"+prestamo.getFechaDevolucion()+"</returnDate>" +
                "</loan>";
    }
}
