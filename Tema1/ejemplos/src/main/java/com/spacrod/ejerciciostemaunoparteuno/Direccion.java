package com.spacrod.ejerciciostemaunoparteuno;

import java.io.Serializable;

public class Direccion implements Serializable {
    private static final long serialVersionUID = 1L;
    private String direccion;
    private String ciudad;
    private String pais;
    private Integer cp;

    public Direccion(String direccion, String ciudad, String pais, Integer cp) {
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.pais = pais;
        this.cp = cp;
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "direccion='" + direccion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", pais='" + pais + '\'' +
                ", cp=" + cp +
                '}';
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Integer getCp() {
        return cp;
    }

    public void setCp(Integer cp) {
        this.cp = cp;
    }
}
