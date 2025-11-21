package com.bryan.aplicacionweb.manejosesiones.repositorio;

import com.bryan.aplicacionweb.manejosesiones.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositorioJdbcImplement implements Repositorio<Categoria> {

    private Connection conn;

    public CategoriaRepositorioJdbcImplement(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();

        String sql = "SELECT id_categoria, nombre_categoria FROM categorias";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getLong("id_categoria"));
                c.setNombreCategoria(rs.getString("nombre_categoria"));
                categorias.add(c);
            }
        }

        return categorias;
    }

    @Override
    public Categoria porId(Long id) throws SQLException {
        Categoria categoria = null;

        String sql = "SELECT id_categoria, nombre_categoria FROM categorias WHERE id_categoria = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    categoria = new Categoria();
                    categoria.setIdCategoria(rs.getLong("id_categoria"));
                    categoria.setNombreCategoria(rs.getString("nombre_categoria"));
                }
            }
        }

        return categoria;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {
        String sql;

        if (categoria.getIdCategoria() != null && categoria.getIdCategoria() > 0) {
            sql = "UPDATE categorias SET nombre_categoria=? WHERE id_categoria=?";
        } else {
            sql = "INSERT INTO categorias(nombre_categoria) VALUES(?)";
        }

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, categoria.getNombreCategoria());

            if (categoria.getIdCategoria() != null && categoria.getIdCategoria() > 0) {
                ps.setLong(2, categoria.getIdCategoria());
            }

            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM categorias WHERE id_categoria=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }
}
