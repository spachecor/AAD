package com.spacrod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EjemploCuatro {
    public static void main(String[] args) {
        Path path = Paths.get("documentos/documentoBinario.bin");
        String nombre = "Selene";
        byte[] nombreToByte = nombre.getBytes();
        byte[] datos = { 65, 66, 67, 68, 69 }; // Datos en formato binario (A, B, C, D, E)

        try {
            // Escribir los datos en el fichero binario
            Files.write(path, nombreToByte);
            System.out.println("Datos escritos correctamente en " + path.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
