package com.spacrod.ejerciciostemaunopartetres;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class EjercicioSiete {
    public static void main(String[] args) {
        /*
        * Ejercicio 7: Mover Todos los Ficheros. Escribe un programa que mueva todos los ficheros de un directorio
        * descargas a un directorio documentos. Asegúrate de que el directorio documentos existe o créalo si no.
        * */
        String pathDirectorioOrigen = "documentos/ejerciciostemaunopartetres/descargas";
        String pathDirectorioDestino = "documentos/ejerciciostemaunopartetres/documentos";
        try{
            //si el directorio documentos no existe lo creamos
            if(!Files.exists(Path.of(pathDirectorioDestino)))Files.createDirectory(Path.of(pathDirectorioDestino));
            File directorioOrigen = new File(pathDirectorioOrigen);
            File[] archivosDirectorioOrigen = directorioOrigen.listFiles();
            if (archivosDirectorioOrigen != null && archivosDirectorioOrigen.length > 0){
                for(File archivo : archivosDirectorioOrigen){
                    Files.move(
                            archivo.toPath(),
                            new File(pathDirectorioDestino + "/" + archivo.getName()).toPath()
                    );
                }
                System.out.println("Archivos movidos correctamente");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
