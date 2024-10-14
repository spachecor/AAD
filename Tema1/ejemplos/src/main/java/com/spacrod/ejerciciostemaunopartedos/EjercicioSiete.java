package com.spacrod.ejerciciostemaunopartedos;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EjercicioSiete {
    public static void main(String[] args) {
        /*
        * Ejercicio 7: Lista de Libros. Escribe un programa que permita al usuario ingresar varios libros
        * (título y autor) y los almacene en un fichero de texto llamado libros.txt, uno por línea.
        * */
        Scanner sc = new Scanner(System.in);
        List<Libro> libros = new ArrayList<>();
        boolean aniadir = true;
        int aux = 1;
        System.out.println("Vamos a registrar varios libros: ");
        do{
            Libro libroTemp = new Libro();
            System.out.println("Introduzca el titulo del "+aux+"º libro: ");
            libroTemp.setTitulo(sc.nextLine());
            System.out.println("Introduzca el autor del "+aux+"º libro: ");
            libroTemp.setAutor(sc.nextLine());
            aux++;
            libros.add(libroTemp);
            System.out.println("¿Desea seguir agregando libros?(si/no)");
            String seguir = sc.nextLine();
            if(seguir.equals("no")){
                aniadir = false;
                System.out.println("Libros añadidos correctamente");
            }
        }while(aniadir);
        String path = "documentos/ejerciciostemaunopartedos/libros.txt";
        try{
            File file = new File(path);
            if(!file.exists())file.createNewFile();
            for (Libro libro : libros) {
                Files.writeString(Path.of(path), libro.toString()+"\n", StandardOpenOption.APPEND);
            }
            System.out.println("Libros introducidos");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
