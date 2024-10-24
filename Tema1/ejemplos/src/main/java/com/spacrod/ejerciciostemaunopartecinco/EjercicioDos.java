package com.spacrod.ejerciciostemaunopartecinco;

import com.spacrod.Libro;
import com.spacrod.Libros;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class EjercicioDos {
    public static void main(String[] args) {
        /*
        * 1- Define la clase Libros que contenga una lista de objetos Libro.
        * 2- Crea una clase LeerLibrosXMLque use Unmarshaller para deserializar el XML y mostrar todos los títulos y
        * autores en la consola.
        * */
        try {
            File file = new File("xml/libros.xml");
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
