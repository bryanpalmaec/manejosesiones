package com.bryan.aplicacionweb.manejosesiones.filter;

import com.bryan.aplicacionweb.manejosesiones.services.ServiceJdbcException;
import com.bryan.aplicacionweb.manejosesiones.util.Conexion;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConexionFilter implements Filter {
    /*
    * Una clase Filter en Java es un objeto que realiza
    * tareas de filtrado en las solicitudes cliente servidor respuesta
    * a un recurso: los filtros se pueden ejecturar en servidores comptables con JAKARTA EE
    * Los filtros interceptan solicitudes y respuestas de manera dinamica
    * para transformar o utilizar la informacion que contienen. El filtrado se realiza mediante el metodo doFilter.
    * */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        /*
        * request: repiticion que hace el cliente
        * response: respuesta del servidor
        * filterChain: Es una clase de filtro que representa el flujo
        * de procesamiento, este metodo llama al metodo chain.doFilter(request, response)
        * dentro de un filtro pasa la solicitud, el siguiente paso la clase filtra
        * o te devuelve el recurso destino que puede ser un servlet o jsp
        * */
        try (Connection conn = Conexion.getConnection()) {
            //Verificamos que la conexion realizada o se cambien a autocomit
            //configuracion automatica a la base de datos y cada instruccion SQL
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            try {
                //Agregamos la conexion como un atributo en la solicitud
                //Esto nos permite que otros componentes como servlet o DAOS puedan acceder a la conexion
                request.setAttribute("conn", conn);
                //Pasa la solicitud y respuesta al siguiente filtro o recurso destino
                filterChain.doFilter(request, response);
                //Si el procesamiento se realizo correctamente se lanza la excepciones, la solicitud
                // y se aplica los cambios a la base de datos
                conn.commit();
                //Si ocurre un errores durante el prcesamiento se captura la excepcion
            } catch (SQLException | ServiceJdbcException e) {
                //Se deshacer los cambios con un rollback y de esta forma se mantiene la DB
                conn.rollback();
                //Emviamos un codigo de error HTTP 500 al cliente indicando un problema
                // interno con el servidor
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
