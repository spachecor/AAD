package com.spacrod;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EjemploOcho {
    public static void main(String[] args) {
        Path path = Paths.get("documentos/ejemploseis.bin"); // Reemplaza con tu ruta

        try (InputStream inputStream = Files.newInputStream(path)) {
            int byteDato;
            while ((byteDato = inputStream.read()) != -1) {
                //System.out.printf("%02X ", byteDato); // Imprime en formato hexadecimal
                System.out.print(String.format("%8s", Integer.toBinaryString(byteDato)).replace(' ', '0') + " "); // Imprime en binario
            }
            System.out.println(); // Nueva línea al final
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el fichero.");
            e.printStackTrace();
        }
    }
}
