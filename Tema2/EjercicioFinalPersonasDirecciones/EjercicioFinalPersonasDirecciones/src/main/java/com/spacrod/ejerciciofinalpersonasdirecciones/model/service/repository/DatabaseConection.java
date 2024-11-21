package com.spacrod.ejerciciofinalpersonasdirecciones.model.service.repository;

import com.spacrod.ejerciciofinalpersonasdirecciones.model.service.properties.PropertiesOptions;
import com.spacrod.ejerciciofinalpersonasdirecciones.model.service.properties.PropertiesService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConection {
    private Connection connection;
    public DatabaseConection() {
        try{
            connection = DriverManager.getConnection(
                    PropertiesService.getProperty(PropertiesOptions.URL.getPropertyOption())+PropertiesService.getProperty(PropertiesOptions.NOMBRE_BBDD.getPropertyOption()),
                    PropertiesService.getProperty(PropertiesOptions.USUARIO.getPropertyOption()),
                    PropertiesService.getProperty(PropertiesOptions.CONTRASENA.getPropertyOption())
            );
        }catch(SQLException e){
            System.out.println("No se pudo conectar con la base de datos: "+e.getMessage());
            System.exit(1);
        }
    }
    public Connection getConnection() {
        return connection;
    }
}
