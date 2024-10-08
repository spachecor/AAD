package com.spacrod.ejerciciostemaunopartedos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class EjercicioCinco {
    public static void main(String[] args) {
        /*
        * Ejercicio 5: Clase Libro. Crea una clase Libro que contenga atributos como título y autor. Escribe un
        * programa que cree un objeto Libro y lo guarde en un fichero de texto llamado libro.txt, utilizando el formato
        * “Título: {titulo}, Autor: {autor}”.
        * */
        String path = "documentos/ejerciciostemaunopartedos/libro.txt";
        Libro libro = new Libro("Hola mundo", "MundoLovers");
        try{
            File file = new File(path);
            if(!file.exists())file.createNewFile();
            Files.writeString(Path.of(path), libro.toString());
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
