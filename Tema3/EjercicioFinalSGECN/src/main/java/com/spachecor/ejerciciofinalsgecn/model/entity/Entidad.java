package com.spachecor.ejerciciofinalsgecn.model.entity;

/**
 * Clase Entidad que es el molde de todas las entidades del programa
 * @param <T> Representa el tipo de entidad que es
 * @author Selene
 * @version 1.0
 */
public abstract class Entidad<T extends Entidad> implements Comparable<T>{
    /**
     * Método abstracto que devuelve el id de la entidad. Toda entidad tiene un id
     * @return El id de la entidad
     */
    public abstract Integer getId();
}
