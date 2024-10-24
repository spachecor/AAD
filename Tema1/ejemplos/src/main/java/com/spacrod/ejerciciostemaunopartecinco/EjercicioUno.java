package com.spacrod.ejerciciostemaunopartecinco;

import com.spacrod.Libro;
import com.spacrod.Libros;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;

public class EjercicioUno {
    public static void main(String[] args) {
        /*
        * 1- Crea una nueva clase GuardarLibroXML(la llamaré EjercicioUno para mayor orden).
        * 2- En el método main, crea un objeto Libro, establece su título y autor, y guarda el objeto en un fichero
        * llamado nuevo_libro.xml.
        * */
        Libro elPrincipito = new Libro();
        elPrincipito.setTitulo("El Principito");
        elPrincipito.setAutor("Antoine de Saint-Exupéry");
        Libro milNovecientosOchentaYCuatro = new Libro();
        milNovecientosOchentaYCuatro.setTitulo("1984");
        milNovecientosOchentaYCuatro.setAutor("George Orwell");
        Libros libros = new Libros();
        libros.getListaLibros().add(elPrincipito);
        libros.getListaLibros().add(milNovecientosOchentaYCuatro);
        try {
            //Creamos el contecto JAXB
            JAXBContext jc = JAXBContext.newInstance(Libros.class);
            //creamos el marchaller para convertir objetos a xml
            Marshaller m = jc.createMarshaller();
            //configuramos la salida del xml con formato legible
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            //escribimos en el archivo xml
            m.marshal(libros, new File("xml/libros.xml"));
            System.out.println("Libros agregados correctamente");
            m.marshal(libros, System.out);
        }catch (JAXBException e){
            e.printStackTrace();
        }
    }
}
