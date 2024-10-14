package com.spacrod.ejerciciostemaunopartetres;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class EjercicioCinco {
    public static void main(String[] args) {
        /*
        * Ejercicio 5: Copiar Directorio. Escribe un programa que copie tó el contenido de un directorio origen a un
        * directorio destino. Asegúrate de que el directorio de destino se cree si no existe.
        * */
        String pathDirectorioOrigen = "documentos/ejerciciostemaunopartetres/directorioorigen";
        String pathDirectorioDestino = "documentos/ejerciciostemaunopartetres/directoriodestino";
        try{
            File directorioOrigen = new File(pathDirectorioOrigen);
            File[] archivosDirectorioOrigen = directorioOrigen.listFiles();
            if (archivosDirectorioOrigen != null && archivosDirectorioOrigen.length > 0){
                for(File archivo : archivosDirectorioOrigen){
                    Files.copy(archivo.toPath(), new File(pathDirectorioDestino + "/" + archivo.getName()).toPath());
                }
                System.out.println("Archivos copiados correctamente");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
