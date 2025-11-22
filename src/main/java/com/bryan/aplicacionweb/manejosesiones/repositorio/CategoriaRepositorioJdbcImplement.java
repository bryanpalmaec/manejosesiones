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
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from categoria")){
            while (rs.next()) {
                Categoria categoria = getCategoria(rs);
                categorias.add(categoria);
            }
        }
        return categorias;
    }

    @Override
    public Categoria porId(Long id) throws SQLException {
        Categoria categoria = null;
        try (PreparedStatement stmt = conn.prepareStatement("select * from categoria where id=?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    categoria = getCategoria(rs);
                }
            }
        }

        return categoria;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {
        String sql;

        if (categoria.getIdCategoria() != null && categoria.getIdCategoria() > 0) {
            sql = "UPDATE categoria SET nombreCategoria=? WHERE id=?";
        } else {
            sql = "INSERT INTO categoria(nombreCategoria) VALUES(?)";
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
        String sql = "DELETE FROM categoria WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public void activar(Long id) throws SQLException {
    }

    @Override
    public void desactivar(Long id) throws SQLException {

    }

    private static Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setNombreCategoria(rs.getString("nombreCategoria"));
        categoria.setDescripcionCategoria(rs.getString("descripcion"));
        categoria.setIdCategoria(rs.getLong("id"));
        return categoria;
    }

}
