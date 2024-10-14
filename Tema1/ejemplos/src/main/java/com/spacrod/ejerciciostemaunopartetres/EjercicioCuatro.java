package com.spacrod.ejerciciostemaunopartetres;

import java.nio.file.Files;
import java.nio.file.Path;

public class EjercicioCuatro {
    public static void main(String[] args) {
        /*
        * Ejercicio 4: Mover y Renombrar. Crea un programa que mueva un fichero llamado documento.txt a un nuevo
        * directorio llamado archivos y lo renombre como documento_final.txt. Si el directorio no existe, debe crearlo.
        * */
        String pathDocumento = "documentos/ejerciciostemaunopartetres/documento.txt";
        String pathArchivos = "documentos/ejerciciostemaunopartetres/archivos";
        String pathRenombrado = "documentos/ejerciciostemaunopartetres/archivos/documento_final.txt";
        try{
            if(!Files.exists(Path.of(pathArchivos)))Files.createDirectory(Path.of(pathArchivos));
            Files.move(Path.of(pathDocumento), Path.of(pathRenombrado));
            System.out.println("Archivo movido correctamente");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
