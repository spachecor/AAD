package com.spachecor.proyectoproductos.model.conversores;

import java.io.File;
import java.io.IOException;

/**
 * Clase que define el comportamiento de los conversores que hereden de ella.
 * @author Selene
 * @version 1.0
 */
public abstract class Conversor {
    /**
     * Método que convierte el File que entre en otro File con la misma información pero distinto formato
     * @param file Un File con contenido a convertir
     * @return El File convertido
     * @throws IOException Excepción que puede lanzar si no es capaz de hacer la conversión
     */
    public abstract File convert(File file) throws IOException;

    /**
     * Método que obtiene la extensión de un File. Puede lanzar IllegalArgumentException si no existe el File o
     * si no contiene extensión
     * @param nombreFichero El nombre del File del que obtener la extensión
     * @return Objeto del tipo ExtensionDocumento que define la extension del documento que es
     */
    protected ExtensionDocumento obtenerExtensionDocumento(String nombreFichero) {
        int i = nombreFichero.lastIndexOf('.');
        if(i == -1) throw new IllegalArgumentException("El archivo no existe o no contiene extensión.");
        return ExtensionDocumento.fromString(nombreFichero.substring(i+1));
    }
}
