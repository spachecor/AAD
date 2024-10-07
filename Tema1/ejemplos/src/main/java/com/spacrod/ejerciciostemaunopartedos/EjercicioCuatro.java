package com.spacrod.ejerciciostemaunopartedos;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class EjercicioCuatro {
    public static void main(String[] args) {
        /*
        * Ejercicio 4: Contar Líneas. Escribe un programa que lea el fichero lineas.txt y cuente cuántas líneas tiene,
        * imprimiendo el resultado en la consola.
        * */
        String path = "documentos/ejerciciostemaunopartedos/lineas.txt";
        try{
            List<String> lineas = Files.readAllLines(Path.of(path));
            lineas.forEach(System.out::println);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
