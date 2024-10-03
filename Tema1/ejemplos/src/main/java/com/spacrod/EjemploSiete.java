package com.spacrod;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class EjemploSiete {
    public static void main(String[] args) {
        Path path = Paths.get("documentos/ejemploseis.bin"); // Reemplaza con tu ruta

        try {
            // Leer todos los bytes del fichero
            byte[] datos = Files.readAllBytes(path);

            // Mostrar los datos en formato hexadecimal
            for (byte b : datos) {
                System.out.printf("%02X ", b);
            }
            System.out.println(); // Nueva línea al final
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el fichero.");
            e.printStackTrace();
        }
    }
}
