package com.bryan.aplicacionweb.manejosesiones.test;

import com.bryan.aplicacionweb.manejosesiones.util.Conexion;
import java.sql.Connection;
import java.sql.SQLException;

public class TestConexion {
    public static void main(String[] args) {
        System.out.println("Probando conexión a la base de datos...");

        try {
            Connection conn = Conexion.getConnection();

            if (conn != null) {
                System.out.println("--Conexión con éxito--");
                System.out.println("Base de datos: " + conn.getCatalog());
                System.out.println("Auto-commit: " + conn.getAutoCommit());

                conn.close();
                System.out.println("Conexión cerrada correctamente");
            }

        } catch (SQLException e) {
            System.out.println("--Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
