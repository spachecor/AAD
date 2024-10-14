package com.spacrod.ejerciciostemaunopartedos;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EjercicioNueve {
    public static void main(String[] args) {
        /*
        * Ejercicio 9: Modificar un Libro. Escribe un programa que lea el fichero libros.txt, permita al usuario
        * modificar el título de un libro específico y luego escriba la lista actualizada de libros en el mismo fichero.
        * */
        //primero leemos el fichero
        Scanner sc = new Scanner(System.in);
        String path = "documentos/ejerciciostemaunopartedos/libros.txt";
        List<Libro> libros = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            if(!new File(path).exists())throw new FileNotFoundException(path);
            String libro = reader.readLine();
            while(libro != null){
                libros.add(Libro.toObject(libro));
                libro = reader.readLine();
            }
            System.out.println("Elige un libro seleccionándolo por su número");
            for(int i = 1; i <= libros.size(); i++){
                System.out.println(i + ": " + libros.get(i-1).toString());
            }
            int opcion = sc.nextInt();
            System.out.println("La opción ejegida es: \n"+libros.get(opcion-1).toString());
            System.out.println("Escribe el nuevo título(Si no desea modificar, vuelva a escribir el actual): ");
            //reseteamos el scanner
            sc.nextLine();
            String titulo = sc.nextLine();
            System.out.println("Escribe el nuevo autor(Si no desea modificar, vuelva a escribir el actual): ");
            String autor = sc.nextLine();
            libros.get(opcion-1).setTitulo(titulo);
            libros.get(opcion-1).setAutor(autor);
            //vaciamos el fichero
            FileWriter fw = new FileWriter(path);
            fw.flush();
            fw.close();
            //ahora lo volvemos a escribir
            for (Libro libroTemp : libros) {
                Files.writeString(Path.of(path), libroTemp.toString()+"\n", StandardOpenOption.APPEND);
            }
            System.out.println("Libros modificados correctamente");
        }catch (Exception e){
            e.printStackTrace();
        }
        sc.close();
    }
}
