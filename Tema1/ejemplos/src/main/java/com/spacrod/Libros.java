package com.spacrod;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "libros")
public class Libros {
    private List<Libro> listaLibros;

    public Libros() {
        this.listaLibros = new ArrayList<>();
    }

    @XmlElement(name = "libro")
    public List<Libro> getListaLibros() {
        return listaLibros;
    }

    public void setListaLibros(List<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }
}