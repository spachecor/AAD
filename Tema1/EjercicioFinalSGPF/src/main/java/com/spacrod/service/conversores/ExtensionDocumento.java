package com.spacrod.service.conversores;

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
    public static ExtensionDocumento fromString(String extension) {
        for (ExtensionDocumento ext : ExtensionDocumento.values()) {
            if (ext.getExtension().equalsIgnoreCase(extension)) {
                return ext;
            }
        }
        throw new IllegalArgumentException("No se encontró ninguna extensión para: " + extension);
    }
}
