package com.bryan.aplicacionweb.manejosesiones.controllers;

import com.bryan.aplicacionweb.manejosesiones.models.DetalleCarro;
import com.bryan.aplicacionweb.manejosesiones.models.ItemCarro;
import com.bryan.aplicacionweb.manejosesiones.models.Producto;
import com.bryan.aplicacionweb.manejosesiones.services.ProductoService;
import com.bryan.aplicacionweb.manejosesiones.services.ProductoServiceImpl;
import com.bryan.aplicacionweb.manejosesiones.services.ProductoServiceJdbcImplement;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/agregar-carro")
public class AgregarCarroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        //Traemos la conexion
        Connection conn = (Connection) req.getAttribute("conn");
        //ProductoService service = new ProductoServiceImpl();
        //Instancioamos el nuevo objeto jdbc
        ProductoService service = new ProductoServiceJdbcImplement(conn);
        Optional<Producto> producto = service.porId(id);
        if (producto.isPresent()) {
            ItemCarro item = new ItemCarro(1,producto.get());
            HttpSession session = req.getSession();
            DetalleCarro detalleCarro;
            if(session.getAttribute("carro") == null){
                detalleCarro = new DetalleCarro();
                session.setAttribute("carro", detalleCarro);
            }else {
                detalleCarro = (DetalleCarro)session.getAttribute("carro");
            }
            detalleCarro.addItemCarro(item);

        }
        resp.sendRedirect(req.getContextPath()+ "/ver-carro");
    }
}
