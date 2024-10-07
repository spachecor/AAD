package com.spacrod.ejerciciostemaunoparteuno;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EjecicioSeis {
    public static void main(String[] args) {
        /*
        * Ejercicio 6: Leer una Lista de Personas
        * Escribe un programa que lea el fichero personas.bin y muestre todos los nombres y edades de las personas en
        * la lista.
        * */
        List<Persona> personas = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("documentos/ejerciciostemaunoparteuno/persona2.bin"))){
            //leemos el primer objeto
            Object aux = ois.readObject();
            //mientras haya objetos, iteramos
            while (aux!=null){
                if (aux instanceof Persona)
                    personas.add((Persona) aux);
                aux = ois.readObject();
            }
        }catch (EOFException e){
            System.out.println("No hay mas personas");
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(personas);
    }
}
