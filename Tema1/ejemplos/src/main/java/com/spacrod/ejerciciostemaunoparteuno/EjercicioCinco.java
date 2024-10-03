package com.spacrod.ejerciciostemaunoparteuno;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class EjercicioCinco {
    public static void main(String[] args) {
        /*
        * Ejercicio 5: Lista de Personas
        * Modifica la clase Persona para que permita almacenar una lista de varias personas. Escribe un programa que
        * serialice una lista de objetos Persona en un fichero binario llamado personas.bin.
        * */
        //creamos a las personas
        Persona personaUno = new Persona("Selene", 25, new Direccion("Calle Amiantos, 3", "Sevilla", "España", 41015));
        Persona personaDos = new Persona("Juan", 34, new Direccion("Calle Amiantos, 4", "Sevilla", "España", 41015));
        List<Persona> personas = new ArrayList<>();
        personas.add(personaUno);
        personas.add(personaDos);
        //el fichero
        String path = "documentos/ejerciciostemaunoparteuno/persona2.bin";
        File file = new File(path);
        ObjectOutputStream oos = null;
        try{
            //si no existe el fichero, lo creamos
            if(!file.exists())file.createNewFile();
            oos = new ObjectOutputStream(new FileOutputStream(file));
            for(Persona persona : personas){
                oos.writeObject(persona);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                oos.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
