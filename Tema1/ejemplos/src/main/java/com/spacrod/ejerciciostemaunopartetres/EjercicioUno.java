package com.spacrod.ejerciciostemaunopartetres;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class EjercicioUno {
    public static void main(String[] args) {
        /*
        * Ejercicio 1: Copiar un Fichero. Crea un programa que copie un fichero de texto llamado texto.txt a un nuevo
        * fichero llamado copia_texto.txt. Asegúrate de que se sobrescriba si ya existe
        * */
        String pathOriginal = "documentos/ejerciciostemaunopartetres/texto.txt";
        String pathCopia = "documentos/ejerciciostemaunopartetres/copia_texto.txt";
        try{
            Files.copy(Path.of(pathOriginal), Path.of(pathCopia), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Fichero copiado correctamente");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
