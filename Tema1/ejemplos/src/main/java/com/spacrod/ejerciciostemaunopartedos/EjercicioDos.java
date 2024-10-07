package com.spacrod.ejerciciostemaunopartedos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class EjercicioDos {
    public static void main(String[] args) {
        /*
         * Ejercicio 2: Leer un Mensaje. Escribe un programa que lea el contenido del fichero mensaje.txt y lo imprima en la
         * consola.
         * */
        String path = "documentos/ejerciciostemaunopartedos/mensaje.txt";
        try{
            if(!new File(path).exists())throw new FileNotFoundException(path);
            String mensaje = Files.readString(Path.of(path));
            System.out.println(mensaje);
        }catch(IOException e){
            System.out.println("No se pudo recuperar el contenido del archivo");
            e.printStackTrace();
        }
    }
}
