package com.bryan.aplicacionweb.manejosesiones.controllers;

import com.bryan.aplicacionweb.manejosesiones.services.ProductoService;
import com.bryan.aplicacionweb.manejosesiones.services.ProductoServiceJdbcImplement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/eliminar")
public class ProductoEliminarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Long id = req.getParameter("id") != null
                ? Long.parseLong(req.getParameter("id"))
                : null;

        if (id == null) {
            resp.sendRedirect(req.getContextPath() + "/productos");
            return;
        }

        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImplement(conn);

        try {
            service.eliminar(id);
            resp.sendRedirect(req.getContextPath() + "/productos");
        } catch (Exception e) {
            throw new ServletException("Error al eliminar el producto", e);
        }
    }
}
