package com.spacrod.ejerciciofinalpersonasdirecciones.model;

import com.spacrod.ejerciciofinalpersonasdirecciones.model.entity.*;
import com.spacrod.ejerciciofinalpersonasdirecciones.model.service.repository.CrudRepository;
import com.spacrod.ejerciciofinalpersonasdirecciones.model.service.repository.PersonaRepository;

import java.sql.*;
import java.util.List;

public class Test {
    public static void main(String[] args) throws SQLException {
        CrudRepository<Persona> personaRepository = new PersonaRepository();
        List<Persona> personas = personaRepository.findAll();
        System.out.println(personas);
    }
}
