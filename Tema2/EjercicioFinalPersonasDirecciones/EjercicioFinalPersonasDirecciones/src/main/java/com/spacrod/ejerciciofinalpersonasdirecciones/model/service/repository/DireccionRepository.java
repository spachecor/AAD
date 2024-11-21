package com.spacrod.ejerciciofinalpersonasdirecciones.model.service.repository;

import com.spacrod.ejerciciofinalpersonasdirecciones.model.entity.Direccion;

import java.sql.SQLException;
import java.util.List;

public class DireccionRepository implements CrudRepository<Direccion> {
    @Override
    public Direccion findById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Direccion> findAll() throws SQLException {
        return List.of();
    }

    @Override
    public void save(Direccion direccion) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public void update(Direccion direccion) throws SQLException {

    }
}
