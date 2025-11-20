package com.bryan.aplicacionweb.manejosesiones.repositorio;

import java.sql.SQLException;
import java.util.List;

public interface Repositorio<T> {
    /*
    * Implemento un metodo para listar los productos
    * */
    List<T> listar() throws SQLException;
    //Implementamos un metodo para buscar un producto por ID
    T porId(Long id) throws SQLException;
    //Implementamos un metodo para guardar la informacion en la BBDD
    void guardar(T t) throws SQLException;
    //Implementamos un metodo para eliminar
    void eliminar(Long id) throws SQLException;
    //Implementar un metodo para desactivar el producto
    //Implementar un metodo para activar el producto
}
