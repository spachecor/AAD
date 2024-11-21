package com.spacrod.ejerciciofinalpersonasdirecciones.model.service.repository;

import java.sql.SQLException;
import java.util.List;

public interface CrudRepository<T> {
    T findById(int id) throws SQLException;
    List<T> findAll() throws SQLException;
    void save(T t) throws SQLException;
    void delete(int id) throws SQLException;
    void update(T t) throws SQLException;
}
