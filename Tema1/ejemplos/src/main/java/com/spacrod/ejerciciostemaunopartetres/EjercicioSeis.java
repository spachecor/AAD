package com.spacrod.ejerciciostemaunopartetres;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class EjercicioSeis {
    public static void main(String[] args) {
        /*
        * Ejercicio 6: Copia de Múltiples Ficheros. Crea un programa que copie múltiples ficheros
        * (por ejemplo, fichero1.txt, fichero2.txt, fichero3.txt) desde un directorio origen a un directorio destino,
        * sobrescribiendo ficheros existentes.
        * */
        String pathDirectorioOrigen = "documentos/ejerciciostemaunopartetres/directoriodestino";
        String pathDirectorioDestino = "documentos/ejerciciostemaunopartetres/directorioorigen";
        try{
            File directorioOrigen = new File(pathDirectorioOrigen);
            File[] archivosDirectorioOrigen = directorioOrigen.listFiles();
            if (archivosDirectorioOrigen != null && archivosDirectorioOrigen.length > 0){
                for(File archivo : archivosDirectorioOrigen){
                    Files.copy(
                            archivo.toPath(),
                            new File(pathDirectorioDestino + "/" + archivo.getName()).toPath(),
                            StandardCopyOption.REPLACE_EXISTING
                    );
                }
                System.out.println("Archivos copiados correctamente");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
