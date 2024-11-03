package com.spachecor.proyectoproductos.model.entity;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Clase que es la estructura del objeto Producto y define esta entidad
 * @author Selene
 * @version 1.0
 */
@XmlRootElement(name = "producto")
public class Producto implements Comparable<Producto>{
    private String nombre;
    private Double precio;
    private Integer cantidad;
    public Producto(){}

    @Override
    public int compareTo(Producto o) {
        return this.nombre.compareTo(o.nombre);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Producto))return false;
        return this.nombre.equals(((Producto)obj).nombre);
    }

    @Override
    public String toString() {
        return "Producto{" + "nombre=" + nombre + ", precio=" + precio + ", cantidad=" + cantidad + '}';
    }

    @XmlElement
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement
    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @XmlElement
    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
