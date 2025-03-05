package com.spachecor.ejerciciofinalsgb.controller.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class XMLDocumentService {
    /**
     * Funcion que crea un documento XML con el contenido que le entra por parámetro y que devuelve la URL de éste
     * @param contenido El contenido del xml(en su formato)
     * @return La url del documento o null en caso de fallo
     */
    public static String crearDocumento(String contenido){
        String ruta = Paths.get("src/main/resources/com/spachecor/ejerciciofinalsgb/elemento.xml").toAbsolutePath().toString();
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))){
            writer.write(contenido);
            return ruta;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Funcion que elimina el documento temporal usado para crear una nueva entidad
     * @param ruta La ruta del documento
     * @return true si se ha eliminado correctamente y false por el contrario
     */
    public static boolean eliminarDocumento(String ruta){
        return new File(ruta).delete();
    }
}
