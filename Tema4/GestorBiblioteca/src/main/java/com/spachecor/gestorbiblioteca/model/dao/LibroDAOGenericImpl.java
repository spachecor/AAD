package com.spachecor.gestorbiblioteca.model.dao;

import com.spachecor.gestorbiblioteca.model.entity.Libro;
import com.spachecor.gestorbiblioteca.model.mapper.LibroMapper;
import com.spachecor.gestorbiblioteca.model.mapper.Mapper;

public class LibroDAOGenericImpl extends EntidadGenericDAOImpl<Libro> {

    private final Mapper<Libro> MAPPER_LIBRO = new LibroMapper();

    @Override
    protected Mapper<Libro> getMapper() {
        return MAPPER_LIBRO;
    }

    @Override
    protected String getCollectionPath() {
        return "/library/books";
    }

    @Override
    protected String getEntityTag() {
        return "book";
    }
}
