package com.spacrod.ejerciciostemaunoparteuno;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class EjercicioCuatro {
    public static void main(String[] args) {
        /*
        * Ejercicio 4: Leer un Objeto Persona
        * Escribe un programa que lea el objeto Persona almacenado en persona.bin y muestre sus atributos en la consola.
        * */
        Persona persona = null;
        ObjectInputStream in = null;
        try(FileInputStream fileIn = new FileInputStream("documentos/ejerciciostemaunoparteuno/persona.bin")){
            in = new ObjectInputStream(fileIn);
            persona = (Persona) in.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(in != null)in.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        System.out.println(persona);
    }
}
