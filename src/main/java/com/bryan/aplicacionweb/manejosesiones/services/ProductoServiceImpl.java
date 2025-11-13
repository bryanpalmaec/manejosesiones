package com.bryan.aplicacionweb.manejosesiones.services;

/*
 * Autor:Bryan Palma
 * Fecha: 07/11/2025
 * Descripción: Implementación de la clase que usa la interface ProductoService y retorno en
 * formato de array los datos para crear los productos.
 * */

import com.bryan.aplicacionweb.manejosesiones.models.Producto;

import java.util.Arrays;
import java.util.List;

public class ProductoServiceImpl implements ProductoService {
    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L,"Laptop","Computación",250.25),
                new Producto(2L,"Refrigeradora","Cocina",745.13),
                new Producto(3L,"Cama","Dormitorio",350.12));
    }
}

