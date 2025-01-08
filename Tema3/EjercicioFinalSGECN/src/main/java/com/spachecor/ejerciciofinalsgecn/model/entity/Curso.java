package com.spachecor.ejerciciofinalsgecn.model.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Clase Curso, que es un curso donde pueden matricularse estudiantes y que contiene las notas de los mismos
 * @see Entidad
 * @see Estudiante
 * @see Nota
 * @author Selene
 * @version 1.0
 */
@Entity
public class Curso extends Entidad<Curso>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String descripcion;
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Nota> notas;
    @ManyToMany(mappedBy = "cursos")
    private Set<Estudiante> estudiantes;

    public Curso() {
        this.notas = new HashSet<>();
        this.estudiantes = new HashSet<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Nota> getNotas() {
        return notas;
    }

    public void setNotas(Set<Nota> notas) {
        this.notas = notas;
    }

    public Set<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Set<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public int compareTo(Curso o) {
        return this.getId().compareTo(o.getId());
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", nº notas=" + notas.size() +
                ", nº estudiantes=" + estudiantes.size() +
                '}';
    }
}
