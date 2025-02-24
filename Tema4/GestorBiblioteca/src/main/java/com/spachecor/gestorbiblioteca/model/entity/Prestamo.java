package com.spachecor.gestorbiblioteca.model.entity;

import java.time.LocalDate;

public class Prestamo extends Entidad<Prestamo>{
    private Integer id;
    private Usuario usuario;
    private Libro libro;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    public Prestamo() {}

    public Prestamo(Integer id, Usuario usuario, Libro libro, LocalDate fechaPrestamo, LocalDate fechaDevolucion) {
        this.id = id;
        this.usuario = usuario;
        this.libro = libro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "id=" + id +
                ", usuario=" + usuario.getId() +
                ", libro=" + libro.getId() +
                ", fechaPrestamo=" + fechaPrestamo +
                ", fechaDevolucion=" + fechaDevolucion +
                '}';
    }

    public Integer getId() {
        return id;
    }

    @Override
    public int compareTo(Prestamo t) {
        if(
                this.usuario.equals(t.getUsuario())
                        && this.libro.equals(t.getLibro())
                        && this.fechaPrestamo.equals(t.getFechaPrestamo())
                        && this.fechaDevolucion.equals(t.getFechaDevolucion())
        )return 0;
        else return -1;
    }
}
