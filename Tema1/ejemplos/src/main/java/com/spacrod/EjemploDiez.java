package com.spacrod;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class EjemploDiez {
    public static void main(String[] args) {
        Path origen = Paths.get("documentos/ejemplo.txt"); // Reemplaza con tu ruta
        Path destino = Paths.get("documentos/ejemploMoverEjemploNueve.txt");

        try {
            // Mover el fichero
            Files.move(origen, destino);
            System.out.println("Fichero movido correctamente a " + destino);
            //OJO QUE EL FICHERO ORIGINAL SE PIERDE
        } catch (IOException e) {
            System.out.println("Ocurrió un error al mover el fichero.");
            e.printStackTrace();
        }
    }
}
