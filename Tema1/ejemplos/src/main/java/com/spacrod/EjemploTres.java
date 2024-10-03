package com.spacrod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EjemploTres {
    public static void main(String[] args) {
        Path directorio = Paths.get("documentos"); // Reemplaza el entrecomillado con tu ruta

        try {
            // Listar y imprimir los nombres de los ficheros en el directorio
            Files.list(directorio).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
