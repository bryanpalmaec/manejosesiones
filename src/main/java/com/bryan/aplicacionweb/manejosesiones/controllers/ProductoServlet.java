package com.bryan.aplicacionweb.manejosesiones.controllers;

import com.bryan.aplicacionweb.manejosesiones.models.Producto;
import com.bryan.aplicacionweb.manejosesiones.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImplement(conn);
        List<Producto> productos = service.listar();

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        resp.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='es'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Lista de Productos</title>");
            out.println("<link rel='stylesheet' " +
                    "href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css'>");
            out.println("</head>");
            out.println("<body class='bg-light'>");

            out.println("<div class='container mt-4 p-4 bg-white shadow rounded'>");

            out.println("<h1 class='mb-4'>Lista de productos</h1>");

            if (usernameOptional.isPresent()) {
                out.println("<div class='alert alert-info'>Hola <strong>"
                        + usernameOptional.get()
                        + "</strong>, bienvenido!</div>");
            }

            out.println("<table class='table table-bordered table-striped'>");
            out.println("<thead class='table-dark'>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Nombre</th>");
            out.println("<th>Stock</th>");
            out.println("<th>Fecha Producción</th>");

            if (usernameOptional.isPresent()) {
                out.println("<th>Precio</th>");
                out.println("<th>Opciones</th>");
            }

            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            productos.forEach(p -> {
                out.println("<tr>");
                out.println("<td>" + p.getIdProducto() + "</td>");
                out.println("<td>" + p.getNombreProducto() + "</td>");
                out.println("<td>" + p.getStock() + "</td>");
                out.println("<td>" + p.getFechaElaboracion() + "</td>");

                if (usernameOptional.isPresent()) {
                    out.println("<td>$" + p.getPrecio() + "</td>");
                    out.println("<td>");
                    out.println("<a class='btn btn-sm btn-success' href='"
                            + req.getContextPath()
                            + "/agregar-carro?id="
                            + p.getIdProducto()
                            + "'>Agregar</a>");
                    out.println("</td>");
                }

                out.println("</tr>");
            });

            out.println("</tbody>");
            out.println("</table>");

            out.println("<a href='" + req.getContextPath() + "/index.html' class='btn btn-secondary'>Volver</a>");

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
