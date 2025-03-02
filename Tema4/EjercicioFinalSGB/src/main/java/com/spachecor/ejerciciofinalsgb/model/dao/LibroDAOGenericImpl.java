package com.spachecor.ejerciciofinalsgb.model.dao;

import com.spachecor.ejerciciofinalsgb.model.entity.Libro;
import com.spachecor.ejerciciofinalsgb.model.mapper.LibroMapper;
import com.spachecor.ejerciciofinalsgb.model.mapper.Mapper;

/**
 * Clase LibroDAOGenericImpl que se encarga de definir el Mapper, el CollectionPath y el EntityTag relacionados con el
 * objeto Entidad tipo Libro para acceder a los libros.
 * @author Selene
 * @version 1.0
 */
public class LibroDAOGenericImpl extends EntidadGenericDAOImpl<Libro> {

    private final Mapper<Libro> MAPPER_LIBRO;

    public LibroDAOGenericImpl() {
        this.MAPPER_LIBRO = new LibroMapper();
    }

    @Override
    protected Mapper<Libro> getMapper() {
        return this.MAPPER_LIBRO;
    }

    @Override
    protected String getCollectionPath() {
        return "librarywithcollections/books";
    }

    @Override
    protected String getEntityTag() {
        return "book";
    }
}
