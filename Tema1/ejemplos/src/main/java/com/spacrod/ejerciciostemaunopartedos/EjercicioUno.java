package com.spacrod.ejerciciostemaunopartedos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class EjercicioUno {
    public static void main(String[] args) {
        /*
        * Ejercicio 1: Escribir un Mensaje. Crea un programa que pida al usuario que ingrese un mensaje y lo escriba en
        * un fichero de texto llamado mensaje.txt. Asegúrate de que el fichero se sobrescriba si ya existe.
        * */
        Scanner sc = new Scanner(System.in);
        System.out.println("Por favor, ingrese un mensaje: ");
        String mensaje = sc.nextLine();
        File file = new File("documentos/ejerciciostemaunopartedos/mensaje.txt");
        try{
            if(!file.exists())file.createNewFile();
            Files.writeString(file.toPath(), mensaje);
            System.out.println("Mensaje enviado exitosamente");
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Terrible! Error, mensaje no enviado");
        }
        sc.close();
    }
}
