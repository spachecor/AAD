package com.spacrod;

import java.nio.file.Path;
import java.nio.file.Paths;

public class EjemploUno {
    public static void main(String[] args) {
        // Definir una ruta de ejemplo (relativa)
        Path path = Paths.get("documentos/ejemplo.txt"); // Reemplaza con tu ruta

        // Obtener el nombre del fichero
        String nombreFichero = path.getFileName().toString();
        System.out.println("Nombre del fichero: " + nombreFichero);

        // Obtener la ruta del directorio que contiene el fichero
        Path directorioPadre = path.getParent();
        System.out.println("Directorio padre: " + (directorioPadre != null ? directorioPadre.toString() : "N/A"));

        // Obtener la ruta absoluta
        Path rutaAbsoluta = path.toAbsolutePath();
        System.out.println("Ruta absoluta: " + rutaAbsoluta.toString());

        /* MÁXIMO
        * // Definir una ruta de ejemplo (relativa)
        Path path = Paths.get("documentos/ejemplo.txt"); // Reemplaza con tu ruta

        // Obtener nombre del archivo o del directorio como path
        Path pathFichero = path.getFileName();
        //pasarlo a string
        String nombreFichero = pathFichero.toString();
        //imprimirlo, al ser string se imprime directamente con el sout
        System.out.println("Nombre del fichero: " + nombreFichero);

        // Obtener la ruta del directorio que contiene el fichero
        Path directorioPadre = path.getParent();
        //declaramos la variable para guardar el directorio padre, tanto si existe como si no
        String comprobacion;
        //comprobamos si el directorio padre es null
        if(directorioPadre == null) {
            comprobacion = "N/A";
        }else{
            //si no es null, existe y se puede recoger, así que lo recogemos
            comprobacion = directorioPadre.toString();
        }
        //imprimimos el directorio padre
        System.out.println("Directorio padre: " + comprobacion);

        // Obtener la ruta absoluta
        Path rutaAbsoluta = path.toAbsolutePath();
        //Pasamos la ruta absoluta a string
        String rutaAbsolutaString = rutaAbsoluta.toString();
        System.out.println("Ruta absoluta: " + rutaAbsolutaString);
        * */

        /* MÍNIMO
         * System.out.println("Nombre del fichero: " + Paths.get("documentos/ejemplo.txt").getFileName());
         * System.out.println("Directorio padre: " + (Paths.get("documentos/ejemplo.txt").getParent() != null ? Paths.get("documentos/ejemplo.txt").getParent() : "N/A"));
         * System.out.println("Ruta absoluta: " + Paths.get("documentos/ejemplo.txt").toAbsolutePath());
         * */
    }
}