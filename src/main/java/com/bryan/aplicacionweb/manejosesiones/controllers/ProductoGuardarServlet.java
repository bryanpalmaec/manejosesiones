package com.bryan.aplicacionweb.manejosesiones.controllers;

import com.bryan.aplicacionweb.manejosesiones.models.Categoria;
import com.bryan.aplicacionweb.manejosesiones.models.Producto;
import com.bryan.aplicacionweb.manejosesiones.services.ProductoService;
import com.bryan.aplicacionweb.manejosesiones.services.ProductoServiceJdbcImplement;
import com.bryan.aplicacionweb.manejosesiones.util.Conexion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;

@WebServlet("/guardar")
public class ProductoGuardarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = req.getParameter("idProducto") != null && !req.getParameter("idProducto").isEmpty() ? Long.parseLong(req.getParameter("idProducto")) : null;
        String nombre = req.getParameter("nombreProducto");
        Long idCategoria = Long.parseLong(req.getParameter("idCategoria"));
        int stock = Integer.parseInt(req.getParameter("stock"));
        String descripcion = req.getParameter("descripcion");
        String codigo = req.getParameter("codigo");
        Double precio = Double.parseDouble(req.getParameter("precio"));
        String fe = req.getParameter("fechaElaboracion");
        String fc = req.getParameter("fechaCaducidad");
        LocalDate fechaElab = (fe != null && !fe.isEmpty()) ? LocalDate.parse(fe) : null;
        LocalDate fechaCad = (fc != null && !fc.isEmpty()) ? LocalDate.parse(fc) : null;
        int condicion = req.getParameter("condicion") != null ? Integer.parseInt(req.getParameter("condicion")) : 1;
        try (Connection conn = Conexion.getConnection()) {
            ProductoService service = new ProductoServiceJdbcImplement(conn);
            Producto p = new Producto();
            p.setIdProducto(id);
            Categoria c = new Categoria();
            c.setIdCategoria(idCategoria);
            p.setCategoria(c);
            p.setNombreProducto(nombre);
            p.setStock(stock);
            p.setDescripcion(descripcion);
            p.setCodigo(codigo);
            p.setPrecio(precio);
            p.setFechaElaboracion(fechaElab);
            p.setFechaCaducidad(fechaCad);
            p.setCondicion(condicion);
            service.guardar(p);
            resp.sendRedirect(req.getContextPath() + "/productos");
        } catch (Exception e) {
            throw new ServletException("Error al guardar producto", e);
        }
    }
}