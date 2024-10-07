package com.spacrod.ejerciciostemaunoparteuno;

import java.io.*;

public class EjercicioNueve {
    public static void main(String[] args) {
        /*
        * Modificar un Objeto. Crea un programa que lea un objeto Persona desde persona.bin, modifique su edad y vuelva
        * a escribir el objeto actualizado en el mismo fichero.
        * */
        String path = "documentos/ejerciciostemaunoparteuno/persona.bin";
        Persona persona = null;
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))){
            persona = (Persona) in.readObject();
        }catch (Exception e) {
            e.printStackTrace();
        }
        persona.setEdad(persona.getEdad() + 1);
        try(FileOutputStream fileOut = new FileOutputStream(path)){
            ObjectOutputStream oos = new ObjectOutputStream(fileOut);
            oos.writeObject(persona);
            System.out.println("Persona modificada");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
