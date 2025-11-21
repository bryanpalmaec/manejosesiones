package com.bryan.aplicacionweb.manejosesiones.controllers;

import com.bryan.aplicacionweb.manejosesiones.services.ProductoServiceJdbcImplement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;

@WebServlet({"/activar","/desactivar"})
public class ProductoEstadoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        ProductoServiceJdbcImplement service = new ProductoServiceJdbcImplement(conn);

        Long id = Long.valueOf(req.getParameter("id"));

        if (req.getServletPath().contains("activar")) {
            service.activar(id);
        }
        if (req.getServletPath().contains("desactivar")) {
            service.desactivar(id);
        }


        resp.sendRedirect(req.getContextPath() + "/productos");
    }
}
