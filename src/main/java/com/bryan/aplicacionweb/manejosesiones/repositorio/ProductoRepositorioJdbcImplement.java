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
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select p.*, c.nombreCategoria as categoria FROM producto as p "+
                    " INNER JOIN categoria as c ON (p.idCategoria=c.id) order by p.id ASC")){
        while(rs.next()){
            Producto p = getProducto(rs);
            productos.add(p);
        }
        }

        return productos;
    }

    private static Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setIdProducto(rs.getLong("id"));
        p.setNombreProducto(rs.getString("nombreProducto"));
        p.setStock(rs.getInt("stock"));
        p.setPrecio(rs.getDouble("precio"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setFechaElaboracion(rs.getDate("fecha_elabocacion").toLocalDate());
        p.setFechaCaducidad(rs.getDate("fecha_caducidad").toLocalDate());
        p.setCondicion(rs.getInt("condicion"));
        Categoria c = new Categoria();
        c.setIdCategoria(rs.getLong("id"));
        c.setNombreCategoria(rs.getString("categoria"));
        p.setCategoria(c);
        return p;
    }

    @Override
    public Producto porId(Long id) throws SQLException {
        Producto producto = null;
        try(PreparedStatement stmt = conn.prepareStatement("select p*, c.nombreCategoria as categoria FROM producto as p"
                        +" INNER JOIN categoria as c ON (p.idCategoria=c.id) where id=? order by p.id ASC")){
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    producto = getProducto(rs);
                }
            }
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {
        String sql;
        if(producto.getIdProducto() != null && producto.getIdProducto() >0){
            sql = "update producto set nombreCategoria=?, idCategoria=?, stock=?, precio=?, descripcion=? where idProducto=?"+
                    " fecha_elabocacion=?, fecha_caducidad=? where id=?";
        }else{
            sql = "insert into producto (nombreCategoria, idCategoria, stock, precio, descripcion, codigo, fecha_elaboracion, fecha_caducidad,condicion)" +
                    "values (?,?,?,?,?,?,?,?,1)";
        }
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, producto.getNombreProducto());
            stmt.setInt(2, producto.getStock());
            stmt.setDouble(3, producto.getPrecio());
            stmt.setString(4, producto.getDescripcion());
            stmt.setString(5, producto.getCodigo());
            stmt.setLong(6, producto.getCategoria().getIdCategoria());

            if(producto.getIdProducto() != null && producto.getIdProducto() >0){
                stmt.setLong(7, producto.getIdProducto());
            }else{
                stmt.setDate(7, Date.valueOf(producto.getFechaElaboracion()));
                stmt.setDate(8, Date.valueOf(producto.getFechaCaducidad()));
            }
            stmt.executeUpdate();
        }

    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "delete from producto where id=?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }

    }
}
