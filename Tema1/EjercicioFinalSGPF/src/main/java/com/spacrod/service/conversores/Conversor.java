package com.spacrod.service.conversores;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Conversor {
    public abstract File convert(File file) throws IOException;
    protected ExtensionDocumento obtenerExtensionDocumento(String nombreFichero) {
        int i = nombreFichero.lastIndexOf('.');
        if(i == -1) throw new IllegalArgumentException("El archivo no existe o no contiene extensión.");
        return ExtensionDocumento.fromString(nombreFichero.substring(i+1));
    }
}
