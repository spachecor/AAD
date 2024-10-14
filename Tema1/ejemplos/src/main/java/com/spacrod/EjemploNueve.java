package com.spacrod;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class EjemploNueve {
    public static void main(String[] args) {
        Path origen = Paths.get("documentos/ejemplo.txt");
        Path destino = Paths.get("documentos/ejemploCopiarEjemploNueve.txt");
        //OJO QUE EL ARCHIVO DONDE COPIAMOS NO DEBE ESTAR YA CREADO
        try {
            //Copiar el fichero
            Files.copy(origen, destino);
            System.out.println("Fichero copiado correctamente a " + destino);
        } catch (IOException e) {
            System.out.println("Ocurrió un error al copiar el fichero.");
            e.printStackTrace();
        }
    }
}
