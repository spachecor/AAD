package com.spacrod.ejerciciostemaunopartedos;

public class Libro implements Comparable<Libro>{
    private String titulo;
    private String autor;

    public Libro() {}

    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Título={" + titulo + '}' +
                ", Autor={" + autor + '}';
    }

    public static Libro toObject(String libroTexto){
        String tituloTexto = libroTexto.substring(libroTexto.indexOf("{")+1, libroTexto.indexOf("}"));
        String autorTexto = libroTexto.substring(libroTexto.lastIndexOf("{")+1, libroTexto.lastIndexOf("}"));
        return new Libro(tituloTexto, autorTexto);
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

    @Override
    public int compareTo(Libro o) {
        if(this.titulo.compareTo(o.titulo)==0&&this.autor.compareTo(o.autor)==0)return 0;
        else return -1;
    }
}
