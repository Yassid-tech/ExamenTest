package com.poo_java.testprepexamen.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnexionDB {
    private static Connection conexion;

    static {
        try {
            // Explicitly load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace(System.err);
            throw new RuntimeException("Failed to load MySQL JDBC driver", e);
        }
    }

    public SingletonConnexionDB() {}

    public static Connection getConnection() {
        if (conexion == null) {
            try {
                conexion = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/gestionCabinet",
                        "root",
                        ""
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conexion;
    }
}
