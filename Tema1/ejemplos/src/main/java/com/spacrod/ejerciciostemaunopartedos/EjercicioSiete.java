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
        Libro libroUno = new Libro();
        Libro libroDos = new Libro();
        Libro libroTres = new Libro();
        List<Libro> libros = new ArrayList<>();
        System.out.println("Vamos a registrar tres libros: ");
        System.out.println("Introduzca el titulo del 1º libro: ");
        libroUno.setTitulo(sc.nextLine());
        System.out.println("Introduzca el autor del 1º libro: ");
        libroUno.setAutor(sc.nextLine());
        System.out.println("Introduzca el titulo del 2º libro: ");
        libroDos.setTitulo(sc.nextLine());
        System.out.println("Introduzca el autor del 2º libro: ");
        libroDos.setAutor(sc.nextLine());
        System.out.println("Introduzca el titulo del 3º libro: ");
        libroTres.setTitulo(sc.nextLine());
        System.out.println("Introduzca el autor del 3º libro: ");
        libroTres.setAutor(sc.nextLine());
        libros.add(libroUno);
        libros.add(libroDos);
        libros.add(libroTres);
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
