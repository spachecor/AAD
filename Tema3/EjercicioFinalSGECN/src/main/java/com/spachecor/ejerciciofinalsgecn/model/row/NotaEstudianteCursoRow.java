package com.spachecor.ejerciciofinalsgecn.model.row;

import com.spachecor.ejerciciofinalsgecn.model.entity.Curso;
import com.spachecor.ejerciciofinalsgecn.model.entity.Estudiante;
import com.spachecor.ejerciciofinalsgecn.model.entity.Nota;

public class NotaEstudianteCursoRow {
    private Integer idEstudiante;
    private String nombreEstudiante;
    private Integer idCurso;
    private String nombreCurso;
    private Double nota;
    public NotaEstudianteCursoRow(Estudiante estudiante, Curso curso, Nota nota) {
        this.idEstudiante = estudiante.getId();
        this.nombreEstudiante = estudiante.getNombre()+" "+estudiante.getApellido();
        this.idCurso = curso.getId();
        this.nombreCurso = curso.getNombre();
        this.nota = nota.getValor();
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

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
}
