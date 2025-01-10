package com.spachecor.ejerciciofinalsgecn.model.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Clase Estudiante, que define al estudiante que puede matricularse en una serie de cursos.
 * @see Entidad
 * @see Curso
 * @author Selene
 * @version 1.0
 */
@Entity
public class Estudiante extends Entidad<Estudiante>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "estudiante_curso",
            joinColumns = @JoinColumn(name = "id_estudiante"),
            inverseJoinColumns = @JoinColumn(name = "id_curso")
    )
    private Set<Curso> cursos;
    @OneToMany(mappedBy = "estudiante", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Nota> notas;

    public Estudiante() {
        this.cursos = new HashSet<>();
        this.notas = new HashSet<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Set<Curso> cursos) {
        this.cursos = cursos;
    }

    public Set<Nota> getNotas() {
        return notas;
    }

    public void setNotas(Set<Nota> notas) {
        this.notas = notas;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public int compareTo(Estudiante o) {
        return this.getId().compareTo(o.getId());
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", nº cursos=" + cursos.size() +
                '}';
    }
}
