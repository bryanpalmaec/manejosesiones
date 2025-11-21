package com.bryan.aplicacionweb.manejosesiones.models;

import java.time.LocalDate;

/*
 * Autor:Bryan Palma
 * Fecha: 07/11/2025
 * Descripción: Modelo del MVC, se declara la clase Producto, con sus atributos privados
 * y se crea los métodos de setter y getter para todos aquellos atributos.
 * */
public class Producto {
    private Long idProducto;
    private String nombreProducto;
    private Categoria categoria;
    private int stock;
    private String descripcion;
    private String codigo;
    private Double precio;
    private LocalDate fechaElaboracion;
    private LocalDate fechaCaducidad;
    private int condicion;

    /*
     **
     */
    public Producto() {
        this.categoria = new Categoria();
    }

    public Producto(Long idProducto, String nombreProducto, Categoria categoria, int stock, String descripcion, String codigo, Double precio, String tipo, LocalDate fechaElaboracion, LocalDate fechaCaducidad, int condicion) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        //Categoria categoria = new Categoria();
        categoria.setNombreCategoria(tipo);
        this.categoria = categoria;
        this.stock = stock;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.precio = precio;
        this.fechaElaboracion = fechaElaboracion;
        this.fechaCaducidad = fechaCaducidad;
        this.condicion = condicion;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public LocalDate getFechaElaboracion() {
        return fechaElaboracion;
    }

    public void setFechaElaboracion(LocalDate fechaElaboracion) {
        this.fechaElaboracion = fechaElaboracion;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public int getCondicion() {
        return condicion;
    }

    public void setCondicion(int condicion) {
        this.condicion = condicion;
    }
}

