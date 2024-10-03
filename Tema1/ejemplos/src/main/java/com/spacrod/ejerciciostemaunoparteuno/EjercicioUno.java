package com.spacrod.ejerciciostemaunoparteuno;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class EjercicioUno {
    public static void main(String[] args) {
        /*
        * Ejercicio 1: Escribir un Nombre
        * Crea un programa que pida al usuario que ingrese su nombre y lo escriba en un fichero binario llamado
        * nombre.bin. Asegúrate de que el fichero se sobrescriba si ya existe.
        * */
        Scanner sc = new Scanner(System.in);
        //primero creamos el fichero
        String path = "documentos/ejerciciostemaunoparteuno/nombre.bin";
        File file = new File(path);
        //solicitamos el nombre
        System.out.println("Introduzca su nombre para almacenarlo en el fichero");
        String nombre = sc.nextLine();
        try{
            //si no existe el fichero, lo creamos
            if(!file.exists())file.createNewFile();
            //una vez comprobamos que hay fichero, escribimos
            Files.write(Path.of(path), nombre.getBytes());
            System.out.println("Nombre escrito correctamente");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
