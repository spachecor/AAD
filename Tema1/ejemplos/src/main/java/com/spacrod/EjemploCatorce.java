package com.spacrod;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class EjemploCatorce {
    public static void main(String[] args) {
        try {
            File file = new File("xml/biblioteca.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Libro.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Libro libro = (Libro) jaxbUnmarshaller.unmarshal(file);
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
