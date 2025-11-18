package com.bryan.aplicacionweb.manejosesiones.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static String url="jdbc:mysql://localhost:3306/siscompraventa?useTimezone=true&serverTimezone=UTC";
    private static String user="root";
    private static String password="";
    /*
    * Implementacion
    * */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }
}
