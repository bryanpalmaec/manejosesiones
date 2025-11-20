package com.bryan.aplicacionweb.manejosesiones.repositorio;

import com.bryan.aplicacionweb.manejosesiones.models.Categoria;

import java.sql.SQLException;
import java.util.List;

public class CategoriaRepositorioJdbcImplement implements Repositorio<Categoria> {
    @Override
    public List<Categoria> listar() throws SQLException {
        return List.of();
    }

    @Override
    public Categoria porId(Long id) throws SQLException {
        return null;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }
}
