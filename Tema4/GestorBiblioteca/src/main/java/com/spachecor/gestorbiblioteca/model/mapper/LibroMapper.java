package com.spachecor.gestorbiblioteca.model.mapper;

import com.spachecor.gestorbiblioteca.model.entity.Libro;
import org.w3c.dom.Element;

/**
 * Clase LibroMapper que sirve para definir como se mapea un libro de XML a Libro y viceversa.
 * @author Selene
 * @version 1.0
 */
public class LibroMapper implements Mapper<Libro> {
    @Override
    public Libro deXML(String xml) {
        Libro libro = new Libro();
        try {
            Element root = this.obtenerElementoRaiz(xml);

            libro.setId(Integer.parseInt(root.getElementsByTagName("id").item(0).getTextContent()));
            libro.setTitulo(root.getElementsByTagName("title").item(0).getTextContent());
            libro.setAutor(root.getElementsByTagName("author").item(0).getTextContent());
            libro.setAnioPublicacion(Integer.parseInt(root.getElementsByTagName("publicationYear").item(0).getTextContent()));
            libro.setCategoria(root.getElementsByTagName("category").item(0).getTextContent());
            libro.setSubCategoria(root.getElementsByTagName("subcategory").item(0).getTextContent());
            libro.setIsbn(Long.parseLong(root.getElementsByTagName("isbn").item(0).getTextContent()));
            libro.setEditorial(root.getElementsByTagName("publisher").item(0).getTextContent());
            libro.setNumeroPaginas(Integer.parseInt(root.getElementsByTagName("pages").item(0).getTextContent()));
            libro.setNumeroCopiasDisponibles(Integer.parseInt(root.getElementsByTagName("availableCopies").item(0).getTextContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return libro;
    }

    @Override
    public String aXML(Libro libro) {
        return "<book>" +
                "<id>" + libro.getId() + "</id>" +
                "<title>" + libro.getTitulo() + "</title>" +
                "<author>" + libro.getAutor() + "</author>" +
                "<publicationYear>" + libro.getAnioPublicacion() + "</publicationYear>" +
                "<category>" + libro.getCategoria() + "</category>" +
                "<subcategory>" + libro.getSubCategoria() + "</subcategory>" +
                "<isbn>" + libro.getIsbn() + "</isbn>" +
                "<publisher>" + libro.getEditorial() + "</publisher>" +
                "<pages>" + libro.getNumeroPaginas() + "</pages>" +
                "<availableCopies>" + libro.getNumeroCopiasDisponibles() + "</availableCopies>" +
                "</book>";
    }
}
