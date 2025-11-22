package com.bryan.aplicacionweb.manejosesiones.controllers;

import com.bryan.aplicacionweb.manejosesiones.models.Categoria;
import com.bryan.aplicacionweb.manejosesiones.models.Producto;
import com.bryan.aplicacionweb.manejosesiones.services.ProductoService;
import com.bryan.aplicacionweb.manejosesiones.services.ProductoServiceJdbcImplement;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

public class ProductoFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Connection conn = (Connection) req.getServletContext().getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImplement(conn);
        Long id;
        try{
            id = Long.parseLong(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0L;
        }
        Producto producto = new Producto();
        producto.setCategoria(new Categoria());
        if (id >0){
            Optional<Producto> o = service.porId(id);
            if (o.isPresent()){
                producto = o.get();
            }
        }
        req.setAttribute("categorias", service.listarCategoria());
        req.setAttribute("producto", producto);
        getServletContext().getRequestDispatcher("/form.jsp").forward(req,resp);
    }
}
