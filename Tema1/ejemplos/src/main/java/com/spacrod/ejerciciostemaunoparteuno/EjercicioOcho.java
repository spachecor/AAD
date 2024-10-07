package com.spacrod.ejerciciostemaunoparteuno;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class EjercicioOcho {
    public static void main(String[] args) {
        /*
        * Ejercicio 8: Leer Datos Numéricos. Escribe un programa que lea los números almacenados en numeros.bin y los
        * imprima en la consola, calculando también la suma total de los números.
        * */
        List<Long> numeros = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("documentos/ejerciciostemaunoparteuno/numeros-aleatorios.bin"))){
            numeros = (List<Long>) ois.readObject();
            System.out.println(numeros);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
