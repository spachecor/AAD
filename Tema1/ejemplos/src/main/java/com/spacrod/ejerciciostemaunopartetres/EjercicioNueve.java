package com.spacrod.ejerciciostemaunopartetres;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class EjercicioNueve {
    public static void main(String[] args) {
        /*
        * Ejercicio 9: Registro de Copias. Desarrolla un programa que copie un fichero y registre la acción en un
        * fichero de log registro.txt, indicando qué fichero fue copiado y a dónde se copió. Si el fichero de log
        * no existe, créalo.
        * */
        Scanner sc = new Scanner(System.in);
        String pathOrigen = "documentos/ejerciciostemaunopartetres/importante.txt";
        String pathDestino = "documentos/ejerciciostemaunopartetres/seguridad.txt";
        try{
            File seguridad = new File(pathDestino);
            if(seguridad.exists()){
                System.out.println("El archivo seguridad.txt ya existe, desea sobreescribirlo?(si/no)");
                String respuesta = sc.nextLine();
                if(respuesta.equals("si")) {
                    Files.copy(Path.of(pathOrigen), Path.of(pathDestino), StandardCopyOption.REPLACE_EXISTING);
                    EjercicioNueve.registrar(pathOrigen, pathDestino);
                    System.out.println("El archivo seguridad.txt se ha sobreescrito");
                }
                else System.out.println("No se reescribirá el archivo, se queda como estaba");
            }else {
                Files.copy(Path.of(pathOrigen), Path.of(pathDestino));
                EjercicioNueve.registrar(pathOrigen, pathDestino);
                System.out.println("Archivo copiado correctamente");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void registrar(String pathOrigen, String pathDestino){
        String pathLog = "documentos/ejerciciostemaunopartetres/registro.txt";
        try{
            File registro = new File(pathLog);
            if(!registro.exists()) Files.createFile(Path.of(pathLog));
            Files.writeString(Path.of(pathLog), "Archivo copiado desde: "+pathOrigen+" hasta: "+pathDestino+"\n", StandardOpenOption.APPEND);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
