package com.bryan.aplicacionweb.manejosesiones.controllers;

import com.bryan.aplicacionweb.manejosesiones.models.Producto;
import com.bryan.aplicacionweb.manejosesiones.services.LoginService;
import com.bryan.aplicacionweb.manejosesiones.services.LoginServiceSessionImpl;
import com.bryan.aplicacionweb.manejosesiones.services.ProductoService;
import com.bryan.aplicacionweb.manejosesiones.services.ProductoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImpl();
        List<Producto> productos = service.listar();

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Listado de Productos</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("</head>");
            out.println("<body class='bg-light'>");

            out.println("<div class='container mt-5'>");
            out.println("<div class='card shadow'>");
            out.println("<div class='card-body'>");

            out.println("<h1 class='text-center mb-4 text-primary'>Listado de productos</h1>");

            // Mostrar mensaje de bienvenida si hay sesión activa
            if (usernameOptional.isPresent()) {
                out.println("<div class='alert alert-success text-center'>");
                out.println("Hola <strong>" + usernameOptional.get() + "</strong>, ¡bienvenido!");
                out.println("</div>");
            } else {
                out.println("<div class='alert alert-warning text-center'>");
                out.println("Inicia sesión para ver los precios de los productos.");
                out.println("</div>");
            }

            // Tabla de productos
            out.println("<table class='table table-striped table-bordered text-center align-middle mt-3'>");
            out.println("<thead class='table-dark'>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Nombre</th>");
            out.println("<th>Categoría</th>");
            if (usernameOptional.isPresent()) {
                out.println("<th>Precio</th>");
                out.println("<th>Opciones</th>");
            }
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            for (Producto p : productos) {
                out.println("<tr>");
                out.println("<td>" + p.getIdProducto() + "</td>");
                out.println("<td>" + p.getNombre() + "</td>");
                out.println("<td>" + p.getCategoria() + "</td>");
                if (usernameOptional.isPresent()) {
                    out.println("<td>$" + p.getPrecio() + "</td>");
                    out.println("<td><a href=\""
                            +req.getContextPath()
                            +"/agregar-carro?id="
                            +p.getIdProducto()
                            +"\">Agregar Producto al carro</a></td>");
                }
                out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table>");

            out.println("<div class='text-center mt-4'>");
            out.println("<a href='" + req.getContextPath() + "/index.html' class='btn btn-outline-primary me-2'>Volver al inicio</a>");
            if (usernameOptional.isPresent()) {
                out.println("<a href='" + req.getContextPath() + "/logout' class='btn btn-outline-danger'>Cerrar sesión</a>");
            }
            out.println("</div>");

            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
