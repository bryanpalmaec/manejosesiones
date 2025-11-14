package com.bryan.aplicacionweb.manejosesiones.services;

/*
 * Autor:Bryan Palma
 * Fecha: 07/11/2025
 * Descripción: Se declara la interfaz que sirve como plantilla para la implementacion del
 * servicio de producto
 * */


import com.bryan.aplicacionweb.manejosesiones.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();
    Optional<Producto> porId(Long id);
}
