package com.spacrod.ejerciciostemaunopartedos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class EjercicioOcho {
    public static void main(String[] args) {
        /*
        * Ejercicio 8: Leer Lista de Libros. Crea un programa que lea el fichero libros.txt y muestre todos los libros
        * en la consola, enumerándolos con un número.
        * */
        String path = "documentos/ejerciciostemaunopartedos/libros.txt";
        List<Libro> libros = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            if(!new File(path).exists())throw new FileNotFoundException(path);
            String libro = reader.readLine();
            while(libro != null){
                libros.add(Libro.toObject(libro));
                libro = reader.readLine();
            }
            for(int i = 1; i <= libros.size(); i++){
                System.out.println(i + ": " + libros.get(i-1).toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
