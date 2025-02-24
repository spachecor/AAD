package com.spachecor.gestorbiblioteca.model.entity;

/**
 * Clase Entidad, que es una clase abstracta genérica que define como deben ser las entidades
 * @param <T> El tipo de entidad
 * @author Selene
 * @version 1.0
 */
public abstract class Entidad<T extends Entidad> implements Comparable<T>{
    /**
     * Funcion para obtener el id de la entidad
     * @return El id de la entidad
     */
    protected abstract Integer getId();
}
