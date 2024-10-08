package com.spacrod.ejerciciostemaunopartedos;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;

public class EjercicioSeis {
    public static void main(String[] args) {
        /*
        * Ejercicio 6: Leer Objeto Libro. Escribe un programa que lea el fichero libro.txt y muestre los atributos del
        * objeto Libro en la consola, separando el título y el autor.
        * */
        String path = "documentos/ejerciciostemaunopartedos/libro.txt";
        try{
            if(!new File(path).exists())throw new FileNotFoundException(path);
            String libro = Files.readString(Path.of(path));
            System.out.println(Libro.toObject(libro));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
