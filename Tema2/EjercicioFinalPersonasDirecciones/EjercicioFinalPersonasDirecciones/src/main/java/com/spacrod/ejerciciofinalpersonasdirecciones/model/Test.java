package com.spacrod.ejerciciofinalpersonasdirecciones.model;

import com.spacrod.ejerciciofinalpersonasdirecciones.model.service.properties.PropertiesOptions;
import com.spacrod.ejerciciofinalpersonasdirecciones.model.service.properties.PropertiesService;

public class Test {
    public static void main(String[] args){
        System.out.println(PropertiesService.getProperty(PropertiesOptions.NOMBRE_BBDD.getPropertyOption()));
    }
}
