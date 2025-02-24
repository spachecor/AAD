package com.spachecor.gestorbiblioteca.model.mapper;

import com.spachecor.gestorbiblioteca.model.entity.Entidad;

/**
 * Interfaz Mapper que define las funciones necesarias para el mapeo de una Entidad de tipo T
 * @param <T> El tipo de Entidad
 * @author Selene
 * @version 1.0
 */
public interface Mapper<T extends Entidad> {
    /**
     * Funcion que convierte una cadena que representa un objeto en formato XML a una Entidad del tipo T
     * @param xml La cadena que representa el objeto Entidad en XML
     * @return El objeto Entidad del tipo T
     */
    T deXML(String xml);

    /**
     * Funcion que convierte un objeto Entidad del tipo T en una cadena que representa el objeto en formato XML
     * @param t El objeto Entidad de tipo T
     * @return La cadena que representa el objeto en XML
     */
    String aXML(T t);
}
