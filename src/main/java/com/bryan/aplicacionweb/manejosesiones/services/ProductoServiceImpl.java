package com.bryan.aplicacionweb.manejosesiones.services;

/*
 * Autor:Bryan Palma
 * Fecha: 07/11/2025
 * Descripción: Implementación de la clase que usa la interface ProductoService y retorno en
 * formato de array los datos para crear los productos.
 * */

import com.bryan.aplicacionweb.manejosesiones.models.Categoria;
import com.bryan.aplicacionweb.manejosesiones.models.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImpl implements ProductoService {
    @Override
    public List<Producto> listar() {
        return List.of();
    }

    @Override
    public Optional<Producto> porId(Long id) {
        return listar().stream().filter(p -> p.getIdProducto().equals(id)).findAny();
    }
}

