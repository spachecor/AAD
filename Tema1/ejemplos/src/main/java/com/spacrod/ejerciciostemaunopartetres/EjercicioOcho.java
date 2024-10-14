package com.spacrod.ejerciciostemaunopartetres;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class EjercicioOcho {
    public static void main(String[] args) {
        /*
        * Ejercicio 8: Copiar con Opciones. Crea un programa que copie un fichero importante.txt a seguridad.txt,
        * pero si seguridad.txt ya existe, el programa debe preguntarte si deseas sobrescribirlo (sí/no).
        * */
        Scanner sc = new Scanner(System.in);
        String pathImportante = "documentos/ejerciciostemaunopartetres/importante.txt";
        String pathSeguridad = "documentos/ejerciciostemaunopartetres/seguridad.txt";
        try{
            File seguridad = new File(pathSeguridad);
            if(seguridad.exists()){
                System.out.println("El archivo seguridad.txt ya existe, desea sobreescribirlo?(si/no)");
                String respuesta = sc.nextLine();
                if(respuesta.equals("si")) {
                    Files.copy(Path.of(pathImportante), Path.of(pathSeguridad), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("El archivo seguridad.txt se ha sobreescrito");
                }
                else System.out.println("No se reescribirá el archivo, se queda como estaba");
            }else {
                Files.copy(Path.of(pathImportante), Path.of(pathSeguridad));
                System.out.println("Archivo copiado correctamente");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
