package com.bryan.aplicacionweb.manejosesiones.repositorio;

import com.bryan.aplicacionweb.manejosesiones.models.Categoria;
import com.bryan.aplicacionweb.manejosesiones.models.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorioJdbcImplement implements Repositorio<Producto> {

    private Connection conn;

    public ProductoRepositorioJdbcImplement(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();

        String sql =
                "SELECT p.*, c.id AS idCategoria, c.nombreCategoria AS categoria " +
                        "FROM producto AS p " +
                        "INNER JOIN categoria AS c ON (p.idCategoria = c.id) " +
                        "ORDER BY p.id ASC";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Producto p = getProducto(rs);
                productos.add(p);
            }
        }
        return productos;
    }

    private static Producto getProducto(ResultSet rs) throws SQLException {

        Producto p = new Producto();
        p.setIdProducto(rs.getLong("id"));
        p.setNombreProducto(rs.getString("productoNombre"));
        p.setStock(rs.getInt("stock"));
        p.setPrecio(rs.getDouble("precio"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setFechaElaboracion(rs.getDate("fecha_elaboracion").toLocalDate());
        p.setFechaCaducidad(rs.getDate("fecha_caducidad").toLocalDate());
        p.setCondicion(rs.getInt("condicion"));

        Categoria c = new Categoria();
        c.setIdCategoria(rs.getLong("idCategoria"));
        c.setNombreCategoria(rs.getString("categoria"));

        p.setCategoria(c);
        return p;
    }

    @Override
    public Producto porId(Long id) throws SQLException {

        Producto producto = null;

        String sql =
                "SELECT p.*, c.id AS idCategoria, c.nombreCategoria AS categoria " +
                        "FROM producto AS p " +
                        "INNER JOIN categoria AS c ON (p.idCategoria = c.id) " +
                        "WHERE p.id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = getProducto(rs);
                }
            }
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {

        String sql;

        if (producto.getIdProducto() != null && producto.getIdProducto() > 0) {

            sql = "UPDATE producto SET nombreProducto=?, idCategoria=?, stock=?, precio=?, " +
                    "descripcion=?, codigo=?, fecha_elaboracion=?, fecha_caducidad=? WHERE id=?";

        } else {

            sql = "INSERT INTO producto (nombreProducto, idCategoria, stock, precio, descripcion, codigo, " +
                    "fecha_elaboracion, fecha_caducidad, condicion) VALUES (?,?,?,?,?,?,?,?,1)";
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, producto.getNombreProducto());
            stmt.setLong(2, producto.getCategoria().getIdCategoria());
            stmt.setInt(3, producto.getStock());
            stmt.setDouble(4, producto.getPrecio());
            stmt.setString(5, producto.getDescripcion());
            stmt.setString(6, producto.getCodigo());
            stmt.setDate(7, Date.valueOf(producto.getFechaElaboracion()));
            stmt.setDate(8, Date.valueOf(producto.getFechaCaducidad()));

            if (producto.getIdProducto() != null && producto.getIdProducto() > 0) {
                stmt.setLong(9, producto.getIdProducto());
            }

            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM producto WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
