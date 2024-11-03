package com.spachecor.proyectoproductos.model.conversores;

/**
 * Enum que define los tipos de extensión de documentos
 * @author Selene
 * @version 1.0
 */
public enum ExtensionDocumento {
    XML("xml"),
    CSV("csv"),
    JSON("json");
    private final String EXTENSION_DOCUMENTO;
    ExtensionDocumento(String extension) {
        EXTENSION_DOCUMENTO = extension;
    }
    public String getExtension() {
        return EXTENSION_DOCUMENTO;
    }

    /**
     * Método que devuelve un objeto del tipo ExtensionDocumento según la cadena que le entre comparándolo con las
     * opciones dentro de este Enum. Puede lanzar la excepción IllegalArgumentException si no encuentra la extensión
     * dentro de las opciones del Enum
     * @param extension La cadena que simboliza la extensión de documento
     * @return Un objeto del tipo ExtensiónDocumento que coincide con la extensión introducida
     */
    public static ExtensionDocumento fromString(String extension) {
        for (ExtensionDocumento ext : ExtensionDocumento.values()) {
            if (ext.getExtension().equalsIgnoreCase(extension)) {
                return ext;
            }
        }
        throw new IllegalArgumentException("No se encontró ninguna extensión para: " + extension);
    }
}
