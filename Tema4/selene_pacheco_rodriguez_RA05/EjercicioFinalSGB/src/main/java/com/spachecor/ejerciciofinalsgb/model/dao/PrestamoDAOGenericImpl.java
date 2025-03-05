package com.spachecor.ejerciciofinalsgb.model.dao;

import com.spachecor.ejerciciofinalsgb.model.entity.Prestamo;
import com.spachecor.ejerciciofinalsgb.model.mapper.Mapper;
import com.spachecor.ejerciciofinalsgb.model.mapper.PrestamoMapper;

/**
 * Clase PrestamoDAOGenericImpl que se encarga de definir el Mapper, el CollectionPath y el EntityTag relacionados con el
 * objeto Entidad tipo Prestamo para acceder a los prestamos.
 * @author Selene
 * @version 1.0
 */
public class PrestamoDAOGenericImpl extends EntidadGenericDAOImpl<Prestamo> {

    private final Mapper<Prestamo> MAPPER_PRESTAMO;

    public PrestamoDAOGenericImpl() {
        this.MAPPER_PRESTAMO = new PrestamoMapper();
    }

    @Override
    protected Mapper<Prestamo> getMapper() {
        return this.MAPPER_PRESTAMO;
    }

    @Override
    protected String getCollectionPath() {
        return "librarywithcollections/loans";
    }

    @Override
    protected String getEntityTag() {
        return "loan";
    }
}
