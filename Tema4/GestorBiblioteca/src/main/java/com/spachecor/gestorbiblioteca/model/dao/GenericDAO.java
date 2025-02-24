package com.spachecor.gestorbiblioteca.model.dao;

import com.spachecor.gestorbiblioteca.model.entity.Entidad;

import java.util.List;

/**
 * Interfaz generica GenericDAO que se encarga de definir las funciones para el acceso a la base de datos
 * para todas las entidades
 * @param <T> T será una clase que herede de Entidad
 * @author Selene
 * @version 1.0
 */
public interface GenericDAO<T extends Entidad> {
    /**
     * Funcion que devuelve una lista de Entidades del tipo T
     * @return Lista de Entidades del tipo T
     */
    List<T> listar();

    /**
     * Funcion que busca una Entidad concreta del tipo T a partir de su id
     * @param id El id de la Entidad
     * @return La Entidad que coincide con el id
     */
    T buscarPorId(Integer id);

    /**
     * Funcion que crea una Entidad en la base de datos a partir de la Entidad pasada por parametro
     * @param t La Entidad de tipo T a guardar
     */
    void crear(T t);

    /**
     * Funcion que actualiza una Entidad en la base de datos
     * @param t La Entidad del tipo T a actualizar
     */
    void actualizar(T t);

    /**
     * Funcion que elimina una Entidad de la base de datos
     * @param t La Entidad a eliminar del tipo T
     */
    void eliminar(T t);
}
