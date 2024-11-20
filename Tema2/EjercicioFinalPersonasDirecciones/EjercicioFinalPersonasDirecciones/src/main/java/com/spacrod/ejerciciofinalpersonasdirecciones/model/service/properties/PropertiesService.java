package com.spacrod.ejerciciofinalpersonasdirecciones.model.service.properties;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesService {
    private static final Properties PROPERTIES;
    static{
        PROPERTIES = new Properties();
        try{
            vincularProperties();
        }catch(IOException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
    private static void vincularProperties() throws IOException {
        try{
            PROPERTIES.load(new FileReader("src/main/resources/com/spacrod/ejerciciofinalpersonasdirecciones/properties/config.properties"));
        }catch(IOException e){
            throw new IOException("No se pudieron cargar las propiedades del programa: "+e.getMessage());
        }
    }
    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}
