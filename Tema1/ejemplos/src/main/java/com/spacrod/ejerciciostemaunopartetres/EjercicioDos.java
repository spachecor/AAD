package com.spacrod.ejerciciostemaunopartetres;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class EjercicioDos {
    public static void main(String[] args) {
        /*
        * Ejercicio 2: Mover un Fichero. Escribe un programa que mueva el fichero copia_texto.txt a un directorio
        * llamado backup. Si el directorio no existe, debes crearlo.
        * */
        String pathBackup = "documentos/ejerciciostemaunopartetres/backup";
        String pathCopiaTexto = "documentos/ejerciciostemaunopartetres/copia_texto.txt";
        try{
            if(!Files.exists(Path.of(pathBackup))){
                Files.createDirectory(Path.of(pathBackup));
            }
            Files.move(Path.of(pathCopiaTexto), Path.of(pathBackup+"/copia_texto.txt"));
            System.out.println("Fichero movido correctamente");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
