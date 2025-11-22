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
            out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css'>");
            out.println("</head>");
            out.println("<body class='bg-light'>");

            out.println("<div class='container mt-4 p-4 bg-white shadow rounded'>");

            out.println("<h1 class='mb-4'>Lista de productos</h1>");

            if (usernameOptional.isPresent()) {
                out.println("<div class='alert alert-info'>Hola <strong>"
                        + usernameOptional.get()
                        + "</strong>, bienvenido!</div>");
            }
            out.println("<a href='" + req.getContextPath() + "/crear' class='btn btn-success mb-3'>");
            out.println("<i class='bi bi-plus-circle'></i> Crear Producto</a>");

            out.println("<table class='table table-bordered table-striped'>");
            out.println("<thead class='table-dark'>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Nombre</th>");
            out.println("<th>Stock</th>");
            out.println("<th>Fecha Producción</th>");
            out.println("<th>Categoría</th>");  // 🔥 AGREGADO AQUÍ

            if (usernameOptional.isPresent()) {
                out.println("<th>Precio</th>");
                out.println("<th>Estado</th>");
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

                // categoria
                if (p.getCategoria() != null) {
                    out.println("<td>" + p.getCategoria().getNombreCategoria() + "</td>");
                } else {
                    out.println("<td><em>Sin categoría</em></td>");
                }

                if (usernameOptional.isPresent()) {

                    out.println("<td>$" + p.getPrecio() + "</td>");

                    String estadoBadge = p.getCondicion() == 1
                            ? "<span class='badge bg-success'>Activo</span>"
                            : "<span class='badge bg-secondary'>Inactivo</span>";
                    out.println("<td>" + estadoBadge + "</td>");

                    out.println("<td>");

                    out.println("<a href='" + req.getContextPath() + "/agregar-carro?id=" + p.getIdProducto()
                            + "' class='btn btn-primary btn-sm me-1'><i class='bi bi-cart-plus'></i></a>");

                    out.println("<a href='" + req.getContextPath() + "/editar?id=" + p.getIdProducto()
                            + "' class='btn btn-warning btn-sm me-1'><i class='bi bi-pencil-square'></i></a>");

                    if (p.getCondicion() == 1) {
                        out.println("<a href='" + req.getContextPath() + "/desactivar?id=" + p.getIdProducto()
                                + "' class='btn btn-danger btn-sm'><i class='bi bi-x-circle'></i></a>");
                    } else {
                        out.println("<a href='" + req.getContextPath() + "/activar?id=" + p.getIdProducto()
                                + "' class='btn btn-success btn-sm'><i class='bi bi-check-circle'></i></a>");
                    }

                    out.println("<a href='" + req.getContextPath() + "/eliminar?id=" + p.getIdProducto()
                            + "' class='btn btn-outline-danger btn-sm me-1' "
                            + "onclick=\"return confirm('¿Está seguro de eliminar este producto?');\">"
                            + "<i class='bi bi-trash'></i></a>");

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
