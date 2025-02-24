package com.spachecor.gestorbiblioteca.model.entity;

public class Libro extends Entidad<Libro> {
    private Integer id;
    private String titulo;
    private String autor;
    private Integer anioPublicacion;
    private String categoria;
    private String subCategoria;
    private Long isbn;
    private String editorial;
    private Integer numeroPaginas;
    private Integer numeroCopiasDisponibles;

    public Libro() {}

    public Libro(Integer id, String titulo, String autor, Integer anioPublicacion, String categoria, String subCategoria, Long isbn, String editorial, Integer numeroPaginas, Integer numeroCopiasDisponibles) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.categoria = categoria;
        this.subCategoria = subCategoria;
        this.isbn = isbn;
        this.editorial = editorial;
        this.numeroPaginas = numeroPaginas;
        this.numeroCopiasDisponibles = numeroCopiasDisponibles;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(Integer anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(String subCategoria) {
        this.subCategoria = subCategoria;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Integer getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(Integer numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public Integer getNumeroCopiasDisponibles() {
        return numeroCopiasDisponibles;
    }

    public void setNumeroCopiasDisponibles(Integer numeroCopiasDisponibles) {
        this.numeroCopiasDisponibles = numeroCopiasDisponibles;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", anioPublicacion=" + anioPublicacion +
                ", categoria='" + categoria + '\'' +
                ", subCategoria='" + subCategoria + '\'' +
                ", isbn=" + isbn +
                ", editorial='" + editorial + '\'' +
                ", numeroPaginas=" + numeroPaginas +
                ", numeroCopiasDisponibles=" + numeroCopiasDisponibles +
                '}';
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public int compareTo(Libro t) {
        if(
                this.autor.compareTo(t.getAutor())==0
                        &&this.titulo.compareTo(t.getTitulo())==0
                        &&this.anioPublicacion.compareTo(t.getAnioPublicacion())==0
        )return 0;
        else return -1;
    }
}
