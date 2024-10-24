package com.spacrod.ejerciciostemaunopartecinco;

import com.spacrod.Libro;
import com.spacrod.Libros;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class EjercicioTres {
    public static void main(String[] args) {
        /*
        * Crea un método en la clase LeerLibrosXML que exporte la lista de libros a un archivo CSV llamado libros.csv.
        * Implementa otro método que lea el archivo libros.csv y cree objetos Libroa partir de los datos.
        * El archivo CSV debe tener el siguiente formato:
        * titulo,autor
        * El Principito, Antoine de Saint-Exupéry
        * 1984,George Orwell
        * */
        EjercicioTres ejercicioTres = new EjercicioTres();
        ejercicioTres.exportarALibrosCSV();
    }
    // Método para exportar a CSV
    public void exportarALibrosCSV() {
        String pathLibros = "xml/libros.xml";
        String pathCsv = "csv/libros.csv";
        try {
            File file = new File(pathLibros);
            JAXBContext jaxbContext = JAXBContext.newInstance(Libros.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Libros libros = (Libros) jaxbUnmarshaller.unmarshal(file);

            //escribimos la primera linea que determina el orden
            Files.writeString(Path.of(pathCsv), "autor, titulo\n", StandardCharsets.UTF_8);
            //escribimos los libros leídos
            for (Libro libro : libros.getListaLibros()) {
                Files.writeString(Path.of(pathCsv), libro.getAutor()+","+libro.getTitulo()+"\n", StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            }
            System.out.println("Proceso terminado. xml/libros.xml -> csv/libros.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para importar desde CSV
    public List<Libro> importarDesdeLibrosCSV() {
        List<Libro> libros = new ArrayList<Libro>();
        return libros;
    }
}
