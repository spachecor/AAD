package com.spacrod.ejerciciostemaunoparteuno;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class EjercicioCuatro {
    public static void main(String[] args) {
        /*
        * Ejercicio 4: Leer un Objeto Persona
        * Escribe un programa que lea el objeto Persona almacenado en persona.bin y muestre sus atributos en la consola.
        * */
        Persona persona = null;
        try(FileInputStream fileIn = new FileInputStream("documentos/ejerciciostemaunoparteuno/persona.bin")){
            ObjectInputStream in = new ObjectInputStream(fileIn);
            persona = (Persona) in.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(persona);
    }
}
