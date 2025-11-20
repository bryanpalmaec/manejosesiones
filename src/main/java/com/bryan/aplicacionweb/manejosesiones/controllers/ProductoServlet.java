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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Traemos la conexion
        Connection conn = (Connection) req.getAttribute("conn");
        //Instancionamos el objeto Producto ServiceJdbcImplement
        //ProductoService service = new ProductoServiceImplement();
        ProductoService service = new ProductoServiceJdbcImplement(conn);
        List<Producto> productos = service.listar();

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        resp.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Lista de productos!</h1>");
            if(usernameOptional.isPresent()) {
                out.println("<div style='color: blue; '>Hola "+usernameOptional.get()+" Bienvenido! </div>");
            }
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>id</th>");
            out.println("<th>nombre</th>");
            out.println("<th>stock</th>");
            out.println("<th>fecha produccion</th>");
            if (usernameOptional.isPresent()) {
                out.println("<th>precio</th>");
                out.println("<th>Opciones</th>");
            }
            out.println("</tr>");
            productos.forEach(p -> {
                out.println("<tr>");
                out.println("<td>"+p.getIdProducto()+"</td");
                out.println("<td>"+p.getNombreProducto()+"</td>");
                out.println("<td>"+p.getStock()+"</td>");
                out.println("<td>"+p.getFechaElaboracion()+"</td>");
                if(usernameOptional.isPresent()) {
                    out.println("<td>"+p.getPrecio()+"</td>");
                    out.println("<td><a href=\""
                            +req.getContextPath()
                            +"/agregar-carro?id="
                            +p.getIdProducto()
                            + "\">Agregar Producto al carro</a></td>");
                }
                out.println("</tr>");
            });
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
