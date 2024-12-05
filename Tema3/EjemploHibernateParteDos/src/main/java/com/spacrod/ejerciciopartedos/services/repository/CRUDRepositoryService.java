package com.spacrod.ejerciciopartedos.services.repository;

import java.util.List;
import java.util.Optional;

public interface CRUDRepositoryService<T> {
    Optional<T> findById(Integer id);
    List<T> findAll();
    Boolean save(T t);
    Boolean delete(T t);
    void destruct();
}
