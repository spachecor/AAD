package com.spacrod.ejerciciostemaunopartetres;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class EjercicioTres {
    public static void main(String[] args) {
        /*
        * Ejercicio 3: Copia Condicional. Modifica el programa del Ejercicio 1 para que solo copie el fichero si
        * texto.txt existe. De lo contrario, debe mostrar un mensaje en la consola.
        * */
        String pathTexto = "documentos/ejerciciostemaunopartetres/texto.txt";
        String pathCopiaTexto = "documentos/ejerciciostemaunopartetres/copia_texto.txt";
        try{
            if(Files.exists(Path.of(pathTexto))){
                Files.copy(Path.of(pathTexto), Path.of(pathCopiaTexto), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Copia realizada con exito");
            }else System.out.println("El fichero no existe y por lo tanto no puede ser copiado");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
