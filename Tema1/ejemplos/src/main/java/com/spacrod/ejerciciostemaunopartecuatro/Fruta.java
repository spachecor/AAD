package com.spacrod.ejerciciostemaunopartecuatro;

public class Fruta {
    private String nombre;
    private String color;
    private Integer cantidad;
    public Fruta(String nombre, String color, Integer cantidad) {
        this.nombre = nombre;
        this.color = color;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
