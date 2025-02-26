package com.spachecor.gestorbiblioteca.model.dao;

import com.spachecor.gestorbiblioteca.model.entity.Usuario;
import com.spachecor.gestorbiblioteca.model.mapper.Mapper;
import com.spachecor.gestorbiblioteca.model.mapper.UsuarioMapper;

/**
 * Clase UsuarioDAOGenericImpl que se encarga de definir el Mapper, el CollectionPath y el EntityTag relacionados con el
 * objeto Entidad tipo Usuario para acceder a los usuarios.
 * @author Selene
 * @version 1.0
 */
public class UsuarioDAOGenericImpl extends EntidadGenericDAOImpl<Usuario> {

    private final Mapper<Usuario> MAPPER_USUARIO;

    public UsuarioDAOGenericImpl() {
        this.MAPPER_USUARIO = new UsuarioMapper();
    }

    @Override
    protected Mapper<Usuario> getMapper() {
        return this.MAPPER_USUARIO;
    }

    @Override
    protected String getCollectionPath() {
        return "/library/users";
    }

    @Override
    protected String getEntityTag() {
        return "user";
    }
}
