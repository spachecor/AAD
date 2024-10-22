package com.spacrod;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class EjemploCatorce {
    public static void main(String[] args) {
        try {
            File file = new File("xml/biblioteca.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Libros.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Libros libros = (Libros) jaxbUnmarshaller.unmarshal(file);

            // Mostrar los libros leídos
            for (Libro libro : libros.getListaLibros()) {
                System.out.println("Título: " + libro.getTitulo() + ", Autor: " + libro.getAutor());
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
