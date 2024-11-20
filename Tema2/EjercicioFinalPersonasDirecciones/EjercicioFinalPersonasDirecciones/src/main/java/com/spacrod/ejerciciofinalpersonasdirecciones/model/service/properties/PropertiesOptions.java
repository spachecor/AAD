package com.spacrod.ejerciciofinalpersonasdirecciones.model.service.properties;

public enum PropertiesOptions {
    //base de datos
    NOMBRE_BBDD("nombre.bbdd"),
    URL("url"),
    USUARIO("usuario"),
    CONTRASENA("contrasena"),
    DRIVER("driver");
    private final String PROPERTY_OPTION;
    PropertiesOptions(String propertyOption) {
        this.PROPERTY_OPTION = propertyOption;
    }
    public String getPropertyOption() {
        return PROPERTY_OPTION;
    }
}
