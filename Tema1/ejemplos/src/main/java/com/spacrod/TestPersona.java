package com.spacrod;

import java.io.*;

public class TestPersona {
    public static void main(String[] args) {
        Persona persona = new Persona("Teo", 30);
        try (FileOutputStream fileOut = new FileOutputStream("documentos/persona.bin");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(persona);
        }catch (IOException e){
            e.printStackTrace();
        }
        Persona persona2 = null;
        try(FileInputStream fileIn = new FileInputStream("documentos/persona.bin")){
            ObjectInputStream in = new ObjectInputStream(fileIn);
            persona2 = (Persona) in.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(persona2);
    }
}
