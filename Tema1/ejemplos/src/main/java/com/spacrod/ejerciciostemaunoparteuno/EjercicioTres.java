package com.spacrod.ejerciciostemaunoparteuno;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class EjercicioTres {
    public static void main(String[] args){
        /*
        * Ejercicio 3: Clase Persona
        * Crea una clase Persona que contenga atributos como nombre, edad y dirección. Serializa un objeto de la clase
        * Persona en un fichero binario llamado persona.bin.
        * */
        //creamos la persona
        Persona persona = new Persona("Selene", 25, new Direccion("Calle Amiantos, 3", "Sevilla", "España", 41015));
        String path = "documentos/ejerciciostemaunoparteuno/persona.bin";
        File file = new File(path);
        //creamos el fichero si no existe
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try{
            //si no existe el fichero, lo creamos
            if(!file.exists())file.createNewFile();
            //una vez comprobamos que hay fichero, escribimos
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(persona);
            System.out.println("Persona escrito correctamente");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                fos.close();
                oos.close();
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("No se puede cerrar");
            }
        }
    }
}
