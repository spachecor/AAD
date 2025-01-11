package com.spachecor.ejerciciofinalsgecn.controller.service.tablaservice;

import com.spachecor.ejerciciofinalsgecn.model.entity.Entidad;

public interface EntidadRowListener<T extends Entidad> {
    void eliminarEntidad(T t);
}
