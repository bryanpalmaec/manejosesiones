package com.bryan.aplicacionweb.manejosesiones.controllers;


import com.bryan.aplicacionweb.manejosesiones.models.Categoria;
import com.bryan.aplicacionweb.manejosesiones.models.Producto;
import com.bryan.aplicacionweb.manejosesiones.services.ProductoServiceJdbcImplement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;

import jakarta.servlet.http.*;


@WebServlet("/crear")
public class ProductoCrearServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("accion", "Crear");
        req.getRequestDispatcher("//producto-form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        ProductoServiceJdbcImplement service = new ProductoServiceJdbcImplement(conn);

        Producto p = new Producto();
        p.setNombreProducto(req.getParameter("nombre"));
        p.setStock(Integer.parseInt(req.getParameter("stock")));
        p.setPrecio(Double.parseDouble(req.getParameter("precio")));
        p.setDescripcion(req.getParameter("descripcion"));
        p.setCodigo(req.getParameter("codigo"));
        p.setFechaElaboracion(LocalDate.parse(req.getParameter("fechaElaboracion")));
        p.setFechaCaducidad(LocalDate.parse(req.getParameter("fechaCaducidad")));

        Categoria c = new Categoria();
        c.setIdCategoria(Long.parseLong(req.getParameter("idCategoria")));
        p.setCategoria(c);

        service.guardar(p);

        resp.sendRedirect(req.getContextPath() + "/productos");
    }
}

