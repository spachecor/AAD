package com.spacrod.ejerciciostemaunoparteuno;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class EjercicioSiete {
    public static void main(String[] args) {
        /*
        * Ejercicio 7: Guardar Datos Numéricos. Crea un programa que genere un array de números enteros (por ejemplo,
        * 10 números aleatorios) y lo escriba en un fichero binario llamado numeros.bin.
        * */
        List<Long> numeros = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            Long numero = Math.round(Math.random()*100);
            numeros.add(numero);
        }
        File file = new File("documentos/ejerciciostemaunoparteuno/numeros-aleatorios.bin");
        ObjectOutputStream oos = null;
        try{
            //creamos el fichero si no existe
            if(!file.exists())file.createNewFile();
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(numeros);
            System.out.println("Numeros guardados.");
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
