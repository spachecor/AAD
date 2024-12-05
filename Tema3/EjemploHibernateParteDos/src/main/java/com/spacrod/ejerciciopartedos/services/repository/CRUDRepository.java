package com.spacrod.ejerciciopartedos.services.repository;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<T> {
    T findById(Integer id);
    List<T> findAll();
    void save(T t);
    void delete(T t);
}
