package com.bryan.aplicacionweb.manejosesiones.models;

/*
 * Autor:Bryan Palma
 * Fecha: 07/11/2025
 * Descripción: Modelo del MVC, se declara la clase Producto, con sus atributos privados
 * y se crea los métodos de setter y getter para todos aquellos atributos.
 * */
public class Producto {
    private Long idProducto;
    private String nombre;
    private String categoria;
    private Double precio;
    /*
     **
     */
    public Producto() {

    }
    /*
     **
     */
    public Producto(Long idProducto, String nombre, String categoria, Double precio) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }
    //Get and setter
    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }
    public Long getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}

