package com.spachecor.gestorbiblioteca.model.entity;

/**
 * Clase Usuario que define las propiedades y el comportamiento del objeto Usuario
 * @author Selene
 * @version 1.0
 */
public class Usuario extends Entidad<Usuario>{
    private Integer id;
    private String dni;
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;

    public Usuario() {}

    public Usuario(Integer id, String dni, String nombre, String email, String telefono, String direccion) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public int compareTo(Usuario t) {
        return this.dni.compareTo(t.getDni());
    }
}
