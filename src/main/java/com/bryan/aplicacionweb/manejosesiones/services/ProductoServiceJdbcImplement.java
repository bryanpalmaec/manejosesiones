package com.bryan.aplicacionweb.manejosesiones.services;

import com.bryan.aplicacionweb.manejosesiones.models.Categoria;
import com.bryan.aplicacionweb.manejosesiones.models.Producto;
import com.bryan.aplicacionweb.manejosesiones.repositorio.ProductoRepositorioJdbcImplement;
import com.bryan.aplicacionweb.manejosesiones.repositorio.Repositorio;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJdbcImplement implements ProductoService {
    //Declaramos una variable de tipo ProductoRepositorioJdbcImplement
    private Repositorio<Producto> repositorioJdbc;
    private Repositorio<Categoria> repositorioCategoriaJdbc;
    //Implementamos un constructor para traer la conexion
    public ProductoServiceJdbcImplement(Connection connection){
        this.repositorioJdbc = new ProductoRepositorioJdbcImplement(connection);
    }
    @Override
    public List<Producto> listar() {
        try {
            return repositorioJdbc.listar();
        } catch (SQLException e) {
            throw new ServiceJdbcException("Error en listar productos", e);
        }
    }

    @Override
    public Optional<Producto> porId(Long id) {
        try {
            return Optional.ofNullable(repositorioJdbc.porId(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException("Error obteniendo producto por ID", e);
        }
    }

    @Override
    public void guardar(Producto producto){
        try {
            repositorioJdbc.guardar(producto);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void actualizar(Producto producto){
        try {
            repositorioJdbc.guardar(producto);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void eliminar(Long id){
        try {
            repositorioJdbc.eliminar(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategoria() {
        try{
            return repositorioCategoriaJdbc.listar();
        }catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        try{
            return Optional.ofNullable(repositorioCategoriaJdbc.porId(id));
        }catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void activar(Long id){
        try {
            repositorioJdbc.activar(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void desactivar(Long id){
        try {
            repositorioJdbc.desactivar(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }


}
