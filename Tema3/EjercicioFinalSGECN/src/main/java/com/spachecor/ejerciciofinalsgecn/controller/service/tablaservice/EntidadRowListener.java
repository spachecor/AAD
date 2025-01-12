package com.spachecor.ejerciciofinalsgecn.controller.service.tablaservice;

import com.spachecor.ejerciciofinalsgecn.model.entity.Entidad;

/**
 * Interfaz EntidadRowListener, que es un Listener para las entidades-fila(representación de una entidad o información
 * de la misma pero preparada para ser una fila de una tabla)
 * @param <T> Defina que la entidad será descendiente de Entidad
 */
public interface EntidadRowListener<T extends Entidad> {
    /**
     * Método que define cómo se elimina una entidad de una tabla
     * @param t La entidad a eliminar
     */
    void eliminarEntidad(T t);
}
