package com.spachecor.ejerciciofinalsgb.model.dao;

import com.spachecor.ejerciciofinalsgb.model.entity.Entidad;

import java.util.List;
import java.util.Optional;

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
     * @return Un objeto Optionar que puede contener la Entidad que coincide con el id
     */
    Optional<T> buscarPorId(Integer id);

    /**
     * Funcion que crea una Entidad en la base de datos a partir de la Entidad pasada por parametro
     * @param url La url interna del programa donde se ubica el fichero que contiene la Entidad
     * @param subColeccion El nombre de la coleccion donde lo almacenaremos
     */
    void crear(String url, String subColeccion);

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
