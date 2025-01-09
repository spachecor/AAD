package com.spachecor.ejerciciofinalsgecn.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Clase Nota, que es la nota que un estudiante obtiene en un curso
 * @see Entidad
 * @see Curso
 * @see Estudiante
 * @author Selene
 * @version 1.0
 */
@Entity
public class Nota extends Entidad<Nota>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double valor;
    private LocalDate fecha;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_curso")
    private Curso curso;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;

    public Nota() {}

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public int compareTo(Nota o) {
        return this.getId().compareTo(o.getId());
    }

    @Override
    public String toString() {
        return "Nota{" +
                "id=" + id +
                ", valor=" + valor +
                ", fecha=" + fecha +
                ", curso=" + curso +
                ", estudiante=" + estudiante +
                '}';
    }
}
