package com.spacrod;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class EjemploSeis {
    public static void main(String[] args) {
        Path path = Paths.get("documentos/ejemploseis.bin"); // Reemplaza con tu ruta

        String nombre = "Teo Rojas Mata";

        byte[] datosNombre = nombre.getBytes();

        try {
            // Escribir los datos en el fichero binario
            Files.write(path, datosNombre);
            System.out.println("Datos escritos correctamente en " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
