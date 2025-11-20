package com.bryan.aplicacionweb.manejosesiones.services;

import com.bryan.aplicacionweb.manejosesiones.models.Producto;
import com.bryan.aplicacionweb.manejosesiones.repositorio.ProductoRepositorioJdbcImplement;
import com.google.protobuf.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJdbcImplement implements ProductoService {
    //Declaramos una variable de tipo ProductoRepositorioJdbcImplement
    private ProductoRepositorioJdbcImplement repositorioJdbc;
    //Implementamos un constructor para traer la conexion
    public ProductoServiceJdbcImplement(Connection connection){
        this.repositorioJdbc = new ProductoRepositorioJdbcImplement(connection);
    }
    @Override
    public List<Producto> listar(){
        try{
            return repositorioJdbc.listar();
        }catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(),throwables.getCause());
        }
    }
    @Override
    public Optional<Producto> porId(Long id){
        try{
            return Optional.ofNullable(repositorioJdbc.porId(id));
        } catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(),throwables.getCause());
        }
    }
}
