package com.spacrod.ejerciciostemaunopartedos;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EjercicioTres {
    public static void main(String[] args) {
        /*
        * Ejercicio 3: Guardar Varias Líneas. Crea un programa que pida al usuario que ingrese tres líneas de texto y
        * las escriba en un fichero de texto llamado lineas.txt, asegurándote de que cada línea se guarde en una línea
        * separada del fichero.
        * */
        String path = "documentos/ejerciciostemaunopartedos/lineas.txt";
        List<String> lineas = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese tres líneas de texto según le vayamos indicando.\nIndique la primera: ");
        lineas.add(sc.nextLine());
        System.out.println("Indique la segunda: ");
        lineas.add(sc.nextLine());
        System.out.println("Indique la tercera: ");
        lineas.add(sc.nextLine());
        try{
            File file = new File(path);
            if(!file.exists())file.createNewFile();
            Files.write(Path.of(path), lineas);
            System.out.println("Fichero escrito correctamente");
        }catch(Exception e){
            System.out.println("No se ha podido escribir el fichero");
            e.printStackTrace();
        }
    }
}
