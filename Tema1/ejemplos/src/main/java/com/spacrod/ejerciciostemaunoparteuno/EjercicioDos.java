package com.spacrod.ejerciciostemaunoparteuno;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class EjercicioDos {
    public static void main(String[] args) {
        /*
        * Ejercicio 2: Leer un Nombre
        * Escribe un programa que lea el contenido del fichero nombre.bin y lo imprima en la consola.
        * */
        String path = "documentos/ejerciciostemaunoparteuno/nombre.bin";
        try{
            //metemos en un array de bytes el nombre
            byte[] nombre = Files.readAllBytes(Path.of(path));
            String nombreTexto = new String(nombre, StandardCharsets.UTF_8);
            System.out.println("El nombre contenido era: "+nombreTexto);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
