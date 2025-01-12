package com.spachecor.ejerciciofinalsgecn.model.row;

import com.spachecor.ejerciciofinalsgecn.model.entity.Estudiante;
import com.spachecor.ejerciciofinalsgecn.model.entity.Nota;

/**
 * Clase EstudianteNotasPorCursoRow, que determina como se repesentan los estudiantes y sus notas en una tabla
 * @author Selene
 * @version 1.0
 */
public class EstudianteNotasPorCursoRow {
    private Integer idEstudiante;
    private String nombreEstudiante;
    private Double notaEstudiante;
    public EstudianteNotasPorCursoRow(Estudiante estudiante, Nota nota) {
        this.idEstudiante = estudiante.getId();
        this.nombreEstudiante = estudiante.getNombre();
        this.notaEstudiante = nota.getValor();
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public Double getNotaEstudiante() {
        return notaEstudiante;
    }

    public void setNotaEstudiante(Double notaEstudiante) {
        this.notaEstudiante = notaEstudiante;
    }
}
