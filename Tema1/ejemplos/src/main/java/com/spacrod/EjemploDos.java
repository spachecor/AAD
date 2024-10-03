package com.spacrod;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class EjemploDos {
    public static void main(String[] args) throws IOException {
        Path sourcePath = Paths.get("documentos/ejemploCopiar.txt");
        Path destinationPath = Paths.get("documentos/destino.txt");

        // Copiar un fichero
        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

        // Leer todas las líneas de un fichero
        List<String> lineasUno = Files.readAllLines(sourcePath);
        for (String linea : lineasUno) {
            System.out.println(linea);
        }

        List<String> lineasDos = Files.readAllLines(destinationPath);
        for (String linea : lineasDos) {
            System.out.println(linea);
        }
        String cadena = "\nSoy una nueva linea";
        byte[] arrayBytes = cadena.getBytes();
        Files.write(destinationPath, arrayBytes, StandardOpenOption.APPEND);

        List<String> lineasTres = Files.readAllLines(destinationPath);
        for (String linea : lineasTres) {
            System.out.println(linea);
        }

    }
}
