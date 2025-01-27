package com.spachecor;

import java.math.BigDecimal;

public class Empleado {
    private String nombre;
    private String departamento;
    private BigDecimal salario;

    public Empleado() {}

    public Empleado(String nombre, String departamento, String salario) {
        this.nombre = nombre;
        this.departamento = departamento;
        this.salario = new BigDecimal(salario);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }
}
